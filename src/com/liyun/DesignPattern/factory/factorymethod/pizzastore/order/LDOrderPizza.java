package com.liyun.DesignPattern.factory.factorymethod.pizzastore.order;


import com.liyun.DesignPattern.factory.factorymethod.pizzastore.pizza.*;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-07 12:14
 */
public class LDOrderPizza extends OrderPizza {
    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza=null;
        if(orderType.equals("cheese")){
            pizza = new LDCheesePizza();
        }else if(orderType.equals("pepper")){
            pizza=new LDPepperPizza();
        }
        return pizza;
    }
}
