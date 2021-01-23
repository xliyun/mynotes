package com.liyun.DesignPattern.factory.absfactory.pizzastore.pizza;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-07 12:07
 */
public class LDCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦奶酪披萨");
        System.out.println("伦敦的奶酪披萨 准备原材料");
    }
}
