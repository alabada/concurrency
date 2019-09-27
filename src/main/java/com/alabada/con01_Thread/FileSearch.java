package com.alabada.con01_Thread;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 13:06
 * @Description
 */
public class FileSearch implements Runnable {

    private String initPath;

    private String fileName;

    public FileSearch(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }


    @Override
    public void run() {

        File file = new File(initPath);
        if (file.isDirectory()) {
            try {
                directoryProcess(file);
            } catch (InterruptedException e) { // 实时监测异常是否发生了
                System.out.printf("%s: The search has been interrupted", Thread.currentThread().getName());
            }
        }

    }

    private void directoryProcess(File file) throws InterruptedException {
        File list[] = file.listFiles();
        if(list != null) {
            for (int i = 0; i <list.length; i ++) {
                if (list[i].isDirectory()) {
                    directoryProcess(list[i]);
                } else {
                    fileProcess(list[i]);
                }
            }
            if (Thread.interrupted()) { // 程序处理过程中，抛出异常
                throw new InterruptedException();
            }
        }
    }

    private void fileProcess(File file) throws InterruptedException {
        if (file.getName().equals(fileName)) {
            System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }

    public static void main(String [] args) {
        FileSearch searcher = new FileSearch("D:\\", "log.txt");
        Thread thread = new Thread(searcher);
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }

}
