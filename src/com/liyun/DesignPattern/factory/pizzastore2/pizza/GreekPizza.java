package com.liyun.DesignPattern.factory.pizzastore2.pizza;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-06 16:57
 */
public class GreekPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println(" 准备制作希腊披萨的原材料...");
    }
}
