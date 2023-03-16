package com.wa.last.concurrent.ReentrantLock;

public class Tick implements Runnable{

    private int num = 5;

    @Override
    public void run() {
        while (true){
            if (num < 1){
                return;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : " + num--);
        }
    }

    public static void main(String[] args) {
        Tick tick = new Tick();

        new Thread(tick,"线程A").start();


        new Thread(tick,"线程B").start();
    }
}
