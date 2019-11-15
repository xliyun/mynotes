package com.liyun.leetcodes;

import java.util.ArrayList;
import java.util.List;

public class L006 {
    public String convert(String s, int numRows) {
        if(numRows==1) return s;
        List<StringBuilder> rows=new ArrayList<>();

        //求出多少行
        int rowsLength=Math.min(numRows,s.length());

        for(int i=0;i<rowsLength;i++){
            rows.add(new StringBuilder());
        }

        int index=0;//第几行
        boolean direction=false;//方向

        for(int j=0;j<s.length();j++){
            rows.get(index).append(s.charAt(j));
            if(index==0 || index==rowsLength-1)//换行
                direction=!direction;

            index+=direction?1:-1;

        }
        StringBuilder result=new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();

    }
}
