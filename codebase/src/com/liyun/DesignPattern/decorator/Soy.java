package com.liyun.DesignPattern.decorator;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-16 14:42
 */
public class Soy extends Decorator {
    public Soy(Drink obj) {
        super(obj);
        setDes(" 豆浆 ");
        setPrice(1.5f);
    }
}
