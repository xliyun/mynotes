package com.liyun.leetcodes.L00051;

import java.util.ArrayList;
import java.util.List;

class NQueens2 {
    public int totalNQueens(int n) {
        char[][] checkerboard = new char[n][n];
       int result = 0;
//            Arrays.fill(checkerboard,",");

        for(int s=0;s<n;s++){
            for(int m=0;m<n;m++){
                checkerboard[s][m]='.';
            }
        }

        result = dfs(result,checkerboard,0);
        return result;
    }


        public  int dfs(int result,char[][] checkerboard, int i){
            if(i>=checkerboard.length){
               result= result+1;
               return result;
            }

            for(int j = 0;j<checkerboard.length;j++){
                if(checkQuen(checkerboard,i,j)){
                    checkerboard[i][j]='Q';
                    result = dfs(result,checkerboard,i+1);
                    checkerboard[i][j]='.';
                }
            }
            return result;
        }

        private  List<String> changeToList(char[][] checkerboard) {
            List<String> list = new ArrayList<>();
//            for(int i =0;i<checkerboard.length;i++){
//                StringBuilder builder = new StringBuilder();
//                for(int j=0;j<checkerboard.length;j++){
//                    builder.append(checkerboard[i][j]);
//                }
//                list.add(builder.toString());
//            }
            for(int i=0;i<checkerboard.length;i++){
                list.add(new String(checkerboard[i]));
            }
            return list;
        }

        private  Boolean checkQuen(char[][] checkerboard,int i,int j){
            //检查横线
            if(j>0)
            for(int y=0;y<j;y++){
                if(checkerboard[i][y]=='Q'){
                    return false;
                }
            }

            //检查竖线
            if(i>0)
            for(int x=0;x<i;x++){
                if(checkerboard[x][j]=='Q'){
                    return false;
                }
            }

            //检查对角线，因为是从上向下，从左向右扫描，所以只检查左上角，右上角
            //左上角
            for(int x=i-1,y=j-1;x>=0&&y>=0;x--,y--){
                if(checkerboard[x][y]=='Q'){
                    return false;
                }
            }
            //右上角
                //2,2   3,1
            for(int x=i-1,y=j+1;x>=0&&y<checkerboard.length;x--,y++){
                if(checkerboard[x][y]=='Q'){
                    return false;
                }
            }
            return true;
        }
    }