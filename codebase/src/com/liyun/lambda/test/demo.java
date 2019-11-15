package com.liyun.lambda.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-06-21 13:42
 */
public class demo {
    public static void main(String[] args) {
        //创建线程
        Thread td=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"运行了！！");
            }
        });
        td.start();

        Thread td2=new Thread(()-> System.out.println(Thread.currentThread().getName()+"运行了==="));
        td2.start();

        //排序
        List<String> list= Arrays.asList(new String[]{"a","b","c","d"});
        //匿名类写法
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        Collections.sort(list,(o1,o2)->o1.compareTo(o2));


        //单参数语法
        List<String> proNames=Arrays.asList(new String[]{"a","b","c"});
        List<String> lowercaseNames1=proNames.stream().map(name->{return name.toLowerCase();}).collect(Collectors.toList());

        //单语句写法
        List<String> proNames2=Arrays.asList(new String[]{"1","2","3"});
        List<String> loercaseNames2=proNames2.stream().map(name->name.toLowerCase()).collect(Collectors.toList());

        //方法引用写法
        List<String> proNames3=Arrays.asList("A","B","C");
        List<String> lowercaseNames3=proNames3.stream().map(String::toLowerCase).collect(Collectors.toList());
    }
}
