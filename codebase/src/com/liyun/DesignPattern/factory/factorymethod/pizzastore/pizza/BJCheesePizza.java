package com.liyun.DesignPattern.factory.factorymethod.pizzastore.pizza;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-07 12:04
 */
public class BJCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("北京奶酪披萨");
        System.out.println("北京的奶酪披萨 准备原材料");
    }
}
