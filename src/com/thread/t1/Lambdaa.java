package com.thread.t1;

import java.util.Arrays;
import java.util.List;

public class Lambdaa {
    public static void main(String[] args) {
        List<Integer> values= Arrays.asList(10,20,30,40);
        int res=Lambdaa.add(values);
        System.out.println("计算结果为: "+res);

        //并行执行
        Lambdaa.parallerladd(values);
        //串行执行
        Lambdaa.serialadd(values);
    }

    public static int add(List<Integer> values){
        //parallelStream并行流
        return values.parallelStream().mapToInt(a-> a).sum();
    }
    public static int parallerladd(List<Integer> values){
        values.parallelStream().forEach(System.out::println);
        return 0;
    }
    public static int serialadd(List<Integer> values){
        values.stream().forEach(System.out::println);
        return 0;
    }
}
