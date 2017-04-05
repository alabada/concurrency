package com.alabada.con01_Thread;

import java.sql.Time;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 18:17
 * @Description
 */
public class SearchTask implements Runnable {

    private Result result;

    public SearchTask(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("Thread %s: Strat\n", name);

        try {
            doTask();
            result.setName(name);
        } catch (InterruptedException e) {
            System.out.printf("Thread %s: Interrupted\n", name);
            return;
        }
        System.out.printf("Thread %s: End\n", name);
    }

    private void doTask() throws InterruptedException {
        Random random = new Random((new Date()).getTime());
        int value = (int)(random.nextDouble()*100);
        System.out.printf("Thread %s: %d\n",Thread.currentThread().getName(), value);
        TimeUnit.SECONDS.sleep(value);
    }

    public static void main(String [] args) {
        ThreadGroup threadGroup = new ThreadGroup("Searcher"); // 创建一个标识为Searcher的线程组对象

        Result result = new Result();
        SearchTask searchTask = new SearchTask(result);

        for (int i = 0; i < 5; i ++) {
            Thread thread = new Thread(threadGroup, searchTask); // 第一个参数为ThreadGroup对象，第二个参数是SearchTask对象。
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Number of Threads: %d\n", threadGroup.activeCount()); // 获取线程组包含的线程数目
        System.out.printf("Information about the Thread Group\n");
        threadGroup.list();

        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads); // 获取线程组包含的线程列表
        for (int i=0; i<threadGroup.activeCount(); i++) { // 依次获取线程组下各线程的基本信息
            System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
        }

        waitFinish(threadGroup);

        threadGroup.interrupt(); // 调用线程组的interrupt方法，将该组下其余线程都中断

    }

    private static void waitFinish(ThreadGroup threadGroup) {
        while (threadGroup.activeCount() > 9) {
            try {
                TimeUnit.SECONDS.sleep(1); // sleep一个线程后，将跳出该循环
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
