package com.alabada.con04_executor.demo01;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/18 23:35
 * @Description
 */
public class Server {

    // 声明属性
    private ThreadPoolExecutor executor;

    // 实现构造器
    public Server() {
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    /**
     * This method is called when a request to the server is made. The
     * server uses the executor to execute the request that it receives
     *
     * @param task The request made to the server
     */
    public void executeTask(Task task) {
        System.out.printf("Server: A new task has arrived\n");

        executor.execute(task);

        System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());
        System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
        System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
    }

    /**
     * This method shuts down the executor
     */
    public void endServer() {
        executor.shutdown();
    }

}
