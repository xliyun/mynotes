package com.liyun.test;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-03-26 9:47
 */
public class t2 {
    public static void main(String[] args) {
        t2 t=new t2();
        int[][] array=new int[][]{{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        //int[][] array=new int[][]{{1,2,8,9},{4,7,10,13}};
        System.out.println(t.Find(5,array));
    }
    public boolean Find(int target, int [][] array) {
        int len=array.length-1;
        int col=0;
/*        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }
        System.out.println(array[0][3]);
        System.out.println(target+"---"+len+"  "+col);*/
        while(len>=0 && col<=array[0].length-1){
            System.out.println(len+"=="+col);
            if(array[len][col]==target){
                return true;
            }else if(array[len][col]>target){
                len--;
            }else{
                col++;
            }
        }
        return false;
    }
}
