package com.liyun.learn.ArrayList;

import java.util.ArrayList;

/*
ArrayList当中的常用方法有
public boolean add(E e): 向集合当中添加元素，参数的类型和泛型一致，返回值表示是否成功。
备注：对于ArrayList集合来说，add添加动作一定是成功的，所以返回值可用可不用。
但是对于其他集合来说，add添加动作不一定成功
public E get(int index): 从集合当中获取元素，参数是索引编号，返回值就是对应位置的元素。
public E remove(int index): 从集合当中删除元素，参数是索引编号，返回值就是被删除掉的元素
public int size():       获取集合中包含的元素个数
 */
public class ArrayList_02 {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        System.out.println(list);
        //向集合中添加元素：add
        list.add("张三");
        list.add("李四");
        list.add("王五");
        System.out.println(list);

        System.out.println(list.get(2));

        String remove = list.remove(2);
        System.out.println("被删除的人是："+remove);
        System.out.println(list.size());

        //获取list.fori+Enter
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
