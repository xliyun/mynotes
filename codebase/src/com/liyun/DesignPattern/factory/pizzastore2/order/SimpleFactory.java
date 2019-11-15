package com.liyun.DesignPattern.factory.pizzastore2.order;

import com.liyun.DesignPattern.factory.pizzastore2.pizza.GreekPizza;
import com.liyun.DesignPattern.factory.pizzastore2.pizza.Pizza;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-07 9:53
 */
//简单工厂类
public class SimpleFactory {
    //根据orderType返回对应的Pizza对象
    public Pizza createPizza(String orderType){

        Pizza pizza=null;

        System.out.println("使用简单工厂模式");
        if(orderType.equals("greek")){
            pizza=new GreekPizza();
            pizza.setName("希腊披萨");
        }else if(orderType.equals("cheese")){
            pizza=new GreekPizza();
            pizza.setName("奶酪披萨");
        }
        return pizza;
    }

    //简单工厂模式也叫静态工厂模式
    public static Pizza createPizza2(String orderType){

        Pizza pizza=null;

        System.out.println("使用简单工厂模式");
        if(orderType.equals("greek")){
            pizza=new GreekPizza();
            pizza.setName("希腊披萨");
        }else if(orderType.equals("cheese")){
            pizza=new GreekPizza();
            pizza.setName("奶酪披萨");
        }
        return pizza;
    }
}
