package com.liyun.DesignPattern.factory.factorymethod.pizzastore.order;



import com.liyun.DesignPattern.factory.factorymethod.pizzastore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-06 16:59
 */
public abstract class OrderPizza {


    //构造器
    public OrderPizza(){
        Pizza pizza=null;
        String ordertype;//订购披萨类型
        do{
            ordertype=getType();
            //让工厂子类去实现如何创建披萨
            pizza=createPizza(ordertype);//抽象方法

            if (pizza==null) {
                break;
            }

            //输出pizza制作过程
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }while(true);
    }

    //定义一个抽象方法，createPizza，让各个工厂子类自己实现
    abstract Pizza createPizza(String orderType);


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
