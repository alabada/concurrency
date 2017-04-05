package com.alabada.con01_Thread;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 17:16
 * @Description
 */
public class Task implements Runnable {

    @Override
    public void run() {
        int numero = Integer.parseInt("sss"); // 抛出一个运行时异常
    }

    public static void main(String [] args) {
        Task task = new Task();
        Thread thread = new Thread(task);

        /**
         * 如果线程没有被与之未捕获异常处理器，JVM将打印堆栈记录到控制台，并退出程序
         */
        thread.setUncaughtExceptionHandler(new ExceptionHandler()); // 在线程对象里捕获和处理运行时异常的一种机制。
        thread.start();
    }
}
