package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-18 22:11
 */
public class L062 {
        public int uniquePaths(int m, int n) {
            if(m<=0 || n<=0) return 0;

            int[][] dp=new int[m][n];
            for(int a=0;a<n;a++)
                dp[0][a]=1;

            for(int b=0;b<m;b++)
                dp[b][0]=1;
            for(int i=1;i<m;i++){
                for(int j=1;j<n;j++){
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];

                    for (int[] ints : dp) {
                        for (int anInt : ints) {
                            System.out.print(anInt+" ");
                        }
                        System.out.println();
                    }
                    System.out.println("===================");
                }
            }
            return dp[m-1][n-1];
        }

    public static void main(String[] args) {
        L062 demo=new L062();
        System.out.println(demo.uniquePaths(7,3));
    }
}
