package com.alabada.con03_assist;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class MatrixMock {

    private int data[][];

    public MatrixMock(int size, int length, int number) {

        int counter = 0;
        data = new int[size][length];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                if (data[i][j] == number) {
                    counter++;
                }
            }
        }
        System.out.printf("Mock: There are %d ocurrences of number in generated data.\n", counter, number);
    }

    public int[] getRow(int row) {
        if ((row >= 0) && (row < data.length)) {
            return data[row];
        }
        return null;
    }

    public static void main(String[] args) {


		/*
         * Initializes the bi-dimensional array of data
		 * 		10000 rows
		 * 		1000 numbers in each row
		 * 		Looking for number 5
		 */
        final int ROWS = 10000;
        final int NUMBERS = 1000;
        final int SEARCH = 5;
        final int PARTICIPANTS = 5;
        final int LINES_PARTICIPANT = 2000;
        MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);

        Results results = new Results(ROWS);

        Grouper grouper = new Grouper(results);

        // Creates the CyclicBarrier object. It has 5 participants and, when
        // they finish, the CyclicBarrier will execute the grouper object
        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);

        // Creates, initializes and starts 5 Searcher objects
        Searcher searchers[] = new Searcher[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; i++) { // 启动5个线程
            searchers[i] = new Searcher(i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT, mock, results, 5, barrier);
            Thread thread = new Thread(searchers[i]);
            thread.start();
        }
        System.out.printf("Main: The main thread has finished.\n");
    }

}
