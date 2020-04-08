package com.liyun.learn.Integer;

import java.util.ArrayList;

/*
 自动装箱与自动拆箱：基本类型的 数据和包装类 之间可以自动的相互转换
 JDK1.5之后出现的新特性
 */
public class Integer_02 {
    public static void main(String[] args) {
        //自动装箱
        Integer in=1;
        System.out.println(in.intValue());
        //自动拆箱:in 是包装类，无法直接参与运算，可以自动转换为基本数据再进行计算
        //in+2就相当于 in.intvalue()+2 再装箱为Integer
        in=in+2;

        //ArrayList集合无法直接存储整数，可以存储Integer包装类
        ArrayList<Integer> list=new ArrayList<>();
        list.add(1);//-->自动装箱 list.add(new Integer(1));
        int a=list.get(0);//-->自动拆箱 list.get(0).intValue();
    }
}
