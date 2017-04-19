package com.alabada.con04_executor.demo03_callable;

import java.util.concurrent.Callable;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/19 9:21
 * @Description 实现Callable接口，泛型参数为Integer。因此这个Integer类型将作为在调用call（）方法时返回的类型
 */
public class FactorialCalculator implements Callable<Integer> {

    // 存储任务即将用来计算的数字
    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    /**
     * 业务逻辑 返回数的阶乘
     * @return
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        int num, result;

        num = number.intValue();
        result = 1;

        // If the number is 0 or 1, return the 1 value
        if ((num == 0) || (num == 1)) {
            result = 1;
        } else {
            // Else, calculate the factorial
            for (int i = 2; i <= number; i++) {
                result *= i;
                Thread.sleep(20);
            }
        }
        System.out.printf("%s: %d\n", Thread.currentThread().getName(), result);

        return result;
    }
}
