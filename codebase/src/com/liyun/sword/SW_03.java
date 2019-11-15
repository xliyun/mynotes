package com.liyun.sword;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-03-26 10:00
 */
public class SW_03 {

    public boolean Find(int target, int [][] array) {

        for(int i=0;i<array.length;i++){
            int low=0;
            int high=array[i].length-1;
            while(low<=high){
                int mid=(low+high)/2;
                if(target>array[i][mid])
                    low=mid+1;
                else if(target<array[i][mid])
                    high=mid-1;
                else
                    return true;
            }
        }
        return false;
    }
}
