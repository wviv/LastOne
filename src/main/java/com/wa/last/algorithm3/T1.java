package com.wa.last.algorithm3;

/**
 * Java  内存模型
 */
public class T1 {

    public static volatile boolean flag = false;


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("Threa1");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
//        Thread.sleep(1000);
        thread1.join();
        Thread thread2 = new Thread(() -> {
            System.out.println("Threa2");
        });
        thread2.start();



    }

}
