package com.wa.last;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author echidna
 * @date 2019/9/9 16:13
 */
@Slf4j
public class AtomicIntegerTest extends BaseServiceTest {

    @Test
    public void test() {

        AtomicInteger atomicInteger = new AtomicInteger();

        int get = atomicInteger.get();
        //返回并自增
        int andIncrement = atomicInteger.getAndIncrement();
        log.info("get:" + get + ",andIncrement:" + andIncrement);
        //自增之后
        log.info("getAndIncrement:" + atomicInteger.get());
        //不保证立刻对其他线程可见
        atomicInteger.lazySet(1);
        atomicInteger.set(2);
        log.info("get:" + atomicInteger.get());
        //返回被覆盖的
        log.info("getAndSet:" + atomicInteger.getAndSet(3));
        log.info("getAndSet-later:" + atomicInteger.get());
        //CAS有3个操作数，内存值V，旧的预期值A，要修改的新值B。当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则什么都不做
        log.info("compareAndSet" + atomicInteger.compareAndSet(3, 4));
        log.info("" + atomicInteger.get());
        log.info(""+atomicInteger.getAndDecrement());
        log.info(""+atomicInteger.get());
        log.info(""+atomicInteger.getAndAdd(2));
        log.info(""+atomicInteger.get());
        //自增并返回
        log.info(""+atomicInteger.incrementAndGet());
        //自减并返回
        log.info(""+atomicInteger.decrementAndGet());
        //增加并返回
        log.info(""+atomicInteger.addAndGet(2));
    }
    @Test
    public  void test2() throws NoSuchFieldException, IllegalAccessException {
        Class<?>[] declaredClasses = Integer.class.getDeclaredClasses();
        Class cache = Integer.class.getDeclaredClasses()[0];
        Field c = cache.getDeclaredField("cache");
        c.setAccessible(true);
        Integer[] array = (Integer[]) c.get(cache);
// array[129] is 1
        array[130] = array[129];
// Set 2 to be 1
        array[131] = array[129];
// Set 3 to be 1
        Integer a = 1;
        if(a == (Integer)1 && a == (Integer)2 && a == (Integer)3){
            System.out.println("Success");
        }
    }
}
