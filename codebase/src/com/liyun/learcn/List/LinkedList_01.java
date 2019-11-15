package com.liyun.learcn.List;

import java.util.LinkedList;

/*
java.util.LinkedList集合 implements List接口
LinkedList集合的特点:
    1.底层是一个链表结构:查询慢，增删快
    2.里边包含了大量操作首尾元素的方法
    注意:使用LinkedList集合特有的方法，不能使用多态，多态看不到子类特有的方法
         没有元素时会报NoSuchElementException异常
    public void addFirst(E e):将指定元素插入此列表的开头
    public void addLast(E e): 将指定元素添加到此列表的结尾
    public void push(E e): 将元素推入此列表所表示的堆栈=addLast()


    public E getFirst(): 返回此列表的第一个元素
    public E getLast(): 返回此列表的最后一个元素

    public E removeFirst(): 移除并返回此列表的第一个元素
    public E removeLast(): 移除并返回此列表的最后一个元素
    public E pop(): 从列表所表示的堆栈处弹出一个元素=removeLast()

    public boolean isEmpty(): 如果列表不包含元素，返回true
 */
public class LinkedList_01 {
    public static void main(String[] args) {
        //添加元素
        show01();
        //获取元素
        show02();
        //移除元素
        show03();
    }

    private static void show03() {
        LinkedList<String> linkedList3=new LinkedList<>();
        linkedList3.add("aa");
        linkedList3.add("bb");
        linkedList3.add("cc");
        linkedList3.add("dd");
        System.out.println(linkedList3);
        String first = linkedList3.removeFirst();
        System.out.println("被移除的第一个元素"+first);
        String last = linkedList3.removeLast();
        System.out.println("被移除的最后一个元素"+last);
        System.out.println(linkedList3);
    }

    private static void show02() {
        LinkedList<String> linkedList2=new LinkedList<>();
        linkedList2.add("1");
        linkedList2.addLast("2");
        linkedList2.add("3");

        linkedList2.clear();

        //public boolean isEmpty()如果列表 不包含元素，则返回true
        if(!linkedList2.isEmpty()){
            String first=linkedList2.getFirst();
            System.out.println(first);
            String last=linkedList2.getLast();
            System.out.println(last);
        }

    }

    private static void show01() {
        //创建LinkedList集合对象
        LinkedList<String> linked=new LinkedList<>();
        linked.add("a");
        linked.add("b");
        linked.add("c");
        System.out.println(linked);

        //push等效于addFirst
        linked.push("push");
        linked.addFirst("addFirst");
        System.out.println(linked);

        //addLast等效于add
        linked.addLast("addlast");
        linked.add("add");
    }
}
