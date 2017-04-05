package com.alabada.con01_Thread;


import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 16:23
 * @Description
 */
public class WriteTask implements Runnable {

    private Deque<Event> deque;

    public WriteTask(Deque<Event> deque) {
        this.deque = deque;
    }

    public void run() {
        for (int i=1; i<30; i++) {
            // Creates and initializes the Event objects
            Event event=new Event();
            event.setDate(new Date());
            event.setEvent(String.format("The thread %s has generated an event", Thread.currentThread().getId()));

            // 双向数据结构
            deque.addFirst(event);
            try {
                // Sleeps during one second
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
