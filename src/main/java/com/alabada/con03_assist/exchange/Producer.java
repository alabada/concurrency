package com.alabada.con03_assist.exchange;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/7 14:54
 * @Description
 */
public class Producer implements Runnable {

    // 声明共享buffer
    private List<String> buffer;

    // Exchange型对象用于同步生产者和消费者交换对象
    private final Exchanger<List<String>> exchanger;

    public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle = 1;

        for (int i = 0; i < 10; i++) { // 实现10次数据交换
            System.out.printf("Producer: Cycle %d\n", cycle);

            for (int j = 0; j < 10; j++) {
                String message = "Event " + ((i * 10) + j);
                System.out.printf("Producer: %s\n", message);
                buffer.add(message);
            }

            try {
                /*
				 * 调用exchange()方法与消费者进行数据交换。
				 */
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("Producer: %d\n", buffer.size());

            cycle++;
        }

    }


}
