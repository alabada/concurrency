package com.alabada.con04_executor.demo04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/19 11:53
 * @Description
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // Initialize the parameters of the user
        String username = "test";
        String password = "test";

        // Create two user validation objects
        UserValidator ldapValidator = new UserValidator("LDAP");
        UserValidator dbValidator = new UserValidator("DataBase");

        // Create two tasks for the user validation objects
        TaskValidator ldapTask = new TaskValidator(ldapValidator, username, password);
        TaskValidator dbTask = new TaskValidator(dbValidator, username, password);

        // Add the two tasks to a list of tasks
        List<TaskValidator> taskList = new ArrayList<>();
        taskList.add(ldapTask);
        taskList.add(dbTask);

        // Create a new Executor
        ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
        String result;
        try {
            // ThreadPoolExecutor类的invokeAny（）方法接收到一个任务列表，然后运行任务，并返回第一个完成任务并且没有抛出异常的任务的执行结果。
            result = executor.invokeAny(taskList);

            System.out.printf("Main: Result: %s\n", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // Shutdown the Executor
        executor.shutdown();
        System.out.printf("Main: End of the Execution\n");
    }
}
