package com.alabada.con02_synch.synch;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 22:44
 * @Description
 */
public class Bank implements Runnable {

    private Account account;

    public Bank(Account account) {
        this.account=account;
    }

    @Override
    public void run() {
        for (int i=0; i<100; i++){
            account.subtractAmount(1000);
        }
    }
}
