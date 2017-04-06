package com.alabada.con03_assist;

import java.util.concurrent.CountDownLatch;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 21:05
 * @Description
 */
public class Videoconference implements Runnable {

    private final CountDownLatch controller;

    public Videoconference(int number) {
        controller = new CountDownLatch(number);
    }

    public void arrive(String name) {
        System.out.printf("%s has arrived.\n", name);
        controller.countDown(); // 将内部计数器减1
        System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
    }

    @Override
    public void run() {
        System.out.printf("VideoConference: Initialization: %d participants.\n", controller.getCount());
        try {
            /**
             * 这个方法会让线程等待直到内部计数器为0
             */
            controller.await(); // 会在这里等待
            // Starts the conference
            System.out.printf("VideoConference: All the participants have come\n");
            System.out.printf("VideoConference: Let's start...\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
