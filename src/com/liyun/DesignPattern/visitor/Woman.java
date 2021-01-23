package com.liyun.DesignPattern.visitor;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-22 15:55
 */
//说明
//1.这里我们使用到了双分派，即首先在客户端程序中，将具体的状态作为参数，传递到Woman中（第一次分派）
//2.然后Woman 类调用作为参数的“具体方法” 中的方法，getWomanResult，同时将自己（this）作为参数
//传入，完成第二次的分派
public class Woman extends Person {
    public Woman(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.getWomanResult(this);
    }
}
