package com.liyun.DesignPattern.factory.factorymethod.pizzastore.order;

import com.liyun.DesignPattern.factory.factorymethod.pizzastore.pizza.BJCheesePizza;
import com.liyun.DesignPattern.factory.factorymethod.pizzastore.pizza.BJPepperPizza;
import com.liyun.DesignPattern.factory.factorymethod.pizzastore.pizza.Pizza;


/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-07 12:14
 */
    public class BJOrderPizza extends OrderPizza {
        @Override
        Pizza createPizza(String orderType) {
            Pizza pizza=null;
            if(orderType.equals("cheese")){
                pizza = new BJCheesePizza();
            }else if(orderType.equals("pepper")){
                pizza=new BJPepperPizza();
            }
            return pizza;
        }
    }
