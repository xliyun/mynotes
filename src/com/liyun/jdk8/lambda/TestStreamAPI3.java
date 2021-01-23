package com.liyun.jdk8.lambda;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 */
public class TestStreamAPI3 {
/*
    查找与匹配
    allMatch -- 检查是否匹配所有元素
    anyMatch -- 检查是否至少匹配一个元素
    noneMatch -- 检查是否没有匹配所有元素
    findFirst -- 返回第一个元素
    findAny -- 返回当前流中的任意元素
    count -- 返回流中元素的总个数
    max -- 返回流中最大值
    min -- 返回流中最小值
    */
List<Employee> employees = Arrays.asList(
        new Employee("张三",18,9999.99, Employee.Status.FREE),
        new Employee("李四",58,5555.55, Employee.Status.BUSY),
        new Employee("王五",22,3333.33, Employee.Status.VOCATION),
        new Employee("赵六",44,6666.66, Employee.Status.FREE),
        new Employee("田七",32,8888.88, Employee.Status.BUSY)
);
    public void test1(){
        //employees 都是busy返回true
        boolean b = employees.stream()
                .allMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);

        //employees 里面有一个是busy返回true
        boolean b2 = employees.stream()
                .anyMatch(e -> e.getStatus().equals(Employee.Status.BUSY));

        System.out.println(b2);

        //employees 里面
        boolean b3 = employees.stream()
                .noneMatch(e -> e.getStatus().equals(Employee.Status.BUSY));


        Optional<Employee> op = employees.stream()
                .sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))//由高到低
                .findFirst();

        //lanbda表达式判断返回值有可能为空时，比如op里面的Employee为空，你可以用一个来替代，来避免空指针
        op.orElse(new Employee("替代",22,22.22, Employee.Status.BUSY));
        System.out.println(op.get());

        //使用串行流
        Optional<Employee> op2 = employees.stream()
                .filter(e -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(op2.get());

        //使用并行流，多个线程同时去完成filter和findAny
        Optional<Employee> op3 = employees.parallelStream()
                .filter(e -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
    }

    public void test2(){
        long count = employees.stream()
                .count();

        Optional<Employee> op = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));

        System.out.println(op.get());

        //获取公司中最小工资是多少
        Optional<Double> op2 = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compareTo);
        System.out.println(op2.get());

    }

    //============================归约操作===================
    /*归约
    reduce(T identity,BinaryOperator)/reduce(BinaryOperator) -- 可以将流中元素反复结合起来，得到一个值
    */
    public void test3(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //identity起始值,accumulator(BinaryOperator<T>二元运算)
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        //没有起始值是可能为空的
        Optional<Double> op1 = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op1.get());

    }

    //=========================收集操作=========================
    /*收集
    collect -- 将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素汇总的方法*/

    public void test4(){
        List<String> list = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        list.forEach(System.out::println);
        System.out.println("-------------------------");
        Set<String> set = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());

        set.forEach(System.out::println);

        HashSet<String> hs = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));//toCollection需要一个供给型接口

        hs.forEach(System.out::println);
    }

    public void test5(){
        //总数
        Long count = employees.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        //按工资获取平均值
        Double avg = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

        //总和
        Double sum = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));

        //最大值
        Optional<Employee> max = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));

        //最小工资
        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));

    }

    //分组
    public void test6(){
        Map<Employee.Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));

        System.out.println(map);
    }

    //多级分组
    public void test7(){
        //先按状态，再按照年龄分组
        Map<Employee.Status, Map<String, List<Employee>>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() <= 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));

        System.out.println(map);
    }

    //分片 分区
    public void test8(){
        //满足条件的一个区，不满足条件的一个区
        Map<Boolean, List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));

        System.out.println(map);
    }

    public void test9(){
        DoubleSummaryStatistics dss = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getSum());
        System.out.println(dss.getMax());
        System.out.println(dss.getAverage());
    }

    public void test10(){
        String str = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));

        System.out.println(str);

        String str2 = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",","字符串首部","字符串尾部"));
    }
}
