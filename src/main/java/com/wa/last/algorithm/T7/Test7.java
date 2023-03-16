package com.wa.last.algorithm.T7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 环形链表
 */
public class Test7 {

    public static void main(String[] args) {
//        ListNode listNode = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
        ListNode listNode = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(2))));
        //
        System.out.println(soulation2(listNode));
    }

    private static boolean soulation2(ListNode head) {

        if (head.next == null){
            return false;
        }
        //
        Set<Integer> hashSet = new HashSet<>();

        while (head != null ){

            int val = head.val;
            if (!hashSet.add(val)){
                return true;
            }
            head = head.next;
        }

        return false;
    }

    private static boolean soulation(ListNode head) {


        if (head.next == null){
            return false;
        }

        //key 为值  val  为下标
        Map<Integer, Integer> hashMap = new HashMap<>();
        //
        Set<Integer> hashSet = new HashSet<>();

        //开始下标,正向

        int pos = 0;

        //

        while (head != null ){

            int val = head.val;

            head = head.next;
            if (hashMap.containsKey(val)){
                //计算
                System.out.println(hashMap.get(val));
                return true;
            }else {
                hashMap.put(val, pos);
            }
            pos++;

        }

        return false;
    }


}
