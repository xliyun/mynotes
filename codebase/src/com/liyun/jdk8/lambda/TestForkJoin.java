package com.liyun.jdk8.lambda;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-01-28 22:57
 */
public class TestForkJoin {

    //ForkJoin 框架
    public void test1(){
        //java8新特性
        Instant start = Instant.now();
        ForkJoinPool pool=new ForkJoinPool();
        ForkJoinTask<Long> task =new ForkJoinClculate(0,100000000L);

        Long invoke = pool.invoke(task);
        Instant end = Instant.now();
        //获取纳秒
        System.out.println("耗费的时间为："+Duration.between(start,end).getNano());

    }

    //普通for循环
    public void test2(){
        Instant start = Instant.now();
        long sum=0L;
        for(int i=0;i<=1000000000L;i++){
            sum+=i;
        }
        System.out.println(sum);
        Instant end=Instant.now();

        System.out.println("耗费的时间为："+Duration.between(start,end).getNano());
    }
/*
    java8 并行流
            */

    public void test3(){
        Instant start = Instant.now();
        long sum = LongStream.rangeClosed(0, 100000000000L)
                .parallel()//将顺序流变成并行流
                .reduce(0, Long::sum);

        Instant end = Instant.now();
        System.out.println(sum);
        System.out.println("耗费的时间为："+Duration.between(start,end).getNano());
    }
}
