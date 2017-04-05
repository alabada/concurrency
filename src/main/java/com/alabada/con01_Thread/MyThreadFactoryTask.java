package com.alabada.con01_Thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 19:24
 * @Description
 */
public class MyThreadFactoryTask implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory"); // 创建一个线程工厂
        MyThreadFactoryTask task = new MyThreadFactoryTask();
        Thread thread;

        System.out.printf("Starting the Threads\n");
        for (int i = 0; i < 10; i++) {
            thread = factory.newThread(task); // 利用线程工厂创建线程
            thread.start();
        }
        System.out.printf("Factory stats:\n");
        System.out.printf("%s\n", factory.getStats());

    }

}
