package com.liyun.struct;

/**
    步长序列        最坏情况下复杂度
    n/2^i           O(n^2)
    2^k-1           O(n^(3/2))
    (2^i)*(3^j)     O(nlog^2 n)
 已知的最好步长序列是由Sedgewick提出的(1, 5, 19, 41, 109,...)，该序列的项来自这两个算式。
 */
public class SHELL_SORT {
    public static void main(String[] args) {
        int[] arr={4,66,34,33,21,77};
        ShellSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void ShellSort(int[] arr){
        int gap = arr.length;

        while(1<=gap){
            //把步长为ap的元素编为一个组，扫描所有组
            for(int i=gap;i<arr.length;i++){
                int j=0;
                int temp=arr[i];//要插入的元素
                //temp<arr[j]是为了找到要插入的位置，一旦temp>=arr[j]那么插入的位置就是j+gap
                for(j=i-gap;j>=0 && temp<arr[j];j=j-gap){
                    arr[j+gap]=arr[j];
                }
/*                j=i-gap;
                while(j>=0 && temp<arr[j]){
                    arr[j+gap]=arr[j];
                    j=j-gap;
                }*/
                arr[j+gap]=temp;
            }
            gap=gap/2;
        }
    }
}
