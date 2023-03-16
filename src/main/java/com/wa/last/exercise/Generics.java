package com.wa.last.exercise;

/**
 * 泛型
 */
public class Generics<T,V> {

    private V v;

    public void set(V v){
        this.v = v;
    }

    public V  get(T t){
        return v;
    }
}
