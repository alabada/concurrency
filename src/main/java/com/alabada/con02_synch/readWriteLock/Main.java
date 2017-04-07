package com.alabada.con02_synch.readWriteLock;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/7 18:01
 * @Description
 */
public class Main {

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
