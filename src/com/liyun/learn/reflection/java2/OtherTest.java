package com.liyun.learn.reflection.java2;

import com.liyun.learn.reflection.java1.Person;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 获取构造器结构
 */
public class OtherTest {

    @Test
    public void test1(){
        Class clazz = Person.class;

        //getConstructors(): 当前运行时类中声明为public的构造器（不包含父类）
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor c : constructors) {
            System.out.println(c);
        }

        //getDeclaredConstructors(): 获取当前运行时类中声明的所有构造器（不包含父类）
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor c : declaredConstructors) {
            System.out.println(c);
        }

    }

    /*
    获取运行时类的父类
     */
    @Test
    public void test2(){
        Class clazz = Person.class;

        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);
    }

    /*
获取运行时类的带泛型的父类
 */
    @Test
    public void test3(){
        Class clazz = Person.class;

        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);
    }

    /*
获取运行时类的父类的泛型
 */
    @Test
    public void test4(){
        Class clazz = Person.class;

        Type genericSuperclass = clazz.getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        //获取泛型类型
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        for (Type t : actualTypeArguments) {
            System.out.println(t.getTypeName());
            System.out.println(((Class)t).getName());//type也是Class
        }
    }

    /*
    获取运行时类实现的接口

     */
    @Test
    public void test5(){

        Class clazz = Person.class;

        Class[] interfaces = clazz.getInterfaces();
        for (Class c : interfaces) {
            System.out.println(c);
        }

        Class[] interfaces1 = clazz.getSuperclass().getInterfaces();
        for (Class aClass : interfaces1) {
            System.out.println(aClass);
        }
    }

    /*
    获取运行时类所在的包
     */
    @Test
    public void test6(){
        Class clazz = Person.class;
        Package pack = clazz.getPackage();
        System.out.println(pack);
    }

    /*
获取运行时类声明的注解
 */
    @Test
    public void test7(){
        Class clazz = Person.class;
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}
