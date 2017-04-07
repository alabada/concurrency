package com.alabada.con03_assist.countDownLatch;


import java.util.concurrent.TimeUnit;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 21:13
 * @Description 与会者
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
            TimeUnit.SECONDS.sleep(duration); // 模拟参加会议的人员到达会议室所需时间。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        conference.arrive(name); // 这个方法最后让计数器减1
    }
}
