package com.liyun.test.lambda.l1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-25 9:40
 */
public class Person {
    public static void main(String[] args) {
        Person[] arr={
                new Person("刘亦菲",22),
                new Person("刘德华",44),
                new Person("吴京",33),
                new Person("赵本山",55)
        };

        Arrays.sort(arr, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.age-o2.age;
            }
        });

        //使用lambda表达式，简化匿名函数
        Arrays.sort(arr,(Person o1,Person o2)->{return o1.age-o2.age;});

        //优化省略
        Arrays.sort(arr,((o1, o2) -> o1.age-o2.age));
    }

    private String name;

    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{"+
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
