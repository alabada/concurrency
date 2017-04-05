package com.alabada.con01_Thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 17:56
 * @Description 使用线程局部变量
 */
public class SafeTask implements Runnable {

    /**
     * 通过这种方式，每个线程都有它们自己的startDate属性了
     */
    private static ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
        protected Date initialValue() {
            return new Date();
        }
    };


    @Override
    public void run() {
        System.out.printf("Starting Thread: %s: %s\n", Thread.currentThread().getId(), startDate.get());

        try {
            TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread Finished: %s : %s \n", Thread.currentThread().getId(), startDate.get());
    }

    public static void main(String [] args) {
        SafeTask task = new SafeTask();
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
