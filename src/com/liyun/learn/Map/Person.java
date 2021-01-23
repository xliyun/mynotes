package com.liyun.learn.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/*
HashMap存储自定义类型键值
Map集合保证key是唯一的:
    作为key的元素，必须冲洗hashCode方法和equals方法，以保证key唯一
 */
public class Person {
    public static void main(String[] args) {
        show01();
        show02();
    }

    /*
       HashMap存储定义类型键值
       key:Person类型
           Person类必须重写hashCode方法和equals方法
       value:String类型
     */
    private static void show02() {
        HashMap<Person,String> hashMap=new HashMap<>();
        //往集合中添加元素
        hashMap.put(new Person("剑姬",29),"艾欧尼亚");
        hashMap.put(new Person("伊泽瑞尔",24),"德玛西亚");
        hashMap.put(new Person("沙漠皇帝",28),"恕瑞玛");
        hashMap.put(new Person("剑姬",29),"艾欧尼亚-艾欧尼亚");
        //使用entrySet和增强for遍历Map集合
        Set<Map.Entry<Person,String>> set=hashMap.entrySet();
        for(Map.Entry<Person,String> entry : set){
            Person key=entry.getKey();
            String value=entry.getValue();
            System.out.println("key--->"+key+" "+"value--->"+value);
        }

    }

    /*
    HashMap存储自定义类型键值
    key:String类型
        String类型重写hashCode()方法和equals方法，可以保证key唯一
    value:Person类型
        value可以重复(同名同年龄的人视为同一个人)
     */
    private static void show01() {
        HashMap<String,Person> hashMap=new HashMap<>();
        hashMap.put("北京",new Person("张小凡",28));
        hashMap.put("上海",new Person("金克丝",25));
        hashMap.put("广州",new Person("拉克丝",20));
        hashMap.put("深圳",new Person("石头人",1000));
        Set<String> set=hashMap.keySet();
        for (String key : set) {
            Person value=hashMap.get(key);
            System.out.println(key+"===="+value);
        }
    }

    private String name;
    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public Person() {}

    public Person(String name, int age) {this.name = name;this.age = age;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
