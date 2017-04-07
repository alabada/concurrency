package com.alabada.con03_assist.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/7 16:48
 * @Description
 */
public class Main {

    public static void main(String[] args) {

        final int ROWS = 10000;
        final int NUMBERS = 1000;
        final int SEARCH = 5;
        final int PARTICIPANTS = 5;
        final int LINES_PARTICIPANT = 2000;
        MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);

        Results results = new Results(ROWS);

        /**
         * grouper线程将会同步其他5个查找线程，通过什么方式
         * 通过将该线程的Runnable对象传递给CyclicBarrier，由CyclicBarrier去自动的完成同步动作。
         */
        Grouper grouper = new Grouper(results); //

        /**
         * 创建CyclicBarrier对象需要传递一个数字，让其知道需要同步几个线程
         * 将Runnable对象作为参数传递
         */
        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);

        // 创建出5个查找线程，并启动
        Searcher searchers[] = new Searcher[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; i++) {
            // 创建Searcher线程注意，需要将CyclicBarrier对象传递进来。
            searchers[i] = new Searcher(i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT, mock, results, 5, barrier);
            Thread thread = new Thread(searchers[i]);
            thread.start();
        }
        System.out.printf("Main: The main thread has finished.\n");
    }
}
