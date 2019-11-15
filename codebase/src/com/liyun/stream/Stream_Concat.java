package com.liyun.stream;

import java.util.stream.Stream;

/**
 *  concat:用于把流组合到一起
 *  如果 有两个流，希望合并成一个流，那么可以使用stream接口的静态方法concat
 */
public class Stream_Concat {
    public static void main(String[] args) {
        String[] arr={"张三","李四","王五","赵六","田七"};
        Stream<String> stream1 = Stream.of(arr);

        Stream<String> stream2 = Stream.of("刘亦菲", "刘诗诗", "古力娜扎");
        //把以上两个流合并为一个流
        Stream<String> concat = Stream.concat(stream1, stream2);
        concat.forEach((name)->{
            System.out.println(name);
        });
    }
}
