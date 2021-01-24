package com.liyun.test.lambda.l2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-25 13:10
 */
public class Stream_02 {
    public static void main(String[] args) {
        //1.distinct:对于Stream中包含的元素进行去重操作（去重逻辑依赖元素equals方法），新生成的Stream中没有重复的元素


        //2.filter:对于Stream中包含的元素使用给定的过滤函数进行过滤操作，新生成的Stream只包含复符合条件的元素

        //3.map:对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素。
        //这个方法有三个对于原始类型的变种方法，分别是：mapToInt,mapToLong和mapToDouble。可以避免自动装箱/拆箱的额外消耗

        //4.flatMap：和map类似，不同的是其每个元素转换得到的是Stream对象，会把子Stream中的元素压缩到父集合中
        Stream<List<Integer>> inputStream=Stream.of(
                Arrays.asList(1),
                Arrays.asList(2,3),
                Arrays.asList(4,5,6)
        );
        Stream<Integer> outputStream=inputStream.flatMap((childList)->childList.stream());
        List<Integer> integerList=outputStream.map((name)->{
            System.out.println(name);
            return name;
        }).collect(Collectors.toList());

        //5.peek：生成一个包含原Stream的所有元素的新Stream,同时会提供一个消费函数（Consumer实例），新Stream每个元素被消费的时候都会执行给定的消费函数

        //6.limit：对一个Stream进行截断操作，获取其前N个元素，如果原Stream中包含的元素个数小于N，那就是获取其所有的元素

        //7.skip：返回一个丢弃原Stream的前N个元素后剩下元素组成的Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream
        System.out.println("==================================================");
        List<Integer> nums=Arrays.asList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        System.out.println("打印数组："+nums.stream().filter(num->num!=null).distinct().mapToInt(num->num*2).peek(System.out::println).skip(2).limit(4).sum());
    }
}
