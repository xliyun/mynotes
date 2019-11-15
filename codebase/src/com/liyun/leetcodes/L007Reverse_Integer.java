package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-05 15:04
 */
public class L007Reverse_Integer {
    public int reverse(int x) {
        int res=0;
        while(x!=0){
            //如果溢出了，在溢出之前的那个乘十之前判断 也就是在1463847412时就得返回0
            if(Math.abs(res)>Integer.MAX_VALUE/10) return 0;
            //Math.abs(res) > ((1 << 31) - 1) / 10
            res=res*10+(x%10);
            x=x/10;
        }
        return res;
    }

    public static void main(String[] args) {
        L007Reverse_Integer demo=new L007Reverse_Integer();

        System.out.println(demo.reverse(-123));
    }
}
