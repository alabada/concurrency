package com.alabada.con03_assist.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 21:05
 * @Description 会议
 */
public class Videoconference implements Runnable {

    // 声明
    private final CountDownLatch controller;

    /**
     * 构造器中初始化CountDownLatch（注意需要传递一个数字给他）
     * @param number
     */
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
             *
             * 会议室线程在这里等啊等，直到CountDownLatch中的计数器为0了，他就唤醒我们的会议室线程了，因为这表示人已经到齐了。
             */
            controller.await(); //
            // Starts the conference
            System.out.printf("VideoConference: All the participants have come\n");
            System.out.printf("VideoConference: Let's start...\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
