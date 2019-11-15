package com.liyun.stream;

import java.util.*;
import java.util.stream.Stream;

/**
 * java.util.stream.Stream<T>是Java 8引入的最常用的接口。(这并不是一个函数式接口)
 * 获取一个流
 *      - 所有的Collection集合都可以通过stream默认方法获取流
 *          default Stream<E> stream()
 *      - Stream接口的静态方法of可以获取数组对应的流
 *          static  <T> Stream<T> of (T... value)
 *          参数是一个可变参数，我们就可以传递一个数组
 */
public class GetStream {
    public static void main(String[] args) {
        //把集合转换为stream流
        List<String> list=new ArrayList<>();
        Stream<String> stream1=list.stream();

        Set<String> set=new HashSet<>();
        Stream<String> stream2=set.stream();

        Map<String,String> map=new HashMap<>();
        Stream<String> keyStream=map.keySet().stream();
        Stream<String> valStream=map.values().stream();
        //获取键值对(键与值的映射关系 entrySet)
        Set<Map.Entry<String,String>> set2=map.entrySet();
        Stream<Map.Entry<String,String>> entryStream=set2.stream();

        //把数组转换为stream流
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        //可变参数可以传递数组
        Integer[] arr={1,2,3,4,5};
        Stream<Integer> integerStream2 = Stream.of(arr);

        String[] arr2={"a","b","c"};
        Stream<String> stringStream2 = Stream.of(arr2);
    }
}
