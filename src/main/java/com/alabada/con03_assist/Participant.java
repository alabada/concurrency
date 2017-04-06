package com.alabada.con03_assist;

import java.util.concurrent.TimeUnit;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 21:13
 * @Description
 */
public class Participant implements Runnable {

    private Videoconference conference;

    private String name;

    public Participant(Videoconference conference, String name) {
        this.conference = conference;
        this.name = name;
    }

    @Override
    public void run() {
        Long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        conference.arrive(name);
    }
}
