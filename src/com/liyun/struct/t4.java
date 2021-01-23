package com.liyun.struct;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-04-30 19:17
 */
public class t4 {
    public static void main(String[] args) {
        int[] arr={1,4,7,33,45,46,47,52,55,67,99};
        int result=find(arr,47);
        System.out.println(result);
    }
    //二分查找必须是顺序结构
    public static int find(int[] arr,int target){
           int low=0;
           int high=arr.length-1;
           /*
  二分法最后查找到答案的边界就是剩下两个元素 所以while是low<=hign
 */
           while(high>=low){//最后一次折半 middle就是(high+low)/2
               int middle=(high+low)/2;
               if(arr[middle]==target)
                   return middle;
               else if(arr[middle]>target){
                   high=middle-1;//middle不等于target，所以high和low都舍去它就对了
               }else {
                   low=middle+1;
               }
           }
        return -1;
    }

    //递归实现二分查找
    public static int binarySearch(int[] arr,int target,int low,int high){
        int middle=(low+high)/2;
        //边界状态，就是target没在数组里面
        if(target<arr[low] || target>arr[high] || low>high)
            return -1;
        if(target<arr[middle]){
            return binarySearch(arr,target,low,middle-1);
        }else if(target>arr[middle]){
            return binarySearch(arr,target,middle+1,high);
        }else{
            return middle;
        }
    }
}
