package com.liyun.learcn.annotation;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 注解的使用
 * 1.理解Annotation:
 *  jdk 5.0 新增的功能
 *
 *  2.Annotation的使用示例
 *  示例一：生成文档相关的注解
 *  示例二：在编译时进行格式检查（JDK内置的三个基本注解）
 * @Overrid:限定重写父类方法，改注解只能用于方法
 * @Deprecated:用于表示所修饰的元素（类，方法等）已过时。通常是因为所修饰的结构危险或存在
 * @SuppressWrnings:抑制编译器警告
 *
 * 示例三：跟踪代码依赖性，实现替代配置文件的功能
 * 3.如何自定义注解参照@SuppressWrnings
 *   注解声明为@infterface
 *   内部定义成员，通常使用表示
 *   可以指定成员的默认值，使用default定义
 *   如果定义的注解没有成员，表明是一个标识作用。
 *   自定义注解通常会指明两个元注解：Retention、Target
 *
 * 4.JDK提供的四种元注解
 *      元注解：对现有注解进行解释
 *      Retention:指定所修饰的Annotation的生命周期：SOURCE、CLASS(默认)、RUNTIME
 *          只有声明为RUNTIME生命周期的注解，才能通过反射获取。
 *      Target:用于指定被修饰的Annotation能用于修饰哪些程序元素
 *      Document:表示所修饰的注解在被javadoc解析时，保存下来
 *      Inherited:被它修饰的Annotation将具有继承性
 *
 *  5.通过反射获取注解信息
 *
 *  6.jdk 8 中注解的新特性
 *  6.1 可重复注解
 *      a.在MyAnnotation上声明@Repatable,成员值为MyAnnotation.class
 *      b.MyAnnotation的target和Retention和MyAnnotations相同
 *
 *  6.2 类型注解
 *  就是元注解@Target注解里面ElementType枚举值多了两个
 *  ElementType.TYPE_PARAMETER 表示该注解能写在类型变量的声明语句中（如：泛型声明）
 *  ElementType.TYPE_USE 表示该注解能写在使用类型的任何语句中
 *
 */
public class AnnotationTest {
    public static void main(String[] args) {
        Person p= new Student();
        p.walk();

        Date date = new Date(2020,10,11);
        System.out.println(date);

        @SuppressWarnings({"unused"})
        int num = 10;
        @SuppressWarnings({"unused","rawtypes"})
        List list=new ArrayList();
    }
    @Test
    public void testGetAnnotation(){
        Class<Student> studentClass = Student.class;
        Annotation[] annotations = studentClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}

