package com.wa.last.exercise;

import java.util.List;

/**
 * 泛型
 */
public class Generics2<T>{

    public void a (List<? super T> ob){
        ob.forEach(p -> System.out.println(p.toString()));
    }
}
