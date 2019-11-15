package com.liyun.stream;

import java.util.stream.Stream;

/**
 * Stream流中常用方法 forEach
 * void forEach(Consumer<? super T> action);
 * 该方法接收一个Consumer接口函数，会将每一个流元素交给该函数进行处理。
 * Consumer接口是一个消费型函数式接口，可以传递Lambda表达式，消费数据
 *
 * 简单记:
 *      forEach方法，用来遍历流中的数据
 *      是一个终结方法，遍历之后就不能继续调用Stream流中的其他方法
 */
public class Stream_forEach {
    public static void main(String[] args) {
        //获取Stream流
        Stream<String> stream = Stream.of("张三", "李四", "王五", "赵六", "田七");
        //使用Stream流中的方法forEach对Stream流中的数据进行遍历
      /*
      Stream流属于管道流，只能消费一次
      第一个Stream流调用完毕方法，数据就会流转到下一个Stream上，而这时第一个Stream流已经使用完毕，就会关闭了，所以第一个Stream流就不能在调用方法了
      比如stream.filter().forEach(),
      每一步都返回一个stream流，运行到forEach()之后，filter()的流就关闭了
      */

/*        stream.forEach((String name)->{
            System.out.println(name);
        });*/

        //优化 去掉数据类型括号，只剩下参数，就是forEach需要的参数
        stream.forEach(name->System.out.println(name));
    }
}
