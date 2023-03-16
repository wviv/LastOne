package com.wa.last.exercise;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Generics<String, Integer> integerGenerics = new Generics<>();
        integerGenerics.set(1);
        System.out.println(integerGenerics.get("1"));;

        Generics2<Person> generics2 = new Generics2<>();
        List<Animal> persons = new ArrayList<>();
        persons.add(new Man());
        generics2.a(persons);
    }
}
