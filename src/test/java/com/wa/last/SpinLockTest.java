package com.wa.last;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SpinLockTest {

    private volatile boolean flag = true;

    @Test
    public void main() {

        new Thread(() -> {
            try {
                execu();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.error(Thread.currentThread().getName() + "  执行完毕");
        }, "线程1").start();

        new Thread(() -> {
            try {
                execu();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.error(Thread.currentThread().getName() + "  执行完毕");
        }, "线程2").start();
        new Thread(() -> {
            try {
                execu();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.error(Thread.currentThread().getName() + "  执行完毕");
        }, "线程3").start();
    }

    public boolean execu() throws InterruptedException {

        for (; ; ) {
            if (flag) {
                synchronized (this) {
                    if (flag){
                        try {
                            flag = false;
                            log.error(Thread.currentThread().getName() + "  拿到锁");
                            Thread.sleep(1000);
                            return true;
                        } finally {
                            flag = true;
                        }
                    }
                }
            }
            log.error(Thread.currentThread().getName() + "   自旋自旋");
        }
    }
}
