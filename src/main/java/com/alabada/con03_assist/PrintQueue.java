package com.alabada.con03_assist;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 15:59
 * @Description
 */
public class PrintQueue {

    private final Semaphore semaphore;

    public PrintQueue() {
        // 将1作为传入参数，创建的就是二进制信号量。
        // 信号量的内部计数器初始值是1，所以它只能保护一个共享资源的访问。
        semaphore = new Semaphore(1);
    }

    /**
     * 使用信号量实现临界区必须遵循的三个步骤，从而保护对共享资源的访问：
     * 首先，必须通过acquire（）方法获得信号量；
     * 其次，使用共享资源执行必要的操作；
     * 最后，必须通过release（）方法释放信号量
     *
     * @param document
     */
    public void printJob(Object document) {
        try {
            semaphore.acquire(); // 1、通过该方法获得信号量，

            // 2、
            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), duration);
            Thread.sleep(duration);
//            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // 3、释放信号量
        }
    }

    public static void main(String args[]) {

        PrintQueue printQueue = new PrintQueue();

        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }

        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }
}
