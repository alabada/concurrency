package com.alabada.con03_assist.phaser;

import java.util.concurrent.Phaser;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/7 13:38
 * @Description
 */
public class Main {

    public static void main(String[] args) {

        // 创建一个Phaser对象，并指定参与阶段同步的线程是3个
        Phaser phaser = new Phaser(3);

        // 创建3个文件查找类线程对象
        FileSearch system = new FileSearch("C:\\Windows", "log", phaser);
        FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);
        FileSearch documents = new FileSearch("C:\\Documents And Settings", "log", phaser);

        Thread systemThread = new Thread(system, "System");
        systemThread.start();

        Thread appsThread = new Thread(apps, "Apps");
        appsThread.start();

        Thread documentsThread = new Thread(documents, "Documents");
        documentsThread.start();
        try {
            // 等待三个线程执行结束
            systemThread.join();
            appsThread.join();
            documentsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Terminated: %s\n", phaser.isTerminated()); // phaser的该方法可以获取phaser对象是否已终止

    }

}
