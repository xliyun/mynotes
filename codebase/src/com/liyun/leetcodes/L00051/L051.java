package com.liyun.leetcodes.L00051;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L051 {
    public static void main(String[] args) {
        List<List<String>> lists = Solution.solveNQueens(4);
        int i = 1;
        for (List<String> list : lists) {
            System.out.println("第"+i+"种解法:");
            for (String s : list) {
                System.out.println(s);
            }
            i++;
        }
    }
    static class Solution {
        public List<List<String>> result = new ArrayList<>();
        public static List<List<String>> solveNQueens(int n) {
            char[][] checkerboard = new char[n][n];
            Arrays.fill(checkerboard,",");
            for(int i = 0;i<n;i++){
                dfs(checkerboard,i);
            }
            return null;
        }

        public static void dfs(char[][] checkerboard, int i){
            if(i>=checkerboard.length){
               
            }
        }
    }
}
