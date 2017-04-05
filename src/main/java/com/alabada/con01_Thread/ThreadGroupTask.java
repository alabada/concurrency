package com.alabada.con01_Thread;

import java.util.Random;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 19:03
 * @Description
 */
public class ThreadGroupTask implements Runnable {

    @Override
    public void run() {
        int result;
        Random random = new Random(Thread.currentThread().getId());
        while (true) {
            result = 1000 / ((int) (random.nextDouble() * 1000));
            System.out.printf("%s : %f\n", Thread.currentThread().getId(), result);
            if (Thread.currentThread().isInterrupted()) { // 判断当前线程是否中断了
                System.out.printf("%d : Interrupted\n", Thread.currentThread().getId());
                return;
            }
        }
    }

    public static void main(String[] args) {

        // Create a MyThreadGroup object
        MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
        ThreadGroupTask task = new ThreadGroupTask();
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(threadGroup, task);
            t.start();
        }
    }
}
