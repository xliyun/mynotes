package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-14 16:58
 */
public class L032 {
    public int longestValidParentheses(String s) {
        if(s.isEmpty() || s.length()==1) return 0;
        int l=s.length();
        int max=0;
        //左括号n，不匹配-2，完全匹配-1
        int[][]dp=new  int [l+1][l+1];
        dp[0][0]=-1;
        for(int m=1;m<=l;m++){
            dp[m][m]=(s.charAt(m-1)=='('?1:0);
            int lon=0;
            for(int n=m+1;n<=l;n++){
                //System.out.println("m:"+m+"  n:"+(n-1)+"====="+dp[m][n-1]);
                if(dp[m][n-1]==1){
                    if(s.charAt(n-1)==')') {
                        dp[m][n] = -1;
                        lon = lon + 2;
                        if (lon > max) max = lon;
                    }else if(s.charAt(n-1)=='(')
                        dp[m][n]=dp[m][n-1]+1;
                }else if(dp[m][n-1]>1){
                    if(s.charAt(n-1)=='(')
                        dp[m][n]=dp[m][n-1]+1;
                    else {
                        dp[m][n] = dp[m][n - 1] - 1;
                        lon=lon+2;
                    }
                }
                else if(dp[m][n-1]==-1){
                    dp[m][n]=(s.charAt(n-1)=='('?1:0);

                }else if(dp[m][n-1]==0){
                    dp[m][n]=0;
                }

/*                for(int i=0;i<=l;i++) {
                    for (int j = 0; j <= l; j++) {
                        System.out.print(" " + dp[i][j]);
                    }
                    System.out.println();
                }
                System.out.println("=========================");*/
            }
        }

        return max;
    }

    public int longestValidParentheses2(String s) {
        if(s.isEmpty() || s.length()==1) return 0;
        int l=s.length();
        int max=0;
        //左括号n，不匹配-2，完全匹配-1
        //int[][]dp=new  int [l+1][l+1];
        int[] dp=new int[l+1];
        dp[0]=-1;
        for(int m=1;m<=l;m++){
            dp[m]=(s.charAt(m-1)=='('?1:0);
            int lon=0;
            for(int n=m+1;n<=l;n++){
                //System.out.println("m:"+m+"  n:"+(n-1)+"====="+dp[m][n-1]);
                if(dp[n-1]==1){
                    if(s.charAt(n-1)==')') {
                        dp[n] = -1;
                        lon = lon + 2;
                        if (lon > max) max = lon;
                    }else if(s.charAt(n-1)=='(')
                        dp[n]=dp[n-1]+1;
                }else if(dp[n-1]>1){
                    if(s.charAt(n-1)=='(')
                        dp[n]=dp[n-1]+1;
                    else {
                        dp[n] = dp[n - 1] - 1;
                        lon=lon+2;
                    }
                }
                else if(dp[n-1]==-1){
                    dp[n]=(s.charAt(n-1)=='('?1:0);

                }else if(dp[n-1]==0){
                    dp[n]=0;
                }

                for(int i=0;i<=l;i++) {
                    System.out.println(dp[i]+" ");
                }
                System.out.println("=========================");
            }
        }

        return max;
    }

    public static void main(String[] args) {
        L032 demo=new L032();
        String str=")()())";
                System.out.println(demo.longestValidParentheses2(str));
    }
}
