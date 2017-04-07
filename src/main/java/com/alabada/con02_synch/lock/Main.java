package com.alabada.con02_synch.lock;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/7 17:44
 * @Description
 */
public class Main {

    public static void main(String args[]) {

        PrintQueue printQueue = new PrintQueue();

        /**
         * 创建10个线程来调用打印机
         */
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }
        for (int i = 0; i < 10; i++) {
            thread[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
