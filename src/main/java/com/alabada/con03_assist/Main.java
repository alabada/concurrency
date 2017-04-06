package com.alabada.con03_assist;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 21:16
 * @Description
 */
public class Main {
    public static void main(String[] args) {

        Videoconference conference = new Videoconference(10);
        Thread threadConference = new Thread(conference);
        threadConference.start();

        for (int i = 0; i < 10; i++) {
            Participant p = new Participant(conference, "Participant " + i);
            Thread t = new Thread(p);
            t.start();
        }

    }
}
