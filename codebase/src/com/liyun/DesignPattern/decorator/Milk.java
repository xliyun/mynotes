package com.liyun.DesignPattern.decorator;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-16 14:38
 */
public class Milk extends Decorator{
    public Milk(Drink obj) {
        super(obj);
        setDes(" 牛奶 ");
        setPrice(2.0f);
    }
}
