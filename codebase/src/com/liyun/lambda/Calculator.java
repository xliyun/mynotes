package com.liyun.lambda;
/**
 * Lambda表达式有参数有返回值的 联系
 *  需求：
 *      给定一个计算器Calculator接口，内含抽象方法calc可以将两个int数字相加得到和值
 *      使用Lambda的标准格式调用invokeCalc方法，完成10和20相加计算
 **/
public interface Calculator {
    //定义一个计算两个int类型
    public abstract int calc(int a,int b);

    public static void main(String[] args) {

        invokeCalc(10, 20, new Calculator() {
            @Override
            public int calc(int a, int b) {
                return a+b;
            }
        });

        //使用Lambda表达式简化匿名内部类的书写
        invokeCalc(10,20,(int a,int b)->{
            return a+b;
        });

        //优化省略
        invokeCalc(10,20,(a,b)->a+b);
    }

    //定义个一个方法
    public static void invokeCalc(int a,int b,Calculator c){
        int sum=c.calc(a,b);
        System.out.println(sum);
    }
}
