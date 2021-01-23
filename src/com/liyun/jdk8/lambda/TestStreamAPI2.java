package com.liyun.jdk8.lambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream 的三个操作步骤：
 * 1. 创建Stream
 * 2. 中间操作
 * 3. 终止操作（终端操作）
 */
public class TestStreamAPI2 {

    List<Employee> employees = Arrays.asList(
      new Employee("张三",18,9999.99),
            new Employee("李四",58,5555.55),
            new Employee("王五",22,3333.33),
            new Employee("赵六",44,6666.66),
            new Employee("田七",32,8888.88),
            new Employee("田七",32,8888.88),
            new Employee("田七",32,8888.88)
    );
    //中间操作
    /*
     筛选与切片
     filter -- 接收Lambda，从流中排除某些元素
     limit -- 截断流，使其元素不超过给定数量
     skip(n) -- 跳过元素，返回一个扔掉了前N个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
     distinct -- 筛选，通过流所生成元素的hashCode()和equals()去除重复元素
     */

    //内部迭代:迭代操作由Stream API 完成
    @Test
    public void test1(){
        //filter需要一个断言参数，可以点fileter需要的Predicate<T> 里面是boolean test(T t)
        Stream<Employee> stream = employees.stream()
                .filter(e -> e.getAge() > 35);

        //终止操作：一次性执行全部内容，即“惰性求值”
        stream.forEach(System.out::println);
    }

    //外部迭代：
    @Test
    public void test2(){
        Iterator<Employee> iterator = employees.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //limit
    public void test3(){
        employees.stream()
                .filter(e->e.getSalary()>5000)
                .limit(2)
                .forEach(System.out::println);

        employees.stream()
                .filter(e->{
                    //查看执行结果会发现，迭代两次，取到两个数据后不会再执行
                    //类似于 && ||
                    System.out.println("短路！");
                    return e.getSalary()>5000;})
                .limit(2)
                .forEach(System.out::println);
    }

    //skip 和limit
    public void test4(){
        employees.stream()
                .filter(e->e.getSalary()>5000)
                .skip(2)
                .forEach(System.out::println);

        employees.stream()
                .filter(e->e.getSalary()>5000)
                .skip(2)
                //Employee 需要重写hashcode和equals
                .distinct()
                .forEach(System.out::println);
    }

    //=====================映射=============================
    /**
     map -- 接收Lambda,将元素转换成其他形式或提取信息。接受一个函数作为参数，该函数会被应用带每个元素上，并将其映射成一个新的元素。
     flatMap -- 接受一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流都连接成一个流。
     **/
    public void test5(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::println);

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        //map本身会返回流，而filterCharacter作用是将输入的字符串转换成流
        Stream<Stream<Character>> streamStream = list.stream()
                .map(TestStreamAPI2::filterCharacter);//{{a,a,a},{b,b,b},{c,c,c}}

        streamStream.forEach(stream->{
            stream.forEach(System.out::println);
        });

        //===========================
        //这时候可以用到flatMap
        Stream<Character> characterStream = list.stream()
                .flatMap(TestStreamAPI2::filterCharacter);//整合成一个流 {a,a,a,b,b,b,c,c,c}

        characterStream.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list=new ArrayList<>();
        for (char ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    public void test6(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");

        List list2 = new ArrayList();

        list2.add(11);
        list2.add(22);
        list2.add(list);
        System.out.println(list2);

        /*
         类比map flatMap
          map就相当于add，将整个流加到集合中
          flatMap就相当于将流中一个个元素加到最终的集合中
          */

        list2.addAll(list);
        System.out.println(list2);
    }


    //===================排序===============================
   /*
    排序
    sorted() -- 自然排序 (Comparable)
    sorted(Comparator com) -- 定制排序 (Comparator)
    */
   public void test7(){
       List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");

       list.stream()
               .sorted()
               .forEach(System.out::println);

       System.out.println("-----------------------");

       employees.stream()
               .sorted((e1,e2)->{
                   if(e1.getAge().equals(e2.getAge())){
                       return e1.getName().compareTo(e2.getName());
                   }else {
                       return e1.getAge().compareTo(e2.getAge());
                   }
               }).forEach(System.out::println);

   }
}
