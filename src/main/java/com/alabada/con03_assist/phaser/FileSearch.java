package com.alabada.con03_assist.phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/7 13:07
 * @Description
 */
public class FileSearch implements Runnable {

    // 用来存储查找的文件夹
    private String initPath;

    // 用来存储要查找文件的扩展名
    private String end;

    // 用来存储查找到的文件的完整路径
    private List<String> results;

    // 用来控制任务不同阶段的同步
    private Phaser phaser;

    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.phaser = phaser;
        results = new ArrayList<>();
    }

    /**
     * 使用Phaser对象控制阶段的改变
     */
    @Override
    public void run() {

        // 该方法能够确保查找工作在所有线程都被创建之后再开始，保障在所有线程创建好之前没有线程开始执行任务。即所有线程都将在同一个起跑线上。
        phaser.arriveAndAwaitAdvance();

        System.out.printf("%s: Starting.\n", Thread.currentThread().getName());

        // 1st Phase: Look for the files
        File file = new File(initPath);
        if (file.isDirectory()) {
            directoryProcess(file);
        }

        // If no results, deregister in the phaser and ends
        if (!checkResults()) {
            return;
        }

        // 2nd Phase: Filter the results
        filterResults();

        // If no results after the filter, deregister in the phaser and ends
        if (!checkResults()) {
            return;
        }

        // 3rd Phase: Show info
        showInfo(); // 将结果打印到控制台
        phaser.arriveAndDeregister(); // 撤销线程的注册
        System.out.printf("%s: Work completed.\n", Thread.currentThread().getName());

    }

    /**
     * 简单的打印结果
     */
    private void showInfo() {
        for (int i = 0; i < results.size(); i++) {
            File file = new File(results.get(i));
            System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }
        // Waits for the end of all the FileSearch threads that are registered in the phaser
        phaser.arriveAndAwaitAdvance(); // 确保三个线程都已完成
    }

    /**
     * 检查结果集是否为空
     */
    private boolean checkResults() {
        if (results.isEmpty()) {
            System.out.printf("%s: Phase %d: 0 results.\n", Thread.currentThread().getName(), phaser.getPhase());
            System.out.printf("%s: Phase %d: End.\n", Thread.currentThread().getName(), phaser.getPhase());
            // No results. Phase is completed but no more work to do. Deregister for the phaser
            phaser.arriveAndDeregister(); // 通知Phaser对象当前线程已经结束这个阶段，并且将不再参与接下来的阶段操作
            return false;
        } else {
            // There are results. Phase is completed. Wait to continue with the next phase
            System.out.printf("%s: Phase %d: %d results.\n", Thread.currentThread().getName(), phaser.getPhase(), results.size());
            phaser.arriveAndAwaitAdvance(); // 通知Phaser对象当前线程已经完成了当前阶段，需要被阻塞直到其他线程也都完成当前阶段
            return true;
        }
    }

    /**
     * 做过滤操作，将不是过去24小时修改过的文件删除
     */
    private void filterResults() {
        List<String> newResults = new ArrayList<>();
        long actualDate = new Date().getTime(); // 获取当前时间
        for (int i = 0; i < results.size(); i++) {
            File file = new File(results.get(i));
            long fileDate = file.lastModified();

            if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
                newResults.add(results.get(i));
            }
        }
        results = newResults;
    }

    /**
     * 处理文件夹的所有文件和子文件夹
     * 对于每一个文件夹，方法将递归调用；
     * 对于每一个文件，方法将调用fileProcess()方法。
     */
    private void directoryProcess(File file) {

        File list[] = file.listFiles();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].isDirectory()) { // 如果是文件夹，递归调用
                    directoryProcess(list[i]);
                } else { // 如果是文件，调到文件处理
                    fileProcess(list[i]);
                }
            }
        }
    }

    /**
     * 获取文件名字的扩展名是不是我们需要的
     */
    private void fileProcess(File file) {
        if (file.getName().endsWith(end)) {
            results.add(file.getAbsolutePath());
        }
    }
}
