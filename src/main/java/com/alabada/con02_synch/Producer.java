package com.alabada.con02_synch;


/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 0:09
 * @Description
 */
public class Producer implements Runnable {

    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.set();
        }
    }
}
