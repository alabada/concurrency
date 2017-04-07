package com.alabada.con03_assist.exchange;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/7 15:06
 * @Description
 */
public class Main {

    public static void main(String[] args) {

        // Creates two buffers
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();

        // Creates the exchanger
        Exchanger<List<String>> exchanger = new Exchanger<>();

        // Creates the producer
        Producer producer = new Producer(buffer1, exchanger);
        // Creates the consumer
        Consumer consumer = new Consumer(buffer2, exchanger);

        // Creates and starts the threads
        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);

        threadProducer.start();
        threadConsumer.start();

    }
}
