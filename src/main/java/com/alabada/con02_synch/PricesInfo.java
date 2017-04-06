package com.alabada.con02_synch;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 13:22
 * @Description
 */
public class PricesInfo {

    private double price1;
    private double price2;

    private ReadWriteLock lock;

    public PricesInfo() {
        price1 = 1.0;
        price2 = 2.0;
        lock = new ReentrantReadWriteLock(); // 创建一个ReentrantReadWriteLock实例
    }

    /**
     * 使用读锁来获取对这个属性的访问
     *
     * 读操作锁时可以允许多个线程同时访问
     *
     * @return
     */
    public double getPrice1() {
        lock.readLock().lock(); //
        double value = price1;
        lock.readLock().unlock();
        return value;
    }

    public double getPrice2() {
        lock.readLock().lock();
        double value = price2;
        lock.readLock().unlock();
        return value;
    }

    /**
     * 使用写锁来控制对两个属性的操作
     *
     * 写操作锁时只允许一个线程进行
     *
     * @param price1
     * @param price2
     */
    public void setPrices(double price1, double price2) {
        lock.writeLock().lock();
        this.price1 = price1;
        this.price2 = price2;
        lock.writeLock().unlock();
    }

    public static void main(String[] args) {

        PricesInfo pricesInfo = new PricesInfo();

        // 创建5个读线程，并启动
        Reader readers[] = new Reader[5];
        Thread threadsReader[] = new Thread[5];
        for (int i = 0; i < 5; i++) {
            readers[i] = new Reader(pricesInfo);
            threadsReader[i] = new Thread(readers[i]);
        }

        Writer writer = new Writer(pricesInfo);
        Thread threadWriter = new Thread(writer);
        for (int i = 0; i < 5; i++) {
            threadsReader[i].start();
        }
        threadWriter.start(); // 创建一个写线程，并启动

    }

}
