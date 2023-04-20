package com.wa.last.algorithm.T6;

import com.wa.last.algorithm.T5.ListNode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * 反转链表
 */
public class Test6 {

    public static void main(String[] args) {

        Claims claims = Jwts.parser()
//                .setSigningKey(publicKey)
                .parseClaimsJws("authorization").getBody();
        String clientId = claims.get("client_id", String.class);

        System.out.println(clientId);

        ListNode listNode = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
        ListNode listNode1 = reverseList(listNode);
        System.out.println(1);
    }

    public static ListNode reverseList(ListNode listNode){
        ListNode  head = null;
        ListNode  next = listNode;
        while (next != null){
            //先将要修改的值取出来,再覆盖,然后赋值
            ListNode next1 = next.next;
            next.next = head;
            head = next;
            //自己包含自己 ❎
//            head.next = head;
            next = next1;
        }
        return head;

    }


    public static void reverseList2(ListNode listNode){
        listNode.val = 4;
    }
}
