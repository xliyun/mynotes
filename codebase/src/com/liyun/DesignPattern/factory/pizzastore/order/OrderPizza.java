package com.liyun.DesignPattern.factory.pizzastore.order;

import com.liyun.DesignPattern.factory.pizzastore.pizza.CheesePizza;
import com.liyun.DesignPattern.factory.pizzastore.pizza.GreekPizza;
import com.liyun.DesignPattern.factory.pizzastore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-06 16:59
 */
public class OrderPizza {

    //构造器
    public OrderPizza(){
        Pizza pizza=null;
        String ordertype;//订购披萨类型
        do{
            ordertype=getType();
            if (ordertype.equals("greek")) {
                pizza = new GreekPizza();
                pizza.setName("希腊披萨");
            } else if (ordertype.equals("cheese")) {
                pizza = new CheesePizza();
                pizza.setName("奶酪披萨");
            }else {
                break;
            }
            //输出pizza制作过程
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }while(true);
    }

    //写一个方法，可以获取客户希望订购的披萨种类
    private String getType() {

        try {
            BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("输入披萨种类：");
            String str=strin.readLine();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
