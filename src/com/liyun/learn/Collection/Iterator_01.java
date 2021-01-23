package com.liyun.learn.Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
java.Util.Iterator接口:迭代器(对集合进行遍历)
常用方法:
    public E next(): 返回迭代的下一个元素
    public boolean hasNext(): 如果还有元素可以迭代，则返回true

    Iterator迭代器，是一个接口，我们无法直接使用，需要使用Intertor接口的实现类对象
    Collection接口中有一个方法，叫Iterator(),这个方法返回的就是迭代器的实现类对象。

迭代器的使用步骤:
    1.使用集合中的方法iterator()获取迭代器的实现类对象，使用Ierator对象接收(多态)
    2.使用Iterator接口中的方法hasNext()判断还有没有下一个元素
    3.使用Iterator接口中的方法next取出集合中的下一个元素
 */
public class Iterator_01 {
    public static void main(String[] args) {
        Collection<String> coll=new ArrayList<>();
        coll.add("龙族");
        coll.add("盗墓笔记");
        coll.add("鬼吹灯");
        coll.add("诛仙");
        coll.add("人狼国度");
        coll.add("紫川");

        //Iterator<E>接口也是有泛型的，迭代器的泛型跟着集合走
        //多态  接口               实现类对象
        Iterator<String> iterator=coll.iterator();
        //如果没有元素再取出元素会抛出NoSuchElementException没有元素异常
        while(iterator.hasNext())
            System.out.println(iterator.next());

        //for循环模式 了解就行了
        for(Iterator<String> it2=coll.iterator();it2.hasNext();){
            String str=it2.next();
            System.out.println(str);
        }
    }
}
