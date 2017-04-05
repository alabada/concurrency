package com.alabada.con01_Thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 15:14
 * @Description
 */
public class NetworkConnctionLoader implements Runnable {

    public void run() {
        System.out.printf("Begining network connections loading: %s\n",new Date());
        // Sleep six seconds
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Network connections loading has finished: %s\n",new Date());
    }

}
