package com.liyun.struct;

import java.util.Arrays;

/**
 大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
 小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
 */
public class HEAP_SORT {
    public static void main(String []args){
        int []arr = {9,8,7,6,5,4,3,2,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr){
        //1.构建大顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr,i,arr.length);
        }
        //2.调整堆结构+交换对顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            swap(arr,0,j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr,0,j);//重新对堆进行调整
        }

    }

    //调整大顶堆(仅是调整过程，建立在大顶堆已构建的基础上)
    //以
    public static void adjustHeap(int[] arr,int i,int length){
        int temp=arr[i];//先取出当前元素i

        //头节点-左子节点-右子节点 当中最大的赋值给arr[i]
        for(int k=i*2+1;k<length;k=k*2+1){//从i结点的左子节点，也就是2i+1处开始
            if(k+1<length && arr[k]<arr[k+1]){//如果左子节点小于右子节点，k指向右子节点
                k++;
            }
            if(arr[k]>temp){//如果子节点大于父节点，将子节点赋值给父节点(不用进行交换)
                arr[i]=arr[k];
                i=k;
            }else{//如果父节点大于子节点，就停止调整
                break;
            }
        }
        arr[i]=temp;//将temp值放到最终的位置
    }
    //交换元素
    public static void swap(int []arr,int a ,int b){
        int temp=arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
