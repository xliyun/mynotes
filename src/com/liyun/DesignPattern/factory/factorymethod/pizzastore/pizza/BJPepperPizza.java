package com.liyun.DesignPattern.factory.factorymethod.pizzastore.pizza;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-07 12:05
 */
public class BJPepperPizza extends Pizza{
    @Override
    public void prepare() {
        setName("北京胡椒披萨");
        System.out.println("北京的胡椒披萨 准备原材料");
    }
}
