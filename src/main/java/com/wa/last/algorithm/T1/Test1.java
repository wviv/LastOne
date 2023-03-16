package com.wa.last.algorithm.T1;

/**
 * 回文数
 */
public class Test1 {

    public static void main(String[] args) {

        System.out.println(soulation2(12321));;

    }

    private static boolean soulation2(int x) {

        //小于0  or  个位数为0的 永不为回文数
        if (x <= 0  ||  x % 10 == 0){
            return false;
        }

        int reverse = 0;
        while (reverse < x){
            reverse = (reverse * 10 )+ (x % 10);
            x /=  10;
        }
        System.out.println(x);
        System.out.println(reverse);
        return reverse == x || (reverse / 10) == x;
    }

    private static boolean soulation(int x) {
        if (x < 0){
            return false;
        }
        String a = "" + x;
        StringBuffer reverse = new StringBuffer(a);
        reverse.reverse();

        System.out.println(a);
        System.out.println(reverse.toString());
//        System.out.println(reverse1);

        return a.equals(reverse.toString());
    }
}
