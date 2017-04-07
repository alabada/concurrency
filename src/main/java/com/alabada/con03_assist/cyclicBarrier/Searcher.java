package com.alabada.con03_assist.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 21:53
 * @Description
 */
public class Searcher implements Runnable {

    private int firstRow;

    private int lastRow;

    private MatrixMock mock;

    private Results results;

    private int number;

    private final CyclicBarrier barrier;

    public Searcher(int firstRow, int lastRow, MatrixMock mock, Results results, int number, CyclicBarrier barrier) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.mock = mock;
        this.results = results;
        this.number = number;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        int counter;
        System.out.printf("%s: Processing lines from %d to %d.\n", Thread.currentThread().getName(), firstRow, lastRow);
        for (int i = firstRow; i < lastRow; i++) {
            int row[] = mock.getRow(i);
            counter = 0;
            for (int j = 0; j < row.length; j++) {
                if (row[j] == number) {
                    counter++;
                }
            }
            results.setData(i, counter);
        }
        System.out.printf("%s: Lines processed.\n", Thread.currentThread().getName());
        try {
            barrier.await(); // 所有的线程都将在这里等住，直到所有线程查找完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
