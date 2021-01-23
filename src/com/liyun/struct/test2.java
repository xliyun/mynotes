package com.liyun.struct;

import java.util.Arrays;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-05-16 14:24
 */
public class test2 {
    public static void main(String []args){
        int []arr = {9,8,7,6,5,4,3,2,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int[] arr){
        for(int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }
    }

    private static void adjustHeap(int[] arr, int i, int length) {
        int temp=arr[i];

        for(int j=i;j*2+1<length;j=j*2+1){
            if(j<length &&  arr[j]<arr[j+1]){
                j++;
            }
        }
    }
}
