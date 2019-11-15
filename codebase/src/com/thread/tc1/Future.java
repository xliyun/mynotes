package com.thread.tc1;

public class Future {
    private Product product;

    private boolean down;

    public synchronized void setProduct(Product product){
        //如果已经生产成功就返回就行了
        if(down){
            return;
        }
        System.out.println("预约成功...你可以去做其他事情");
        this.product=product;
        this.down=true;
        notifyAll();
    }

    public synchronized Product get(){
        //等生产完毕再返回
        while(!down){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return product;
    }
}
