package com.liyun.leetcodes.L00048;

import com.liyun.leetcodes.LeeUtils;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-28 13:22
 */
public class RotateImage {
    public static void main(String[] args) {
       // int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        LeeUtils.printTwoDimensionalArray(matrix);
        System.out.println("-------------");
        RotateImage rotateImage = new RotateImage();
        rotateImage.rotate(matrix);
        LeeUtils.printTwoDimensionalArray(matrix);


    }

    public void rotate(int[][] matrix) {
        int length = matrix.length;

        int temp = 0;
        //先水平翻转
        for(int i=0;i<length/2;i++){
            for(int j=0;j<length;j++){
                temp = matrix[i][j];
                matrix[i][j]=matrix[length-i-1][j];
                matrix[length-i-1][j] = temp;
            }
        }

        //再对角线翻转
        for(int i=0;i<length;i++){
            for(int j=i+1;j<length;j++){
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i]= temp;
            }
        }
    }
}
