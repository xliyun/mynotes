package com.liyun.leetcodes.L00037;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-23 17:43
 */
public class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'},
        };
        for (int i = 0;i<board.length;i++){
            for(int j = 0;j<board[0].length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        SudokuSolver sudokuSolver = new SudokuSolver();
        sudokuSolver.solveSudoku(board);
        System.out.println("===========================");

        for (int i = 0;i<board.length;i++){
            for(int j = 0;j<board[0].length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    public void solveSudoku(char[][] board) {
        if(board==null || board.length==0){
            return;
        }
        //初始化已经放了数字的位置
        boolean[][] boarded =new boolean[board.length][board[0].length];
        boolean[][] width = new boolean[9][10];
        boolean[][] height = new boolean[9][10];
        boolean[][][] box = new boolean[3][3][10];

        for (int i = 0;i<board.length;i++){
            for(int j = 0;j<board[0].length;j++){
              if(board[i][j]!='.'){
                  int num = board[i][j] - '0';
                  width[i][num] = true;
                  height[j][num] = true;
                  box[i/3][j/3][num] = true;
              }
            }
        }

     fillBoard(board, width, height, box, 0, 0);

    }

    private boolean fillBoard(char[][] board, boolean[][] width, boolean[][] height, boolean[][][] box, int i, int j) {
        if(j==9){
            j=0;
            i++;//!!!!!!!!!行变化以及剪枝
            if(i==9){
                return true;
            }
        }

        if(board[i][j]=='.'){
            for(int k = 1;k<=9 ;k++){
                //如果校验通过
                if(!(width[i][k] || height[j][k] || box[i/3][j/3][k])){
                    width[i][k] =true;
                    height[j][k] = true;
                    box[i/3][j/3][k] = true;
                    board[i][j] = (char)('0' + k);
                    if(fillBoard(board,width,height,box,i,j+1)){
                        return true;
                    }else{
                        width[i][k] =false;
                        height[j][k] = false;
                        box[i/3][j/3][k] = false;
                        board[i][j] = '.';
                    }
                }
            }
        }else{
           return fillBoard(board,width,height,box,i,j+1);
        }


        return false;

    }
}
