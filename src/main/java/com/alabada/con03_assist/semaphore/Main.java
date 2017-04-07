package com.alabada.con03_assist.semaphore;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/7 15:31
 * @Description
 */
public class Main {

    public static void main(String args[]) {

        PrintQueue printQueue = new PrintQueue();

        // 创建10个线程，并启动
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }
        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }
}
