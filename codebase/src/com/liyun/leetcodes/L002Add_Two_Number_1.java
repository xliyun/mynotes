package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-04-30 10:21
 */
public class L002Add_Two_Number_1 {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){val=x;}
    }

    public static void main(String[] args) {
            ListNode l1=new ListNode(1);
          //  l1.next=new ListNode(9);
          //  l1.next.next=new ListNode(9);

        ListNode l2=new ListNode(9);
        l2.next=new ListNode(9);
        //l2.next.next=new ListNode(4);

        System.out.println("========================");

        ListNode result=addTwoNumbers(l1,l2);
        while(result!=null){
            System.out.println(result.val);
            result=result.next;
        }
    }

    public static   ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(0);
        ListNode now=head;
        int a=0;
        int val=0;

        //如果两个链表有一个不为空
        while(l1!=null || l2!=null){

            //取出两个链表的值
            int left=l1==null?0:l1.val;
            int right=l2==null?0:l2.val;

            //当前位的值,不能少了a
            val=(left+right+a)%10;

            //进位的值,不能少了a
            a=(left+right+a)/10;

            now.val=val;

            //如果有链表走到头了，就取不到.next了
            l1=l1==null?null:l1.next;
            l2=l2==null?null:l2.next;

            if(l1!=null || l2!=null) {
                now.next = new ListNode(0);
                now=now.next;
            }
        }
        if(a!=0)
            now.next=new ListNode(a);
        return head;
    }
}
