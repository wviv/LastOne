package com.wa.last.concurrent.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args) {

        Tick d1 = new Tick();

        new Thread(d1,"线程A").start();


        new Thread(d1,"线程B").start();

    }
}
