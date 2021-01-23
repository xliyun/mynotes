package com.liyun.lambda;

/**
 * 使用lambda标准格式(无参无返回)
 */
public interface Cook {
    public abstract void makeFood();

    public static void main(String[] args) {
        //调用invokeCook方法，参数是Cook接口，传递cook接口的匿名内部类对象
        invoteCook(new Cook() {
            @Override
            public void makeFood() {
                System.out.println("吃饭了");
            }
        });

        //使用lambda表达式，简化匿名内部类的书写
        invoteCook(()->{
            System.out.println("lambda表达式，吃饭了");
        });

        //优化省略
        invoteCook(()->System.out.println("lambda表达式，吃饭了"));
    }

    //定义一个方法，参数传递Cook接口，方法内部调用Cook接口中的方法makeFood
    public static void invoteCook(Cook cook){
        cook.makeFood();
    }

}
