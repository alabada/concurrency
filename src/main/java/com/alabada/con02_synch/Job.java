package com.alabada.con02_synch;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 10:12
 * @Description
 */
public class Job implements Runnable {

    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }
}
