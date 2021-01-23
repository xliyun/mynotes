package com.liyun.test;

import java.util.Stack;

public class A_07_StaticTraceVector<T> {
    private Stack<T> stack1=new Stack<T>();
    private Stack<T> stack2=new Stack<T>();
    public void appendTail(T t){
        stack1.push(t);
    }
    public T deleteHead() throws Exception {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        if(stack2.isEmpty()){
            throw new Exception("队列为空，不能删除");
        }
        return stack2.pop();
    }

    public static void main(String[] args) throws Exception {
        A_07_StaticTraceVector<String> p7=new A_07_StaticTraceVector<>();
        p7.appendTail("a");
        p7.appendTail("b");
        p7.appendTail("c");
        p7.appendTail("d");
        System.out.println(p7.deleteHead());
    }
}
