package com.liyun.lambda.guigu;

import com.liyun.learcn.Static.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream 的三个步骤
 * 1.创建Stream
 * 2.中间操作
 * 3.终止操作（终端操作）
 */
public class TestStreamAPI {

    //1.创建Stream
    public void test1(){
        //1.可以通过Collection 系列集合提供的Stream() 或parallelStream
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过Arrays中的静态方法 stream()获取数组流
        Student[] stus = new Student[10];
        Stream<Student> stream2 = Arrays.stream(stus);

        //3.通过Stream类中的 静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //4.创建无限流
        //迭代 参数1：起始值，参数2：一元运算，往下迭代的方式
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.forEach((t)->{System.out.println(t);} );//这里需要传入一个消费型接口
        stream4.forEach(System.out::println);
    }
}
