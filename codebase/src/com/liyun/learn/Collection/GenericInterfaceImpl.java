package com.liyun.learn.Collection;
/*
    含有泛型的 接口，第一种使用方式:定义接口的实现类，实现接口指定接口的泛型
    public interface Iterator<E>{ E next(); }
    Scanner类实现了Iterator接口，并指定接口的泛型为String，所以重写的next方法泛型默认就是String public final class Scanner implements Iterator<String>{ }
 */
public class GenericInterfaceImpl implements GenericInterface<String> {
    @Override
    public void method(String s) {
        System.out.println(s);
    }
}
