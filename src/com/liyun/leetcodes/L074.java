package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-12 14:05
 */
public class L074 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null) return false;
        int n=matrix.length;
        System.out.println(n);
        if(n==0) return false;
                int m=matrix[0].length;
        long low=1,high=m*n;
        while(low<=high){
            long middle=(low+high)/2;
            if(value(matrix,middle)==target) {
                //System.out.println(middle);
                return true;
            }
            else if(value(matrix,middle)<target) low=middle+1;
            else high=middle-1;
        }

        //System.out.println(value(matrix, 3));
        return false;
    }

    public int value(int[][] matrix,long index){
        long n=matrix.length,m=matrix[0].length;
        long left,right;
        if(index%m==0)
            left=(index/m)-1;
        else
            left=(index/m);
        if(index%m==0)
            right=m-1;
        else right=(index%m)-1;
        System.out.println("left:"+left+"--right:"+right+"****m:"+m+"---n:"+n+"   index:"+index);
        return matrix[(int)left][(int)right];
    }

    public static void main(String[] args) {
        L074 demo=new L074();
        int[][] matrix={{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        int[][] matrix2={{1,3,5,7}};
        int[][] matrix3={{1},{10},{23}};
        int[][] matrix4={};
        int[][] matrix5={{1,3}};
        //System.out.println(matrix.length+" "+matrix[0].length);
        System.out.println(demo.searchMatrix(matrix5,13));
        //System.out.println(2%4);
    }
}
