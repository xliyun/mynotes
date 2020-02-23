package com.liyun.learcn.annotation;

import java.util.ArrayList;

/**
 *
 */
//可重复注解
@MyAnnotation(value = "hi")
@MyAnnotation(value = "hi")
//@MyAnntations({@MyAnnotation(value = "hi"),@MyAnnotation(value = "ha")})
public class Person {
    private String name;
    private int age;

    public Person(){

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void walk(){
        System.out.println("人走路");
    }
}

class Student extends Person{
    public void walk(){
        System.out.println("学生走路");
    }
}

class Generic<@MyAnnotation T>{

    public void show() throws @MyAnnotation RuntimeException{
        ArrayList<@MyAnnotation  String> list = new ArrayList<>();
        int num = (@MyAnnotation  int) 10L;

    }
}