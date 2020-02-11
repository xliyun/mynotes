package com.liyun.jdk8.lambda;

import java.util.Optional;

/**
 * Optional 容器类的常用方法：
 * Optional.of(T t)：创建一个Optional实例
 * Optional.empty(): 创建一个空的Optional实例
 * Optional.ofNullable(T t): 若t不为null，创建Optional实例，否则创建空实例
 * isPresent(): 判断是否包含值
 * orElse(T t): 如果调用对象包含值，返回该值，否则返回t
 * orElseGet(Supplier s): 如果调用对象包含值，返回该值，否则返回s获取的值
 * map(Function f): 如果有值对其处理，并返回处理后的Optional.empty()
 * flatMap(Function mapper): 与map类似，要求返回值必须是Optional
 */
public class TestOptional {

    public void test1() {
        //如果这里传入的是null Optional.of(null)就会最快的发现空指针异常
        Optional<Employee> op = Optional.of(new Employee());
        Employee employee = op.get();
        System.out.println(employee);

    }

    public void test2() {
        //Optional.empty(): 创建一个空的Optional实例
        Optional<Employee> op = Optional.empty();
        System.out.println(op.get());

        //Optional.ofNullable(T t): 若t不为null，创建Optional实例，否则创建空实例
        //ofNullable就是 return value == null ? empty() : of(value);
        Optional<Employee> op2 = Optional.ofNullable(null);
        System.out.println(op2.get());

        //isPresent(): 判断是否包含值
        if (op2.isPresent()) {
            System.out.println("包含值");
        }

        //orElse(T t): 如果调用对象包含值，返回该值，否则返回t
        Employee emp = op.orElse(new Employee("张三", 13, 122.2));

        //orElseGet(Supplier s): 如果调用对象包含值，返回该值，否则返回s获取的值
        Employee emp2 = op.orElseGet(() -> new Employee("李四", 33, 42.3));

    }

    public void test4() {
        Optional<Employee> op = Optional.of(null);
        //map(Function f): 如果有值对其处理，并返回处理后的Optional.empty()
        Optional<String> s = op.map((e) -> e.getName());
        //flatMap(Faunction mapper): 与map类似，要求返回值必须是Optional
        //即
        Optional<String> str2 = op.flatMap((e) -> Optional.of(e.getName()));


    }

    //需求：获取一个男人心中女神的名字
    public void test5() {
        Man man = new Man();
        String n = getGodnessName(man);

        Optional<NewMan> op= Optional.ofNullable(new NewMan());
        String str2= getGodnessName2(op);
        System.out.println(str2);
    }

    private String getGodnessName(Man man) {
        if(man !=null){
            Godness gn= man.getGodness();
            if(gn!=null){
                return gn.getName();
            }
        }

        return "默认女神";
    }

    public String getGodnessName2(Optional<NewMan> newMan){
        return newMan.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("默认女神"))
                .getName();
    }

}
