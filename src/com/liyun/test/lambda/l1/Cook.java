package com.liyun.test.lambda.l1;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-25 9:29
 */
public interface Cook {

    public abstract void makeFood();

    public static void main(String[] args) {
        invoteCook(new Cook() {
            @Override
            public void makeFood() {
                System.out.println("吃饭了");
            }
        });

        invoteCook(()->{
            System.out.println("吃饭了");
        });

        invoteCook(()-> System.out.println("吃饭了"));
    }

    public static void invoteCook(Cook cook){
        cook.makeFood();
    }
}
