package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-19 9:51
 */
public class L064 {
    public int minPathSum(int[][] grid) {
        if(grid.length<=0) return 0;
        int m=grid[0].length,n=grid.length;
        int[][] dp=new int[n][m];

        dp[0][0]=grid[0][0];

        //初始化行
        for(int x=1;x<m;x++){
            dp[0][x]=dp[0][x-1]+grid[0][x];
        }
        //初始化列
        for(int y=1;y<n;y++){
            dp[y][0]=dp[y-1][0]+grid[y][0];
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }


        return dp[n-1][m-1];
    }
    public static void main(String[] args) {

    }
}
