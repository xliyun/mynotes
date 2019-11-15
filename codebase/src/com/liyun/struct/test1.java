package com.liyun.struct;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-05-12 22:09
 */
public class test1 {
    public static void main(String[] args) {
        int[] arr={4,66,34,33,21,77};
        insertSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void insertSort(int[] a){
       int len=a.length;
       int insertNum;
       for(int i=1;i<len;i++){
           insertNum=a[i];
           int j=i-1;
           while(j>=0 && a[j]>insertNum){
               a[j+1]=a[j];
               j--;
           }
           a[j+1]=insertNum;
       }
    }
}
