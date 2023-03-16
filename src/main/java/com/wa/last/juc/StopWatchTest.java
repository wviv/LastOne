package com.wa.last.juc;

import org.springframework.util.StopWatch;

public class StopWatchTest {

    public static void main(String[] args) throws InterruptedException {

        StopWatch stopWatch = new StopWatch("1");
        stopWatch.start("任务一");

        Thread.sleep(1000);

        stopWatch.stop();


        System.out.println(stopWatch.currentTaskName());
        System.out.println(stopWatch.getLastTaskName());
        System.out.println(stopWatch.getLastTaskTimeMillis());


        stopWatch.start("任务二");

        System.out.println(stopWatch.currentTaskName());
        Thread.sleep(1000);

        stopWatch.stop();
        System.out.println(stopWatch.currentTaskName());
        System.out.println(stopWatch.getLastTaskName());
        System.out.println(stopWatch.prettyPrint());

    }

}
