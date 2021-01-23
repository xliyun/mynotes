package com.liyun.sword;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-05-15 14:01
 */
public class SW_10 {
    public static void main(String[] args) {

    }

    public int NumberOf1(int n){
        int count=0;
        while(n!=0){
            count++;
            n=(n-1)&n;
        }
        return count;
    }
}
