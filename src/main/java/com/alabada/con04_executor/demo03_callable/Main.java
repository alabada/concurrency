package com.alabada.con04_executor.demo03_callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/19 9:37
 * @Description
 */
public class Main {

    public static void main(String[] args) {

        // 创建线程池，最多创建两个线程
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        // Future:这个接口声明了一些方法来获取由Callable产生的结果，并管理它们的状态。
        List<Future<Integer>> resultList = new ArrayList<Future<Integer>>();

        // Create a random number generator
        Random random = new Random();
        // Create and send to the executor the ten tasks
        for (int i = 0; i < 10; i++) {
            Integer number = new Integer(random.nextInt(10));
            // FactorialCalculator：Callable对象
            FactorialCalculator calculator = new FactorialCalculator(number);
            Future<Integer> result = executor.submit(calculator); // 通过submit发送一个Callable对象给执行器去执行，返回Future对象
            resultList.add(result);
            // Future对象可以用于以下两个主要目的：
            // 1、控制任务的状态：可以取消任务和检查任务是否已经完成。可使用isDone方法来检查任务是否已经完成。
            // 2、通过call方法获取返回的结果。可以使用get方法，该方法将一直等待直到Callable对象的call方法。
        }

        // Wait for the finalization of the ten tasks
        do {
            System.out.printf("Main: Number of Completed Tasks: %d\n", executor.getCompletedTaskCount());
            for (int i = 0; i < resultList.size(); i++) {
                Future<Integer> result = resultList.get(i);
                System.out.printf("Main: Task %d: %s\n", i, result.isDone());
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (executor.getCompletedTaskCount() < resultList.size());

        // Write the results
        System.out.printf("Main: Results\n");
        for (int i = 0; i < resultList.size(); i++) {
            Future<Integer> result = resultList.get(i);
            Integer number = null;
            try {
                number = result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.printf("Core: Task %d: %d\n", i, number);
        }

        // Shutdown the executor
        executor.shutdown();

    }
}
