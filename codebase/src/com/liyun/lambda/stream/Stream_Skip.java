package com.liyun.lambda.stream;

import java.util.stream.Stream;

/**
 * skip方法:用于跳过元素
 * 如果希望跳过前几个元素，可以使用skip方法获取一个截取之后的新流:
 * Stream<T> skip(long n);
 *      如果流的当前长度大于n,则跳过前n个；否则将会得到一个长度为0的空流
 */
public class Stream_Skip {
    public static void main(String[] args) {
        String[] arr={"张三","李四","王五","赵六","田七"};
        Stream<String> stream = Stream.of(arr);

        Stream<String> stream2 = stream.skip(3);
        stream2.forEach((name)->{
            System.out.println(name);
        });
    }
}
