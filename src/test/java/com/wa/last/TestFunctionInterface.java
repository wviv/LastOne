package com.wa.last;

/**
 * 
 * @date 2019/9/3 16:34
 */
@FunctionalInterface
public interface TestFunctionInterface {

    String toString() ;
    String toStringa2() ;
//    String toStringa22(String a) ;
    default String toStringa223(String a) {
        return a;
    };
    static String toStringa224(){
        return "b";
    }
}
