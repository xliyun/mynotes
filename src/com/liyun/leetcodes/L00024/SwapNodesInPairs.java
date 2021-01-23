package com.liyun.leetcodes.L00024;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-16 16:42
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next ==null){
            return head;
        }
        ListNode head1 = new ListNode();
        head1.next = head;
        ListNode result = head.next;
//        ListNode L1= head1.next;
//        ListNode L2= head1.next;
//        while(head!=null&&head.next!=null){
//            ListNode L1= head;
//            ListNode L2= head.next;
//            ListNode L3= head.next.next;
//
//            L1.next = L3;
//            L2.next = L1;
//            head=L3;
//        }
        while(head1!=null && head1.next!=null && head1.next.next!=null){
            ListNode L1= head1;
            ListNode L2= head1.next;
            ListNode L3= head1.next.next;
            head1 = L2;
            head1.next = L3.next;
            L3.next = L2;
            L1.next = L3;


        }
        return result;
    }

}
