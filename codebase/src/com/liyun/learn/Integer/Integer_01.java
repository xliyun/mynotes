package com.liyun.learn.Integer;

/*
装箱：把基本类型的数据，包装到包装类中(基本类型的数据->包装类)
    构造方法：
    Integer(int value) 构造一个新分配的Integer对象，它表示指定的int值
    Integer(String s) 构造一个新分配的Integer对象，它表示String参数所指示的int值
        传递的字符串必须是基本类型的字符串，否则会抛出异常
    静态方法：
    static Integer valueOf(int i) 返回一个表示指定的int值的Integer实例。
    static Integer valueOf(String s)返回保存指定的String的值的Integer对象。
拆箱：在包装类中取出基本类型的数据(包装类->基本类型的数据)
    成员方法:
        int intValue() 以int类型返回该Integer的值
 */
public class Integer_01 {
    public static void main(String[] args) {
        Integer in1 = new Integer(1);
        System.out.println(in1);//1 重写toString方法

        Integer int2 = new Integer("1");
        System.out.println(int2);
        //静态方法
        Integer in3 = Integer.valueOf(1);
        System.out.println(in3);

        //拆箱
        int i=in1.intValue();
        System.out.println(i);
    }
}
