package com.alabada.con04_executor.demo05;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/19 13:40
 * @Description
 */
public class Result {

    /**
     * The name of the task that generates the result
     */

    private String name;
    /**
     * The value of the task that generates the result
     */
    private int value;

    /**
     * Returns the name of the task
     *
     * @return Name of the task that generates the result
     */
    public String getName() {
        return name;
    }

    /**
     * Establish the name of the task
     *
     * @param name The name of the task that generates the result
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the value of the result
     *
     * @return The value of the result
     */
    public int getValue() {
        return value;
    }

    /**
     * Establish the value of the result
     *
     * @param value The value of the result
     */
    public void setValue(int value) {
        this.value = value;
    }
}
