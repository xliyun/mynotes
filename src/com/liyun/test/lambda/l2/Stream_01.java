package com.liyun.test.lambda.l2;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-25 10:52
 */
public class Stream_01 {
    public static void main(String[] args) {

        List<Integer> nums= Arrays.asList(1,null,3,4,null,6);
        //创建stream   转换stream             对stream进行聚合
        nums.stream().filter(num->num!=null).count();

        //使用Stream静态方法来创建Stream
        Stream<Integer> integerStream=Stream.of(1,2,3,5);
        Stream<String> stringStream=Stream.of("baobao");

        //generator方法
        Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        });

        Stream.generate(()->Math.random());
        Stream.generate(Math::random);

        //iterate方法 初始值1 无线生长，直到第10个
        Stream.iterate(1,item->item+1).limit(10).forEach(System.out::println);


        //通过Collection子类获取Stream对象
        //Collection接口有一个stream方法，所以其所有子类都都可以获取对应的Stream对象。

/*        public interface Collection<E> extends Iterable<E> {
            //其他方法省略
            default Stream<E> stream() {
                return StreamSupport.stream(spliterator(), false);
            }
        }*/
    }
}
