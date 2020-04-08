package com.liyun.learn.Collection;

import java.util.ArrayList;
import java.util.Collection;

/*java.util.Collection接口所有单列集合的最顶层的接口里边定义了所有单列集合的共性方法
任意的单列集合都可以使用Collection接口中的方法

共性方法:
public boolean add(E e): 把给定的对象添加到当前集合中。
public void clear(): 清空集合中所有的元素。
public boolean remove(E e): 把给定的对象在当期集合中删除
public boolean contaiins(E e): 判断当前集合中是否包含给定的对象。
public boolean isEmpty(): 判断当期集合是否为空
public int size(): 返回集合中元素的个数
public Object[] toArray: 把集合中的数组，存储到数组中
 */
public class Collection_01 {
    public static void main(String[] args) {
        //创建集合对象 add方法返回的是一个boolean值，一般返回true,所以可以不用接收
        Collection<String> coll=new ArrayList<>();
        coll.add("张三");
        coll.add("李四");
        coll.add("王五");
        System.out.println(coll);

        //返回值是一个boolean值，集合中存在元素，删除元素，返回true
        //集合中不存在元素，删除失败返回false
        coll.remove("李四");
        System.out.println(coll);

        boolean b4=coll.contains("王五");
        System.out.println("是否包含王五: "+b4);

        System.out.println("是否为空："+coll.isEmpty());
        System.out.println("长度："+coll.size());

        Object[] arr=coll.toArray();
        for(int i=0;i<arr.length;i++)
            System.out.println(arr[i]);

        coll.clear();
        System.out.println(coll);
    }
}
