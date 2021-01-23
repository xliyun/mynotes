package com.liyun.leetcodes.L00051;

import java.util.List;

public class L00051 {
    public static void main(String[] args) {
        NQueens solution = new NQueens();
        List<List<String>> lists = solution.solveNQueens(4);
        int i = 1;
        for (List<String> list : lists) {
            System.out.println("第"+i+"种解法:");
            for (String s : list) {
                System.out.println(s);
            }
            i++;
        }
        NQueens2 solution2 = new NQueens2();
        int i1 = solution2.totalNQueens(4);
        System.out.println(i1);
    }

}
