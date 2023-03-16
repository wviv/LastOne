package com.wa.last.algorithm3;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Java  内存模型
 */
public class Cat1 {

    public static void main(String[] args) throws Exception {

        Callable callable = () -> {

            System.out.println("ddd");

            return "gi";
        };

        FutureTask<String> futureTask = new FutureTask<>(() -> {

            System.out.println("ddd");

            return "gi";
        });

        System.out.println(callable.call());
//        futureTask.run();
//        System.out.println(futureTask.get());

    }
}
