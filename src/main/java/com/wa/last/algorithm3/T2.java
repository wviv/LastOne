package com.wa.last.algorithm3;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * Java  内存模型
 */
public class T2 {

    public static volatile boolean flag = false;


    public static void main(String[] args) throws InterruptedException {

//        int availableProcessors = Runtime.getRuntime().availableProcessors();
//        System.out.println(availableProcessors);

        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.addAndGet(1));;


        LongAdder longAdder = new LongAdder();
        longAdder.add(1L);
//          cells初始化过了，进去条件 || 能否直接cas，如果不能则是发生竞争则进入条件，如果可以直接修改，证明没有竞争，直接返回。
//        if ((as = cells) != null || !casBase(b = base, b + x)) {
//        }

    }

}
