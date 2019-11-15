package com.liyun.learcn.Collection2;

import java.util.ArrayList;
import java.util.Collections;
/*
    java.utils.Collections是集合工具类，用来对集合进行操作。部分方法如下:
    public static <T> boolean addAll(Collection<T> c, T... elements):往集合中添加一些元素
    public static void shuffle(List<?> list) 打乱顺序:打乱集合顺序
    public static <T> void sort(list<T> list): 将集合元素按照默认规则排序
    public static <T> void sort(List<T> list,Comparator<? super T>): 将集合中元素安装指定规则排序
    注意:
        sort(List<T> list)使用前提
        被排序的集合里边存储的元素，必须实现Compareble,重写接口中的方法compareTo定义排序的规则

 */
public class Person implements Comparable<Person>{
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        //往集合中添加多个元素
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        Collections.addAll(list,"汪苏泷","周杰伦");
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);

        Collections.sort(list);

        ArrayList<Person> list2=new ArrayList<>();
        Person p1=new Person("周杰伦",22);
        Person p2=new Person("林俊杰",24);
        Person p3=new Person("李易峰",33);
        Collections.addAll(list2,p1,p2,p3);
        System.out.println();
    }

    public String toString(){
        return "姓名: "+this.name+" 年龄: "+this.age+"\n";
    }
    @Override
    public int compareTo(Person o) {
        //return 0;
        //自定义比较规则，比较两个人的年龄
        return this.getAge()-o.getAge();//按照年龄升序排序
    }
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}
}
