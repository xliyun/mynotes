package com.liyun.lambda.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *  long count(): 用于统计Stream中元素的个数
 *  count方法是一个终结方法，返回值是一个long类型的整数
 *  所以不能再继续调用stream流中的其他方法了
 */
public class Stream_Count {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Stream<Integer> stream = list.stream();
        long count = stream.count();
        System.out.println("有"+count+"个元素");
    }
}
