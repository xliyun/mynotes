package com.liyun.lambda.stream;

import java.util.stream.Stream;

/**
 * Stream流中的常用方法_filter:用于对Stream流中的数据进行过滤
 * Stream<T> filter(Predicate<? super T> predicate);
 * filter方法的参数Predicate是一个函数式接口，所以可以传递Lambda表达式，对数据进行过滤
 * Predicate中的抽象方法:
 *      boolean test(T t);
 */
public class Stream_Filter {
    public static void main(String[] args) {
        //创建一个Stream流
        Stream<String> stream = Stream.of("刘亦菲", "古力娜扎", "岳云鹏", "沈腾");
        //对Stream流中的元素进行过滤,
       Stream<String> stream2 = stream.filter((String name)->{ return name.startsWith("刘");});
       stream2.forEach(name-> System.out.println(name));
    }
}
