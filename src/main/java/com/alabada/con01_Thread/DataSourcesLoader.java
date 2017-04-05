package com.alabada.con01_Thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 15:14
 * @Description
 */
public class DataSourcesLoader implements Runnable {

    public void run() {
        System.out.printf("Beginning data sources loading: %s\n", new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Data sources loading has finished:%s\n", new Date());
    }

    public static void main(String [] args) {
        DataSourcesLoader dsLoader = new DataSourcesLoader();
        Thread thread1 = new Thread(dsLoader, "DataSourceThread");

        NetworkConnctionLoader ncLoader = new NetworkConnctionLoader();
        Thread thread2 = new Thread(ncLoader, "NetworkConnectionLoader");

        thread1.start();
        thread2.start();

        try {
            /**
             * 当一个线程对象的join()方法被调用时，调用它的线程将被挂起，直到这个线程对象完成它的任务。
             */
            thread1.join(); // main线程将被挂起，直到thread1线程执行结束；但thread2线程不会被挂起。
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: Configuration has been loaded: %s\n", new Date());

    }

}
