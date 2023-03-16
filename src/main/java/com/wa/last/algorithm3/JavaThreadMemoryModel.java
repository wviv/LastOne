package com.wa.last.algorithm3;

/**
 * Java  内存模型
 */
public class JavaThreadMemoryModel {

    public static volatile boolean flag = false;


    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {

            while (!flag){
//                try {
//                    Thread.sleep(1000);
//                    System.out.println("变量没有变化");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }

            System.out.println("运行结束");

        }).start();

        Thread.sleep(2000);

        new Thread(() -> {

            flag = true;

            System.out.println("赋值结束");

        }).start();

    }

}
