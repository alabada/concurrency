package com.alabada.con01_Thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 17:36
 * @Description
 */
public class UnsafeTask implements Runnable {

    private Date startDate;

    @Override
    public void run() {
        startDate = new Date();
        System.out.printf("Starting Thread: %s: %s\n", Thread.currentThread().getId(), startDate);

        try {
            TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread Finished: %s : %s \n", Thread.currentThread().getId(), startDate);

    }

    /**
     * 发现创建出来的10个线程，同时操作了同一个属性startDate
     * 各线程之间没有隔离。
     * @param args
     */
    public static void main(String [] args) {
        UnsafeTask task = new UnsafeTask();
        for (int i=0; i<10; i++) {
            Thread thread = new Thread(task); // 启动10个线程
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
