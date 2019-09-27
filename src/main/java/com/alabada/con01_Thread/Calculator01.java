package com.alabada.con01_Thread;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 9:58
 * @Description 线程案例01
 */
public class Calculator01 implements Runnable {

    private int number;

    public Calculator01(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            // printf 的使用
            System.out.printf("%s : %d * %d = %d", Thread.currentThread().getName(), number, i, i * number);
            System.out.println();
        }
    }

    /**
     * 该例子演示了多线程之间是如何进行切换执行的。
     * 各线程之间，并不会等一个线程所有都执行完毕后，再切到另一个线程，而是线程之间相互切换着执行。
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Calculator01 calculator01 = new Calculator01(i);
            Thread thread = new Thread(calculator01);
            thread.start(); // 调用Thread对象的start方法，另一个执行线程将被创建。
        }
    }
}
