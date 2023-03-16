package com.wa.last.juc;

import java.util.concurrent.*;

public class ThreadPoolT1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<>(10));
        Future<String> submit1 = executor.submit(() -> "222");
        String s1 = submit1.get();
        System.out.println(s1);

        Future<String> submit2 = executor.submit(() -> System.out.println(Thread.currentThread().getName()),"111");
        String s2 = submit2.get();
        System.out.println(s2);





        executor.shutdown();
    }
}
