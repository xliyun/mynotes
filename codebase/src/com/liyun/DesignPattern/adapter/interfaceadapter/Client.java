package com.liyun.DesignPattern.adapter.interfaceadapter;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-15 11:34
 */
public class Client {
    public static void main(String[] args) {
        AbsAdapter absAdapter=new AbsAdapter(){
            //只需要覆盖我们需要使用的方法
            @Override
            public void m1(){
                System.out.println("使用了m1的方法");
            }
        };

        absAdapter.m1();
    }
}
