package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-05 16:00
 */
public class L008String_to_Integer {
    public int myAtoi(String str) {
        if(str.isEmpty()) return 0;
        int sign=1,index=0,length=str.length(),number=0;
        while(index<length && str.charAt(index)==' ') ++index;

        if(index<length && (str.charAt(index)=='-' || str.charAt(index)=='+'))
            sign= (str.charAt(index++)=='-')?-1:1;

        while(index<length && (str.charAt(index)>='0' && str.charAt(index)<='9')){
            if(number>Integer.MAX_VALUE/10 || (number==Integer.MAX_VALUE/10 && str.charAt(index)-'0'>(sign==1?7:8)))
                return sign==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
            number=number*10+(str.charAt(index++)-'0');
        }


        return number*sign;
    }

    public static void main(String[] args) {
        L008String_to_Integer demo=new L008String_to_Integer();
        System.out.println(demo.myAtoi("-42"));
    }
}
