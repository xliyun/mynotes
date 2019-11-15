package com.liyun.learcn.Collection2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/*
    java.utils.Collections是集合工具类，用来对集合进行操作。部分方法如下:
        public static <T> void sort(List<T> list,Comparator<? super T>）:将集合中元素按照指定规则排序。

    Comparator和Comparable的区别
        Comparable:自己(this）和别人(参数)比较，自己需要实现Comparable接口，重写比较的规则compareTo方法
        Comparator:相当于找一个第三方的裁判比较两个人
            o1-o2升序
 */
public class Student {
    public static void main(String[] args) {
        ArrayList<Student> list=new ArrayList<>();
        list.add(new Student("a迪丽热巴",19));
        list.add(new Student("古力娜扎",20));
        list.add(new Student("马尔扎哈",100));
        list.add(new Student("b马尔扎哈",19));
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                //按照年龄升序排序
                int result=o1.getAge()-o2.getAge();
                //如果年龄一样，按照姓名排
                if(result==0){
                    //result=o1.getName().charAt(0)-o2.getName().charAt(0)
                    if(o1.getName().charAt(0)>o2.getName().charAt(0))
                        result=1;
                }
                return result;
            }
        });
        System.out.println(list);
    }
    private String name;
    private int age;

    public Student() {}

    public Student(String name, int age) {this.name = name;this.age = age;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
