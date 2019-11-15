package com.liyun.DesignPattern.factory.factorymethod.pizzastore.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-07 12:36
 */
public class PizzaStore {
    public static void main(String[] args) {
        String address=null;
        System.out.println("请输入披萨店地址：beijing或者lundun");
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        try {
            address = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(address.equals("beijing")) {
            //创建北京口味的各种Pizza
            new BJOrderPizza();
        }else if(address.equals("lundun")) {
            //创建伦敦口味的Pizza
            new LDOrderPizza();
        }
    }
}
