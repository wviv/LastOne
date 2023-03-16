package com.wa.last.algorithm.T3;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转整数
 */
public class Test3 {

    public static void main(String[] args) {

//        System.out.println(soulation("III"));
        System.out.println(soulation("IV"));
        System.out.println(soulation("LVIII"));
        System.out.println(soulation("MCMXCIV"));

        System.out.println("====================");

//        System.out.println(soulation2("III"));
        System.out.println(soulation2("IV"));
        System.out.println(soulation2("LVIII"));
        System.out.println(soulation2("MCMXCIV"));
    }

    public static int soulation2(String s) {
        int a = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //如果当前数字小于后一位,说明是需要减掉的
            int a1 = getV(chars[i]);
            if (i < chars.length - 1 ){
                int a2 = getV(chars[i + 1]);
                if (a1 < a2){
                    a -= a1;
                    continue;
                }
            }
            a += a1;

        }
        return a;
    }

    private static int getV(char c) {
        switch (c){
            case 'I' :
                return 1;
            case 'V' :
                return 5;
            case 'X' :
                return 10;
            case 'L' :
                return 50;
            case 'C' :
                return 100;
            case 'D' :
                return 500;
            case 'M' :
                return 1000;
            default:
                return 0;
        }
    }


    public static int soulation(String s) {
        Map<String,Integer> map = new HashMap<>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);

        map.put("IV",4);
        map.put("IX",9);
        map.put("XL",40);
        map.put("XC",90);
        map.put("CD",400);
        map.put("CM",900);


        int a = 0;

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            String d1 = Character.toString(chars[i]);
            String d2 ;
            //最后一位
            if (i < chars.length - 1 && map.containsKey(d2 = Character.toString(chars[i] ) + chars[i + 1])){
                int integer = map.get(d2);
                if (integer != 0){
                    a += integer;
                    i++;
                    continue;
                }
            }
            int integer = map.get(d1);
            a += integer;

        }
        return a;
    }
}
