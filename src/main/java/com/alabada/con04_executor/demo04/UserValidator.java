package com.alabada.con04_executor.demo04;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/19 11:20
 * @Description 
 */
public class UserValidator {

    /**
     * The name of the validation system
     */
    private String name;

    public UserValidator(String name) {
        this.name=name;
    }

    /**
     * Method that validates a user
     * @param name Name of the user
     * @param password Password of the user
     * @return true if the user is validated and false if not
     */
    public boolean validate(String name, String password) {
        Random random=new Random();

        // Sleep the thread during a random period of time
        try {
            Long duration=(long)(Math.random()*10);
            System.out.printf("Validator %s: Validating a user during %d seconds\n",this.name,duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            return false;
        }

        // Return a random boolean value
        return random.nextBoolean();
    }

    /**
     * Return the name of the validation system
     * @return The name of the validation system
     */
    public String getName(){
        return name;
    }
}
