package com.liyun.leetcodes.L00024;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-16 16:41
 */
public class L00024 {

    public static void main(String[] args) {
        SwapNodesInPairs swapNodesInPairs =new SwapNodesInPairs();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
        ListNode listNode = swapNodesInPairs.swapPairs(n1);
        while(listNode!=null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
