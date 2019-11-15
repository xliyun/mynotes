package com.liyun.DesignPattern.decorator;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-16 14:37
 */
//具体的Decrator，这里就是调味品
public class Chocolate extends Decorator {
    public Chocolate(Drink obj) {
        super(obj);
        setDes(" 巧克力 ");
        setPrice(3.0f);// 调味品的价格
    }
}
