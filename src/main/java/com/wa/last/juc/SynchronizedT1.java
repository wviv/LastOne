package com.wa.last.juc;

/**
 * synchronized 测试1
 */
public class SynchronizedT1 {

    public static Integer count = 0;
    public static final Boolean b = true;

    public void add() {
        synchronized (b) {
            count++;
        }

//        这里是错误的，锁了等于没错
//        synchronized (count) {
//            count++;
//        }
    }

    public static void main(String[] args) throws InterruptedException {

        SynchronizedT1 synchronizedT1 = new SynchronizedT1();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronizedT1.add();
            }
        });


        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronizedT1.add();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);


    }

}
