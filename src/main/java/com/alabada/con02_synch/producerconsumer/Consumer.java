package com.alabada.con02_synch.producerconsumer;

import java.util.Random;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 14:59
 * @Description
 */
public class Consumer implements Runnable {

    private Buffer buffer;

    public Consumer (Buffer buffer) {
        this.buffer=buffer;
    }

    @Override
    public void run() {
        while (buffer.hasPendingLines()) {
            String line=buffer.get();
            processLine(line);
        }
    }

    private void processLine(String line) {
        try {
            Random random=new Random();
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
