package com.wa.last.algorithm.T5;

/**
 * 合并两个有序链表
 */
public class Test5 {


    public static void main(String[] args) {

        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        ListNode listNode = mergeTwoLists(list1, list2);
        System.out.println(1);;


    }

    //判断l1和l2的哪个头结点小,然后较小的节点next指向其余节点和合并结果(递归)
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null ){
            return list2;
        }else if (list2 == null ){
            return list1;
        }else if (list1.val < list2.val){
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
