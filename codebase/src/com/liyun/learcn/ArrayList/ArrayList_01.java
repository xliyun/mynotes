package com.liyun.learcn.ArrayList;

import java.util.ArrayList;

/*
定义一个数组，用来储存3个Person对象。
数组有一个缺点：一旦创建，程序运行期间长度不可以发生改变。
但是ArrayList<E>集合的长度是可以随意变化的。
泛型：也就是装在集合当中的所有元素，全部都是统一的E类型
注意：泛型只能是引用类型，不能是基本类型,因为集合里保存的是地址值
直接打印ArrayList，不是地址值，是内容

 */
public class ArrayList_01 {
    public static void main(String[] args) {
        Person[] arrays=new Person[3];
        arrays[0]=new Person("张胜男");
        System.out.println(arrays[0].getName());

        //从jdk1.7开始，右侧尖括号内部可以不写内容，尖括号还是要写上的
        ArrayList<String> list=new ArrayList<>();
        list.add("李四");

    }
}
