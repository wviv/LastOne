package com.wa.last.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchT {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch ready = new CountDownLatch(1);
        CountDownLatch person = new CountDownLatch(6);

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 1; i < 7; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "运动员已就位");
                    ready.await();
                    System.out.println(Thread.currentThread().getName() + "出发");
                    person.countDown();
                    System.out.println(Thread.currentThread().getName() + "到达终点");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            });
        }

        ready.countDown();

        person.await();

        System.out.println("比赛结束");


    }

}
