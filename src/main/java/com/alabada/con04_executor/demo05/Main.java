package com.alabada.con04_executor.demo05;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zhida.wen on 2017/4/19.
 */
public class Main {

    public static void main(String[] args) {

        // Create an executor
        ExecutorService executor=(ExecutorService) Executors.newCachedThreadPool();

        // Create three tasks and stores them in a List
        List<Task> taskList=new ArrayList<Task>();
        for (int i=0; i<3; i++){
            Task task=new Task("Task-"+i);
            taskList.add(task);
        }

        // Call the invokeAll() method
        List<Future<Result>> resultList=null;
        try {
            // 通过invokeAll（）方法等待所有任务的完成。
            resultList=executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Finish the executor
        executor.shutdown();

        // Writes the results to the console
        System.out.printf("Core: Printing the results\n");
        for (int i=0; i<resultList.size(); i++){
            Future<Result> future=resultList.get(i);
            try {
                Result result=future.get();
                System.out.printf("%s: %s\n",result.getName(),result.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
