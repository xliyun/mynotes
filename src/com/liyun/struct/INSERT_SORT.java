package com.liyun.struct;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-05-12 18:28
 */
public class INSERT_SORT {
    public static void main(String[] args) {
        int[] arr={4,66,34,33,21,77};
        insertSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void insertSort(int[] a){
        int len=a.length;//单独把数组长度拿出来，提高效率
        int insertNum;
        for(int i=1;i<len;i++){//因为第一次不用，所以从1开始
            insertNum=a[i];//把要插入的数暂存，向前遍历，找到位置就把它插入
            int j=i-1;//序列元素个数
            while(j>=0 && a[j]>insertNum){
                a[j+1]=a[j];//元素向后移动
                j--;
            }
            a[j+1]=insertNum;//找到位置，插入当前元素
        }

    }
}