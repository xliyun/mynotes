package com.liyun.DesignPattern.factory.absfactory.pizzastore.order;


import com.liyun.DesignPattern.factory.absfactory.pizzastore.pizza.BJCheesePizza;
import com.liyun.DesignPattern.factory.absfactory.pizzastore.pizza.BJPepperPizza;
import com.liyun.DesignPattern.factory.absfactory.pizzastore.pizza.Pizza;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-07 13:31
 */
//这是一个工厂子类
public class BJFacotry implements AbsFactory {
    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("--使用的是抽象工厂模式--");
        Pizza pizza=null;
        if(orderType.equals("cheese")){
            pizza=new BJCheesePizza();
        }else if(orderType.equals("pepper")){
            pizza=new BJPepperPizza();
        }
        return pizza;
    }
}
