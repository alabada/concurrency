package com.alabada.con01_Thread;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 11:29
 * @Description 采用继承Thread的方式来创建线程
 */
public class PrimeGenerator extends Thread {

    @Override
    public void run() {
        long number = 1L;
        while (true) {
            if (isPrime(number)) {
                System.out.printf("Number %d is Prime", number);
                System.out.println();
            }

            if (isInterrupted()) { // 获取该线程的状态：是否已被中断
                System.out.printf("The Prime Generator has been Interruped");
                System.out.println();
                return;
            }
            number++;
        }
    }

    private boolean isPrime(long number) {
        if (number <= 2) {
            return true;
        }
        for (long i = 2; i < number; i++) {
            if ((number % i) == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Thread task = new PrimeGenerator();
        task.start(); // 开启线程

        try {
            // 让main线程睡眠5秒，避免线程开启就关闭
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.interrupt(); // 中断线程
    }

}
