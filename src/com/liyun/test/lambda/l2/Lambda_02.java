package com.liyun.test.lambda.l2;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-25 9:56
 */
public class Lambda_02 {
    public static void main(String[] args) {
        List<String> list= Arrays.asList(new String[]{"b","c","a"});

        List<Integer> integers=Arrays.asList(3,5,4,2);
        //匿名类写法
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
              return  o1.compareTo(o2);
            }
        });

        //lambda写法
        Collections.sort(list,(o1,o2)->o1.compareTo(o2));


        List<String> proNames=Arrays.asList(new String[]{"Ni","Hao","A"});

        List<String> lowercaseNames=new ArrayList<>();

        for (String proName : proNames) {
            lowercaseNames.add(proName.toLowerCase());
        }

        //单参数写法
        List<String> lowercaseNames2=proNames.stream().map((name)->{return name.toLowerCase();}).collect(Collectors.toList());

        //省略 return 和 ; 写法
        List<String> lowercaseNames3=proNames.stream().map((name)-> name.toLowerCase()).collect(Collectors.toList());

        //省略 return 和 ; 和 () 写法
        List<String> lowercaseNames4=proNames.stream().map(name-> name.toLowerCase()).collect(Collectors.toList());

        //==================================================================================================================




        //this概念
        //lambda表达式中this不是指向lambda表达式产生的SAM对象，而是声明它的外部对象
        new Lambda_02().whatThis();

        //方法引用写法
        //前两种把lambda表达式的参数直接当成instanceMethod|staticMethod的参数来用
        //objectName::instanceMethod
        proNames.forEach(System.out::println);

        //ClassName::staticMethod
        Collections.sort(integers,Math::max);

        //ClassName::instanceMethod 最后一种方法等同于把lambda表达式的第一个参数当成instanceMethod的目标对象，其他剩余参数当成该方法的参数
        List<String> lowercaseNames5=proNames.stream().map(String::toLowerCase).collect(Collectors.toList());


        //List<String> lowercaseNames6=proNames.stream().map(System.out::println).collect(Collectors.toList());

        //构造器引用 CLassName::new
        List<String> lowercaseNames6=proNames.stream().map(String::new).collect(Collectors.toList());

    }

    public void whatThis(){
        List<String> proNames=Arrays.asList(new String[]{"Ni","Hao","A"});
        //this指的是外面的类
        List<String> execStrs=proNames.stream().map(str->{
            System.out.println(this.getClass().getName());
            return str.toLowerCase();
        }).collect(Collectors.toList());
    }
}
