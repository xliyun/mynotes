package com.liyun.leetcodes;

/**
    用word2去匹配word1，word1在左长度是n word2在上长度是m

 当word2
 */
public class L072 {
    public int minDistance(String word1, String word2) {
        if(word1.length()==0 ) return word2.length();
        if(word2.length()==0) return word1.length();

        int m=word2.length(),n=word1.length();
       // System.out.println(m+" "+n);
        int[][] dp=new int[n+1][m+1];

            dp[0][0]=0;

        for(int x=0;x<=n;x++)
            dp[x][0]=x;

        for(int y=0;y<=m;y++)
            dp[0][y]=y;

        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] ;
                else
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
            }

/*            for(int s=0;s<=n;s++){
                for(int ss=0;ss<=m;ss++){
                    System.out.print(dp[s][ss]+" ");
                }
                System.out.println();
            }
            System.out.println("=========================");*/
        }
        //求之前
        //求中间多少


        return dp[n][m];
    }

    public static void main(String[] args) {
        L072 demo=new L072();


        System.out.println(demo.minDistance("horse","ros"));
    }
}
