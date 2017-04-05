package com.alabada.con02_synch;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 22:46
 * @Description
 */
public class Company implements Runnable {

    private Account account;

    public Company(Account account) {
        this.account=account;
    }

    @Override
    public void run() {
        for (int i=0; i<100; i++){
            account.addAmount(1000);
        }
    }
}
