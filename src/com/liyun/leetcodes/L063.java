package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-19 9:22
 */
public class L063 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid.length<=0) return 0;
        int m=obstacleGrid[0].length;
        int n=obstacleGrid.length;

        int[][] dp=new int[n][m];

        if(obstacleGrid[0][0]!=1){
            dp[0][0]=1;
        }

        //初始化列
        for(int x=1;x<n;x++){
            if(obstacleGrid[x][0]==1)
                dp[x][0]=0;
            else
                dp[x][0]=dp[x-1][0];
        }

        //初始化行
        for(int y=1;y<m;y++){
            if(obstacleGrid[0][y]==1)
                dp[0][y]=0;
            else
                dp[0][y]=dp[0][y-1];
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(obstacleGrid[i][j]==1)
                    dp[i][j]=0;
                else{
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }
            }
        }

        return dp[n-1][m-1];
    }

    public static void main(String[] args) {
        L063 demo=new L063();
        int[][] a={{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(demo.uniquePathsWithObstacles(a));
    }
}
