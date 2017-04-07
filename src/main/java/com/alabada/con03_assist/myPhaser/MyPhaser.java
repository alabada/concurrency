package com.alabada.con03_assist.myPhaser;

import java.util.concurrent.Phaser;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/7 14:15
 * @Description
 */
public class MyPhaser extends Phaser {

    /**
     * 该方法在phaser阶段改变的时候会被自动执行。
     * 需要两个int型的传入参数：当前的阶段数以及注册的参与者数量。
     * 返回的是boolean值，返回false表示phaser在继续执行，返回true表示phaser已经完成执行并且进入了终止态。
     */
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case 0:
                return studentsArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }

    /**
     * This method is called in the change from phase 0 to phase 1
     *
     */
    private boolean studentsArrived() {
        System.out.printf("Phaser: The exam are going to start. The students are ready.\n");
        System.out.printf("Phaser: We have %d students.\n", getRegisteredParties());
        return false; // 返回false表明phaser已经开始执行
    }

    /**
     * This method is called in the change from phase 1 to phase 2
     *
     */
    private boolean finishFirstExercise() {
        System.out.printf("Phaser: All the students has finished the first exercise.\n");
        System.out.printf("Phaser: It's turn for the second one.\n");
        return false; // 返回false表明phaser在继续执行中
    }

    /**
     * This method is called in the change form phase 2 to phase 3
     *
     */
    private boolean finishSecondExercise() {
        System.out.printf("Phaser: All the students has finished the second exercise.\n");
        System.out.printf("Phaser: It's turn for the third one.\n");
        return false; // 返回false表明phaser已经开始执行
    }

    /**
     * This method is called in the change from phase 3 to phase 4
     *
     */
    private boolean finishExam() {
        System.out.printf("Phaser: All the students has finished the exam.\n");
        System.out.printf("Phaser: Thank you for your time.\n");
        return true; // 返回true表明phaser已经完成了。
    }

}
