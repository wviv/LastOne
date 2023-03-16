package com.wa.last.juc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.*;


/**
 * 多线程测试一
 */
@Slf4j
public class ThreadPoolT2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, NoSuchAlgorithmException {

        StopWatch stopWatch = new StopWatch();

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        ThreadPoolExecutor multipartSearch = new ThreadPoolExecutor(3, 9, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10), r -> {
            Thread thread = new Thread(r);
            thread.setName("多步搜索");
            return thread;
        });

        stopWatch.start();

        Future<String> task1 = multipartSearch.submit(() -> {
            String tName = "任务一";
            int i = random.nextInt(3000);
            log.info("{}执行时间 : {}", tName, i);
            Thread.sleep(i);
            return tName;
        });

        Future<String> task2 = multipartSearch.submit(() -> {
            String tName = "任务二";
            int i = random.nextInt(3000);
            log.info("{}执行时间 : {}", tName, i);
            Thread.sleep(i);
            return tName;
        });

        Future<String> task3 = multipartSearch.submit(() -> {
            String tName = "任务三";
            int i = random.nextInt(3000);
            log.info("{}执行时间 : {}", tName, i);
            Thread.sleep(i);
            return tName;
        });

        for (; ; ) {
            if (task1.isDone()) {
                log.info("任务一执行完毕!");
                break;
            }
            if (task2.isDone()) {
                log.info("任务二执行完毕!");
                break;
            }
            if (task3.isDone()) {
                log.info("任务三执行完毕!");
                break;
            }
        }


        multipartSearch.shutdownNow();

        stopWatch.stop();

        log.info("" + stopWatch.getLastTaskTimeMillis());
    }
}
