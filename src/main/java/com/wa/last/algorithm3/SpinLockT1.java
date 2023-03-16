package com.wa.last.algorithm3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpinLockT1 {

    public static void main(String[] args) {

        T1 t1 = new T1();
        t1.main();
    }

    static class T1 {

        private volatile boolean flag = true;


        public void main() {

            new Thread(() -> {
                try {
                    execu();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info(Thread.currentThread().getName() + "  执行完毕");
            }, "线程1").start();

            new Thread(() -> {
                try {
                    execu();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info(Thread.currentThread().getName() + "  执行完毕");
            }, "线程2").start();
            new Thread(() -> {
                try {
                    execu();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info(Thread.currentThread().getName() + "  执行完毕");
            }, "线程3").start();

        }

        public boolean execu() throws InterruptedException {

            for (; ; ) {
                if (flag) {
                    synchronized (this) {
                        if (flag){
                            try {
                                flag = false;
                                log.info(Thread.currentThread().getName() + "  拿到锁");
                                Thread.sleep(1000);
                                return true;
                            } finally {
                                flag = true;
                            }
                        }
                    }
                }
                Thread.sleep(200);
                log.info(Thread.currentThread().getName() + "   自旋自旋");
            }
        }
    }


}
