package com.liyun.leetcodes;

public class L011 {
    public int maxArea(int[] height) {
        if(height.length<1) return 0;
        int max=0;

        for(int i=0;i<height.length-1;i++){
            for(int j=i+1;j<height.length;j++){
                int a=Math.min(height[i],height[j]);
                int area=a*(j-i);
                max=area>max?area:max;
            }
        }
        return max;
    }
}
