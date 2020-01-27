package com.liyun.lambda.guigu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * JAVA8 内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 *      void accept(T t)
 *
 * Supplier<T> : 供给型接口
 *      T get();
 *
 * Function<T,R> : 函数型接口
 *      R apply(T t);
 *
 * Predicate<T> : 断言型接口
 *
 *      boolean test(T t);
 */
public class TestLambda3 {

    //Consumer<T> 消费型接口
    public void test1(){
        happy(10000,(m)-> System.out.println("消费"+m+"元"));
    }

    public void happy(double money, Consumer<Double> con){
        con.accept(money);
    }

    //Supplier<T> : 供给型接口
    public void test2(){
        List<Integer> numberList = getNumberList(10, () -> (int) Math.random() * 100);
        for (Integer integer : numberList) {
            System.out.println(integer);
        }
    }

    //需求：产生指定个数的整数，并放入集合中
    public List<Integer> getNumberList(int num, Supplier<Integer> sup){
        List<Integer> list=new ArrayList<>();
        for(int i = 0; i< num; i++){
            Integer integer = sup.get();
            list.add(integer);
        }
        return list;
    }

    //Function<T,R> : 函数型接口
    public void test3(){
        String s = strHandler("\t\t\t 被处理的字符串", (str) -> str.trim());
        System.out.println(s);

        String str2 = strHandler("一二三四五六七", (str) -> str.substring(0, 4));
        System.out.println(str2);
    }

    //需求：用于处理字符串
    public String strHandler(String str, Function<String,String> fun){
        return fun.apply(str);
    }

    //Predicate<T> 断言型接口：
    public void test4(){
        List<String> list= Arrays.asList("abcd","efg","hijk","lmnopq","rstu");
        List<String> list1 = filterStr(list, (s) -> s.length() > 3);
        for (String s : list1) {
            System.out.println(s);
        }
    }

    //需求：将满足条件的字符串，放入集合中去，
    public List<String> filterStr(List<String> list, Predicate<String> pre){
        List<String> strList = new ArrayList<>();
        for (String s : strList) {
            if(pre.test(s)){
                strList.add(s);
            }
        }

        return strList;
    }
}
