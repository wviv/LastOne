package com.wa.last.java2.reflect2.T1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test1 {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        Class<?> aClass = Class.forName("com.wa.last.java2.reflect2.T1.TestOb");

        Object o = aClass.newInstance();
        Constructor<?> constructor = aClass.getConstructor(String.class, int.class);
        constructor.setAccessible(true);
//        Object o1 = constructor.newInstance("1", 1);
        Method dd = aClass.getDeclaredMethod("dd");
        dd.invoke(o);

        Method dd2 = aClass.getDeclaredMethod("toString");
        Annotation[] annotations = dd2.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());
        }

        TestOb testOb = new TestOb("1", 60);
        Class<? extends TestOb> aClass2 = testOb.getClass();
        Field b = aClass2.getDeclaredField("b");
        b.setAccessible(true);
        Object o1 = b.get(aClass2);
        System.out.println(o1);
    }
}
