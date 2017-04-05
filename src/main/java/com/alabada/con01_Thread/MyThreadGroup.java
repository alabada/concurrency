package com.alabada.con01_Thread;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 18:58
 * @Description
 */
public class MyThreadGroup extends ThreadGroup {

    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("The thread %s has throw an Exception\n", t.getId());
        e.printStackTrace(System.out);
        System.out.printf("Terminating the rest of the Threads \n");
        interrupt();
    }

}
