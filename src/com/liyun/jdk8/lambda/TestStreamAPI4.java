package com.liyun.jdk8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class TestStreamAPI4 {
    List<Employee> employees = Arrays.asList(
            new Employee("张三",18,9999.99, Employee.Status.FREE),
            new Employee("李四",58,5555.55, Employee.Status.BUSY),
            new Employee("王五",22,3333.33, Employee.Status.VOCATION),
            new Employee("赵六",44,6666.66, Employee.Status.FREE),
            new Employee("田七",32,8888.88, Employee.Status.BUSY)
    );
/*
    1.给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
    ，给定 【1，2，3，4，5】，应该返回【1，4，9，16，25】
    */
    public void test1(){
        Integer[] nums=new Integer[]{1,2,3,4,5};

        Arrays.stream(nums)
                .map(x->x*x)
                .forEach(System.out::println);

    }

/*
    2.怎样用map和reduce方法数一数流中有多少个Employee呢
*/
    public void test2(){
        Optional<Integer> reduce = employees.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);


    }

}
