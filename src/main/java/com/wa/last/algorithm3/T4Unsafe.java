package com.wa.last.algorithm3;

import lombok.Synchronized;
import sun.misc.Unsafe;

/**
 * Java  内存模型
 */
public class T4Unsafe {

    public static volatile boolean flag = false;
//    public static final Dict dict = new Dict();
    public static final Unsafe unsafe = Unsafe.getUnsafe();



    public static void main(String[] args) throws InterruptedException {


//        System.out.println(dict);
//
//        dict.setPid("1");
//        dict.setA(System.currentTimeMillis());
//        dict.setCode("wang");
//        dict.setName("王");
//
//        System.out.println(dict);
    }

    @Synchronized
    public void t1(){

    }

}
