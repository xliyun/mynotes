package com.liyun.learn.enumtest;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-23 23:43
 */
public class Pizza {
    private PizzaStatus status;

    public PizzaStatus getStatus() {
        return status;
    }

    public void setStatus(PizzaStatus status) {
        this.status = status;
    }

    public enum PizzaStatus{
        ORDER(5){
            @Override
            public boolean isOrderd() {
                return true;
            }
        },
        READY(2){
            @Override
            public boolean isReady() {
                return true;
            }
        },
        DELIVERED(0){
            @Override
            public boolean isDelivered() {
                return true;
            }
        };

        private int timeToDelivery;

        public boolean isOrderd(){return false;}
        public boolean isReady(){return false;}
        public boolean isDelivered(){return false;}

        public int getTimeToDelivery(){
            return timeToDelivery;
        }

        PizzaStatus(int timeToDelivery){
            this.timeToDelivery = timeToDelivery;
        }
    }

    public boolean isDeliverable(){
        return this.status.isReady();
    }

    public void printTimeToDeliver(){
        System.out.println("剩余配送时间："+this.getStatus().getTimeToDelivery());
    }

    public static void main(String[] args) {
        Pizza testPz = new Pizza();
        testPz.setStatus(PizzaStatus.READY);
        System.out.println(testPz.isDeliverable());
    }
}
