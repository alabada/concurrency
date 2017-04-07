package com.alabada.con02_synch.synch1;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/5 23:26
 * @Description
 */
public class Cinema {

    private long vacanciesCinema1;

    private long vacanciesCinema2;

    /**
     * 这两个属性单纯就是用来做锁用的
     * 通过这种方式带来的优点：
     * 可以通过不同的对象去控制不同的临界区；
     * 这样就能够提高一些性能，因为锁不一样了。
     *
     * 对于不同的资源使用不同的锁
     */
    private final Object controlCinema1, controlCinema2;

    public Cinema() {
        controlCinema1 = new Object();
        controlCinema2 = new Object();
        vacanciesCinema1 = 20;
        vacanciesCinema2 = 20;
    }

    public boolean sellTickets1(int number) {
        /**
         * 窗口1卖票使用controlCinema1锁
         */
        synchronized (controlCinema1) {
            if (number < vacanciesCinema1) {
                vacanciesCinema1 -= number;
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean sellTickets2(int number) {
        /**
         * 窗口2卖票使用controlCinema2锁
         */
        synchronized (controlCinema2) {
            if (number < vacanciesCinema2) {
                vacanciesCinema2 -= number;
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean returnTickets1(int number) {
        /**
         * 窗口1退票使用controlCinema1锁
         */
        synchronized (controlCinema1) { // 使用synchronized来保护代码块，以一个对象作为其参数
            vacanciesCinema1 += number;
            return true;
        }
    }

    public boolean returnTickets2(int number) {
        /**
         * 窗口2退票使用controlCinema2锁
         */
        synchronized (controlCinema2) {
            vacanciesCinema2 += number;
            return true;
        }
    }

    public long getVacanciesCinema1() {
        return vacanciesCinema1;
    }

    public long getVacanciesCinema2() {
        return vacanciesCinema2;
    }

}
