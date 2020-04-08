package com.liyun.learn.Collection;

import java.util.ArrayList;
import java.util.Iterator;

/*
创建集合对象，使用泛型
好处：
    1.避免了类型转换的麻烦，存储的是什么类型，取出的就是什么类型
    2.把运行期向下转型的异常，提升到了编译期(写代码的时候就会报错了)
弊端：
    泛型是什么类型，只能存储什么类型的数据
 */
public class Generic_01 {
    public static void main(String[] args) {
        show1();
        }
    /*
     创建集合对象，不使用泛型
     好处:
         集合不使用泛型，默认的类型就是Object类型，可以储存任意类型
     弊端:
         不安全，会引发异常
     */
    private static void show1() {

        ArrayList list=new ArrayList();
        list.add("abc");
        list.add(1);
        //使用迭代器遍历list集合,集合没有类型，迭代器也是没有类型的
        Iterator it=list.iterator();
        while(it.hasNext()){
            //取出元素也是Object类型
            Object obj = it.next();
            System.out.println(obj);

            //想要使用String类特有的方法，length获取字符串的长度;
            //不能使用现在是多态Object obj="abc" 多态弊端，不能使用子类的方法
            //需要向下转型
            //会抛出ClassCastException类型转换异常，
            String s=(String)obj;
            System.out.println(s.length());
        }
    }
}
