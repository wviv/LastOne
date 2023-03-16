package com.wa.last.mvc.guide.hashCode;

import org.springframework.transaction.annotation.Transactional;

public class Test1 {


    @Transactional
    public static void main(String[] args) {

        String s = new String();
        s.hashCode();
        System.out.println( 2 << 4);
    }

}
