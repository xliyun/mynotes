package com.liyun.learcn.Collection;
/*
定义含有泛型的方法:泛型定义在方法的修饰符和返回值类型之间
格式:
    修饰符 <泛型> 返回值类型 方法名(参数列表(使用泛型)){}
含有泛型的方法，在调用方法的时候确定泛型的数据类型
 */
public class GenericClass<E> {
    public static void main(String[] args) {
        //不写泛型默认为Object类型
        GenericClass gc=new GenericClass();
        gc.setName("只能是字符串");
        System.out.println(gc.getName());

        GenericClass<String> gc2=new GenericClass<>();
        gc.setName("字符串泛型");
        System.out.println(gc.getName());

        //使用含有泛型的方法
        gc2.method1("含有泛型的方法");
        GenericClass.method2("含有泛型的静态方法");
    }
    private E name;

    //定义一个含有泛型的方法
    public <M> void method1(M m){
        System.out.println(m);
    }

    //定义一个含有泛型的静态方法
    public static <S> void method2(S s){
        System.out.println(s);
    }

    public E getName() {
        return name;
    }

    public void setName(E name) {
        this.name = name;
    }
}
