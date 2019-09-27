package com.alabada.con02_synch.producerconsumer1;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 0:01
 * @Description
 */
public class EventStorage {

    private int maxSize;

    private List<Date> storage;

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<Date>();
    }

    /**
     * 保存数据到列表
     */
    public synchronized void set() {
        while (storage.size() == maxSize) { // 判断空间是否已满
            try {
                wait(); // wait() 方法将挂起线程，等待空余空间的出现
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.printf("Set: %d\n", storage.size());
        notifyAll(); // 唤醒被挂起的线程
    }

    public synchronized void get() {
        while (storage.size() == 0) {
            try {
                wait(); // 必须在while循环中调用wait，并且不断的查询while循环的条件，直到条件为真。
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Get: %d: %s\n", storage.size(), ((LinkedList<?>) storage).poll());
        notifyAll();
    }

}
