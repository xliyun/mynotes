package com.liyun.DesignPattern.factory.absfactory.pizzastore.order;


import com.liyun.DesignPattern.factory.absfactory.pizzastore.pizza.LDCheesePizza;
import com.liyun.DesignPattern.factory.absfactory.pizzastore.pizza.LDPepperPizza;
import com.liyun.DesignPattern.factory.absfactory.pizzastore.pizza.Pizza;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-07 13:33
 */
public class LDFacotry implements AbsFactory {
    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("--使用的是抽象工厂模式--");
        Pizza pizza=null;
        if(orderType.equals("cheese")){
            pizza=new LDCheesePizza();
        }else if(orderType.equals("pepper")){
            pizza=new LDPepperPizza();
        }
        return pizza;
    }
}
