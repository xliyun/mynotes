package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-08 17:16
 */
public class L029Divide_Two_Integers {
    public int divide(int dividend, int divisor) {
        if(dividend==(-(1 << 31)) && divisor==-1) return Integer.MAX_VALUE;
        //求符号
        int sign=(dividend^divisor)>=0?1:-1;
        int result=0;
        int count=0;

        long longdividend=Math.abs((long)dividend); // -2147483648变正会溢出
        long longdivisor=Math.abs((long)divisor);

        for(int i=31;i>=0;i--){
            if((longdividend>>i)>=longdivisor){
                result+=1<<i;
                longdividend-=(longdivisor<<i);
            }
        }

        return result*sign;
    }

    public static void main(String[] args) {
        L029Divide_Two_Integers demo=new L029Divide_Two_Integers();
        System.out.println(demo.divide(-2147483648,-1));
    }
}
