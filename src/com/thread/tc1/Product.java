package com.thread.tc1;

public class Product {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Product(int id, String name) {
        System.out.println("开始烹饪..."+name);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        this.id = id;
        this.name = name;
        System.out.println(name+"烹饪完毕");
    }

    public static void main(String[] args) {
        ProductFactory  pf=new ProductFactory();

        //下单,
        Future f=pf.createProduct("排骨");
        System.out.println("我去洗菜,过会取排骨...");

        //拿着订单获取产品
        System.out.println("我洗完菜了，来拿排骨"+f.get());
    }
}
