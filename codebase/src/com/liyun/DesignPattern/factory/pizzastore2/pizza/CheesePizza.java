package com.liyun.DesignPattern.factory.pizzastore2.pizza;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-06 16:56
 */
public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println(" 准备制作奶酪披萨的原材料...");
    }
}
