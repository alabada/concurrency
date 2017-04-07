package com.alabada.con03_assist.cyclicBarrier;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 22:01
 * @Description
 */
public class Grouper implements Runnable {

    private Results results;

    public Grouper(Results results) {
        this.results = results;
    }

    /**
     * 这个线程在所有其他线程都执行完毕后自动执行
     * 同步了其他5个查找线程
     */
    @Override
    public void run() {
        int finalResult = 0;
        System.out.printf("Grouper: Processing results...\n");
        int data[] = results.getData();
        for (int number : data) {
            finalResult += number;
        }
        System.out.printf("Grouper: Total result: %d.\n", finalResult);
    }
}
