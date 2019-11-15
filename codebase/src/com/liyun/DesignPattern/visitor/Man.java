package com.liyun.DesignPattern.visitor;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-22 15:55
 */
public class Man extends Person {
    public Man(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.getManResult(this);
    }
}
