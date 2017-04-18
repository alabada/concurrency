package com.alabada.con04_executor.demo02;

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
        // 创建一个缓存线程池
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
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

        System.out.printf("Server: Pool Size: %d\n",executor.getPoolSize());
        System.out.printf("Server: Active Count: %d\n",executor.getActiveCount());
        System.out.printf("Server: Task Count: %d\n",executor.getTaskCount());
        System.out.printf("Server: Completed Tasks: %d\n",executor.getCompletedTaskCount());
    }

    /**
     * ThreadPoolExecutor类的一个重要特性是，需要显示的去结束他。
     * 如果不这样做，那么执行器将继续执行，程序也不会结束。它会继续等待新任务的到来
     */
    public void endServer() {
        executor.shutdown();
    }

}
