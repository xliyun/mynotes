package com.liyun.learn.Collection;
/*
含有泛型的接口第二种使用方式:接口使用什么泛型，实现类就使用什么泛型，类跟着接口走
public inerface List<E>{
boolean add(E e);
E get(int index);
}

public class ArrayList<E> implements List<E>{ }
 */
public class GenericInterfaceImpl2<I> implements GenericInterface<I> {
    @Override
    public void method(I i) {
        System.out.println(i);
    }
}
