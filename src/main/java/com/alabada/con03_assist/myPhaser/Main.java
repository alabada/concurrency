package com.alabada.con03_assist.myPhaser;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/7 14:36
 * @Description
 */
public class Main {
    public static void main(String[] args) {

        // 创建自己的phaser
        MyPhaser phaser = new MyPhaser();

        // 5个学生对象
        Student students[] = new Student[5];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(phaser);
            phaser.register(); // 注册
        }

        // Create 5 threads for the students and start them
        Thread threads[] = new Thread[students.length];
        for (int i = 0; i < students.length; i++) {
            threads[i] = new Thread(students[i], "Student " + i);
            threads[i].start();
        }

        // Wait for the finalization of the threads
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Check that the Phaser is in the Terminated state
        System.out.printf("Main: The phaser has finished: %s.\n", phaser.isTerminated());

    }
}
