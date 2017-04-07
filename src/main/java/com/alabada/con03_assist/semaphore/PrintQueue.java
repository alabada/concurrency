package com.alabada.con03_assist.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 15:59
 * @Description
 */
public class PrintQueue {

    // 声明信号量对象
    private final Semaphore semaphore;

    private boolean freePrinters[];

    private Lock lockPrinters;

    public PrintQueue() {
        // 将1作为传入参数，创建的就是二进制信号量。
        // 如果信号量的内部计数器初始值是1，所以它只能保护 一个 共享资源的访问。
        // 以下将信号量初始化为3
        semaphore = new Semaphore(3);

        // 模拟哪些打印机在使用的数组
        freePrinters = new boolean[3];

        for (int i = 0; i < 3; i++) {
            freePrinters[i] = true;
        }
        lockPrinters = new ReentrantLock();
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
            semaphore.acquire(); // 1、每个线程都得通过该方法获得信号量。

            // 2、开始使用共享资源
            int assignedPrinter = getPrinter();
            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job in Printer %d during %d seconds\n", Thread.currentThread().getName(), assignedPrinter, duration);
            TimeUnit.SECONDS.sleep(duration);
            freePrinters[assignedPrinter] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // 3、共享资源使用完毕需要释放信号量
        }
    }

    private int getPrinter() {
        int ret = -1;

        try {
            lockPrinters.lock();
            for (int i = 0; i < freePrinters.length; i++) {
                if (freePrinters[i]) {
                    ret = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockPrinters.unlock();
        }
        return ret;
    }

}
