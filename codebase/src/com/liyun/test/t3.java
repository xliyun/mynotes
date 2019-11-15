package com.liyun.test;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-04-30 9:30
 */
public class t3 {
    public static void main(String[] args) {
        int[] arr={6,3,8,2,9,1};

        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    int swap=arr[i];
                    arr[i]=arr[j];
                    arr[j]=swap;
                }
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
