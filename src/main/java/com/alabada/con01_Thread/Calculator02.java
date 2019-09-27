package com.alabada.con01_Thread;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 10:31
 * @Description 线程案例02
 */
public class Calculator02 implements Runnable {

    private int number;

    public Calculator02(int number) {
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

    public static void main(String[] args) {

        System.out.printf("Minimum Priority: %s\n", Thread.MIN_PRIORITY);
        System.out.printf("Normal Priority: %s\n", Thread.NORM_PRIORITY);
        System.out.printf("Maximun Priority: %s\n", Thread.MAX_PRIORITY);

        Thread[] threads = new Thread[10];
        Thread.State[] status = new Thread.State[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Calculator02(i));
            if ((i % 2) == 0) {
                // 设置线程的优先级
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread " + i);
        }


        try {

            FileWriter file = new FileWriter("D:\\01-Java\\concurrency\\src\\main\\java\\com\\alabada\\con01_Thread\\log.txt");
            PrintWriter pw = new PrintWriter(file);

            for (int i = 0; i < 10; i++) {
                // 读取各线程的状态，因还未start，故状态均为new
                pw.println("Main : Status of Thread " + i + " : " + threads[i].getState());
                status[i] = threads[i].getState();
            }

            for (int i = 0; i < 10; i++) {
                // 启动各线程
                threads[i].start();
            }

            boolean finish = false;
            while (!finish) {
                for (int i = 0; i < 10; i++) {
                    // 线程状态改变，打印该线程基本信息
                    if (threads[i].getState() != status[i]) {
                        writeThreadInfo(pw, threads[i], status[i]);
                        status[i] = threads[i].getState();
                    }
                }

                finish = true;
                for (int i = 0; i < 10; i++) {
                    // 所有线程结束，退出
                    finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
                }
            }

            pw.close(); // 不close，内容写不到文件里

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state) {
        // ID：保存了线程的唯一标识符；Name：保存了线程的名称
        pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());

        // Priority： 保存了线程对象的优先级。线程优先级从1到10，1为最低，10为最高；不推荐修改优先级。除非必须。
        pw.printf("Main : Priority: %d\n", thread.getPriority());

        // 保存了线程的状态：6中状态：new,runnable,blocked,waiting,time waiting,terminated
        pw.printf("Main : Old State: %s\n", state);
        pw.printf("Main : New State: %s\n", thread.getState());
        pw.printf("Main : ************************************\n");
    }
}
