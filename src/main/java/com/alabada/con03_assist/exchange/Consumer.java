package com.alabada.con03_assist.exchange;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/7 15:03
 * @Description
 */
public class Consumer implements Runnable {

    // 定义buffer，用于数据狡猾
    private List<String> buffer;

    private final Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle = 1;

        for (int i = 0; i < 10; i++) {
            System.out.printf("Consumer: Cycle %d\n", cycle);

            try {
                /*
				 * 调用exchange()方法与生产者进行数据交换。
				 */
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("Consumer: %d\n", buffer.size());

            for (int j = 0; j < 10; j++) {
                String message = buffer.get(0);
                System.out.printf("Consumer: %s\n", message);
                buffer.remove(0);
            }

            cycle++;
        }

    }

}
