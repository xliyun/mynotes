package com.liyun.sword;

import com.liyun.test.A_05_ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-05-07 10:35
 */
public class SW_05 {
    public class ListNode{
        ListNode next=null;
        int val;
        ListNode(int val){
            this.val=val;
        }
    }
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode){
        ArrayList<Integer> list=new ArrayList<>();
        if(listNode == null)
            return list;
        Stack<ListNode> stack=new Stack<>();
        while(listNode!=null){
            stack.push(listNode);
            listNode=listNode.next;
        }
        while(!stack.isEmpty()){
            list.add(stack.pop().val);
        }
        return list;
    }
}
