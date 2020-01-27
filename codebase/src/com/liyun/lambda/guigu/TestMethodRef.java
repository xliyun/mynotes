package com.liyun.lambda.guigu;

import com.liyun.learcn.Static.Student;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
 *          （可以理解为方法引用是Lambda表达式的另外一种表现形式）
 *  主要有三种语格式：
 *  (注意：Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！)
 *  对象::实例方法名
 *
 *  类::静态方法名
 *
 *  类::实例方法名（）
 */
public class TestMethodRef {

    //对象::实例方法
    public void test1(){
        //前提:要实现的接口中的抽象方法的参数列表和返回类型要和lambda体中（也就是ps1.pringln(String x)）的方法的参数列表和返回类型要一致

        PrintStream ps1 = System.out;
        Consumer<String> con= (x)-> ps1.println(x);

       PrintStream ps= System.out;
       Consumer<String> con1 = ps::println;

       Consumer<String> con2 = System.out::println;
       con2.accept("abcdef");
    }

    public void test2(){
        Student student=new Student();
        student.setName("娃哈哈");
        Supplier<String> sup = ()->student.getName();
        String s = sup.get();
        System.out.println(s);

        Supplier<String> sp2= student::getName;
        String s1 = sp2.get();
        System.out.println(s1);
    }

    //类::静态方法名
    public void test3(){
        Comparator<Integer> com = (x,y) ->Integer.compare(x,y);

        Comparator<Integer> com1= Integer::compareTo;

    }

    //类::实例方法名
    public void test4(){
        BiPredicate<String,String> bp = (x,y)->x.equals(y);

        //如果两个参数，x是equals方法的调用者，第二个参数是被调用的方法的参数时，就可以使用类名::方法名
        //而不需要使用对象名::方法名
        BiPredicate<String,String> bp2 = String::equals;
    }
}
