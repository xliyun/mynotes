package com.liyun.sword;

import java.util.Stack;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-05-07 11:00
 */
public class SW_07 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
       return stack2.pop();
    }
}
