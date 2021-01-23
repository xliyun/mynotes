package com.liyun.learn.ArrayList;

import java.util.ArrayList;

/*
如果希望向集合ArrayList当中存储级别类型数据，必须使用基本类型对应的“包装类”
基本类型 包装类(引用类型，包装类都位于java.lang包下，不需要导包)
byte short int long float double char(Character) boolean

从JDK 1.5+开始，支持自动装箱、自动拆箱
6.fori
 */
public class ArrayList_03 {
    public static void main(String[] args) {
        ArrayList<Integer> listC=new ArrayList<>();
        listC.add(100);
        listC.add(200);
        System.out.println(listC);
    }
}
