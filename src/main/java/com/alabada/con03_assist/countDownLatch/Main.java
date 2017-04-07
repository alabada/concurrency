package com.alabada.con03_assist.countDownLatch;

import com.alabada.con03_assist.countDownLatch.Participant;
import com.alabada.con03_assist.countDownLatch.Videoconference;

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
        threadConference.start(); // 启动一个会议线程

        // 创建10个参加会议人员线程，并启动
        for (int i = 0; i < 10; i++) {
            Participant p = new Participant(conference, "Participant " + i);
            Thread t = new Thread(p);
            t.start();
        }
    }
}
