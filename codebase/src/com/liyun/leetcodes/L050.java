package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-10 11:13
 */
public class L050 {
    public double myPow(double x, int n) {
        if(n==0) return 1.0;
        if(x==0) return 0;

        long N=n;
        if(N<0){
            x=1/x;
            N=-N;
        }
            return Pow(x, N);
    }

    public double Pow(double x,long n){
        if(n==0) return 1;

        double half=Pow(x,n/2);
        if(n%2==0)
            return half*half;
        else
            return half*half*x;
        //这种写法是错的，每次都会去计算Pow，会超时
        //return n%2==1?Pow(x,n/2)*Pow(x,n/2)*x:Pow(x,n/2)*Pow(x,n/2);
    }

    public static void main(String[] args) {
        L050 demo=new L050();
        System.out.println(demo.myPow(0.00001,2147483647));
    }
}
