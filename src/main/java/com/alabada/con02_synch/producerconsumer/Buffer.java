package com.alabada.con02_synch.producerconsumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 14:36
 * @Description
 */
public class Buffer {

    private LinkedList<String> buffer; // 用来存放共享数据
    private int maxSize; // 用来存放buffer的长度
    private ReentrantLock lock; // 用来对修改buffer的代码块进行控制
    private Condition lines; // 两个条件对象
    private Condition space;
    private boolean pendingLines; // 用来表明缓冲区是否还有数据。

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        space = lock.newCondition();
        pendingLines = true;
    }

    /**
     * 把接到的字符串写到缓冲区中
     * @param line
     */
    public void insert(String line) {
        lock.lock(); // 首先获取锁
        try {
            while (buffer.size() == maxSize) {
                space.await(); // 类似对象的wait方法 space将自动释放这个条件绑定的锁，其他某个线程才可以获取这个锁并且执行相同的操作，或者执行这个锁保护的另一个临界区代码。
            }
            buffer.offer(line);
            System.out.printf("%s: Inserted Line: %d\n", Thread.currentThread()
                    .getName(), buffer.size());
            lines.signalAll(); // 类似对象notify、notifyAll 这里将唤醒所有等待缓冲区中有数据的线程。
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 返回缓冲区中的第一个字符串
     * @return
     */
    public String get() {
        String line = null;
        lock.lock(); // 获取锁
        try {
            while ((buffer.size() == 0) && (hasPendingLines())) {
                lines.await();
            }

            if (hasPendingLines()) {
                line = buffer.poll();
                System.out.printf("%s: Line Readed: %d\n", Thread.currentThread().getName(), buffer.size());
                space.signalAll(); // 唤醒所有等待缓冲区有空位的线程，以插入数据
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return line;
    }

    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

    public boolean hasPendingLines() {
        return pendingLines || buffer.size() > 0;
    }

}
