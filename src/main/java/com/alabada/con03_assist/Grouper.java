package com.alabada.con03_assist;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 22:01
 * @Description
 */
public class Grouper implements Runnable {

    /**
     * Results object with the occurrences of the number in each row
     */
    private Results results;

    /**
     * Constructor of the class. Initializes its attributes
     *
     * @param results Results object with the ocurrences of the number in each row
     */
    public Grouper(Results results) {
        this.results = results;
    }

    /**
     * Main method of the Grouper. Sum the values stored in the Results object
     */
    @Override
    public void run() {
        int finalResult = 0;
        System.out.printf("Grouper: Processing results...\n");
        int data[] = results.getData();
        for (int number : data) {
            finalResult += number;
        }
        System.out.printf("Grouper: Total result: %d.\n", finalResult);
    }
}
