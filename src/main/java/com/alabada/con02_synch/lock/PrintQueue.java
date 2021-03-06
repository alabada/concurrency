package com.alabada.con02_synch.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 9:34
 * @Description
 */
public class PrintQueue {

    /**
     * 使用锁实现一个临界区，并且保证同一时间只有一个执行线程访问这个临界区，必须创建ReentrantLock对象。
     * 临界区的开始，必须通过lock（）方法获取对锁的控制
     *
     * 线程离开临界区的时候，必须使用unlock()方法来释放它持有的锁，以让其他线程来访问临界区。如果在离开临界区的时候没有调用unlock（）方法，其他线程将永远地等待，从而导致死锁情景。
     */
//    private final Lock queueLock = new ReentrantLock(false); // 申明一个锁对象 非公平模式
    private final Lock queueLock = new ReentrantLock(true); // 申明一个锁对象 公平模式

    public void printJob(Object document) {
        queueLock.lock(); // 获取对锁对象的控制

        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), (duration / 1000));
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock(); // 释放对锁对象的控制
        }

        queueLock.lock();
        try {
            Long duration=(long)(Math.random()*10000);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",Thread.currentThread().getName(),(duration/1000));
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
    }

}
