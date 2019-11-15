package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-21 15:30
 */
public class L085 {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0) return 0;
        int n=matrix.length,m=matrix[0].length;

        int[][] dp=new int[n][m];
        int result=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]=='1'){
                    dp[i][j]=j==0?1:dp[i][j-1]+1;

                    int width=dp[i][j];

                    for(int s=i;s>=0 ;s--){
                        width=Math.min(dp[s][j],width);
                        int height=i-s+1;
                        //求这一列当中最小半径然后乘当前dp[s][j]所在的高度和最大面积比较，不然光求最小width会出现最小是1，或者0，然后乘上当前s高度，并不是最大的 情况
                        result=Math.max(result,width*height);
                    }


                }

/*                for(int s1=0;s1<n;s1++){
                    for(int ss=0;ss<m;ss++){
                        System.out.print(dp[s1][ss]+" ");
                    }
                    System.out.println();
                }
                System.out.println("=========================");*/
            }
        }
        return result;
    }

    public static void main(String[] args) {
        L085 demo=new L085();
       /* char[][] matrix={{'0','1'},{'0','1'}};*/
        char[][] matrix={{'0','1','1','0','1'},{'1','1','0','1','0'},{'0','1','1','1','0'},{'1','1','1','1','0'},{'1','1','1','1','1'},{'0','0','0','0','0'}};

        for (char[] chars : matrix) {
            for (char aChar : chars) {
                System.out.print(aChar+" ");
            }
            System.out.println();
        }
        System.out.println(demo.maximalRectangle(matrix));
    }
}
