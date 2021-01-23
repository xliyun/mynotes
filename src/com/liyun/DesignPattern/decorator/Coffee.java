package com.liyun.DesignPattern.decorator;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-16 14:17
 */
public class Coffee extends Drink {
    @Override
    public float cost() {
        return super.getPrice();
    }
}
