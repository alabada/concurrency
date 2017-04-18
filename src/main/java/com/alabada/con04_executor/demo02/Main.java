package com.alabada.con04_executor.demo02;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/18 23:36
 * @Description
 */
public class Main {

    public static void main(String[] args) {
        // Create the server
        Server server = new Server();

        // Send 100 request to the server and finish
        for (int i = 0; i < 100; i++) {
            Task task = new Task("Task " + i);
            server.executeTask(task);
        }

        server.endServer();

    }
}
