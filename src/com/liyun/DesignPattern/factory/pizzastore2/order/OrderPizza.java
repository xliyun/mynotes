package com.liyun.DesignPattern.factory.pizzastore2.order;

import com.liyun.DesignPattern.factory.pizzastore2.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-06 16:59
 */
public class OrderPizza {


    //定义一个简单工厂对象
    SimpleFactory simpleFactory;

    Pizza pizza=null;

    //构造器
    public OrderPizza(SimpleFactory simpleFactory){
        setFactory(simpleFactory);
    }

    private void setFactory(SimpleFactory simpleFactory){
        String orderType="";//用户输入的

        this.simpleFactory=simpleFactory;//设置简单工厂对象

        do{
            orderType=getType();
            Pizza pizza = this.simpleFactory.createPizza(orderType);

            //输出披萨信息
            if(pizza!=null){//订购成功
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            }else{
                System.out.println("没有这种披萨。");
                break;
            }
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
