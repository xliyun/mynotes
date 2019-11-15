package com.liyun.test;


/*
在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完
成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
 */
/*思路
矩阵是有序的，从左下角来看，向上数字递减，向右数字递增，
因此从左下角开始查找，当要查找数字比左下角数字大时。右移
要查找数字比左下角数字小时，上移
 */
public class A_03_Array {
    public static void main(String[] args) {
        System.out.println((int)5/2);
        String test="aa";
        System.out.println(String.valueOf(test));
    }

    public boolean find(int [][] array,int target){
        if(array==null)
            return false;
        int row=0;
        int column=array.length-1;
        while(row<=array.length-1 && column>=0){
            if(array[row][column]==target)
                return true;
            else if(array[row][column]>target)
                column--;
            else
                row++;
        }
        return false;
    }
}
