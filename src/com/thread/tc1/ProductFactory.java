package com.thread.tc1;

import java.util.Random;

public class ProductFactory {
    public Future createProduct(String name){
        //创建一个订单

        Future f=new Future();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //生产产品
                Product p=new Product(new Random().nextInt(),name);
                f.setProduct(p);
            }
        }).start();

        return f;
    }
}
