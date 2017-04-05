package com.alabada.con01_Thread;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 16:36
 * @Description
 */
public class CleanerTask extends Thread {

    private Deque<Event> deque;

    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;
        setDaemon(true); // 把这个线程设置为守护线程 注意:该方法只能在start方法被调用之前设置。一旦线程开始运行，将不能再修改守护状态。
    }

    @Override
    public void run() {
        while (true) { // 守护线程一般都是一个无线循环，比如垃圾回收
            Date date = new Date();
            clean(date);
        }
    }

    private void clean(Date date) {
        long difference;
        boolean delete;
        if (deque.size() == 0) {
            return;
        }
        delete = false;

        do {
            Event e = deque.getLast();
            difference = date.getTime() - e.getDate().getTime();
            if (difference > 10000) {
                System.out.printf("Cleaner: %s\n", e.getEvent());
                deque.removeLast();
                delete = true;
            }
        } while (difference > 10000);
        if(delete) {
            System.out.printf("Cleaner: Size of the queue: %d\n", deque.size());
        }
    }

    public static void main(String [] args) {
        Deque<Event> deque = new ArrayDeque<Event>();

        WriteTask writer = new WriteTask(deque);
        for (int i=0; i<3; i++) { // 启动3个用户线程
            Thread thread = new Thread(writer);
            thread.start();
        }

        CleanerTask cleanerTask = new CleanerTask(deque);
        cleanerTask.start(); // 启动一个守护线程
    }

}
