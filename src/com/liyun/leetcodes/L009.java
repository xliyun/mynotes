package com.liyun.leetcodes;

public class L009 {
    public boolean isPalindrome(int x) {
        if(x==0) return true;
        if(x<0 || (x%10==0 && x!=0))return false;
        if(x>0 && x<10) return true;
        int min=0;

        while(min<x){
            min=min*10+x%10;
            x=x/10;
        }
        if(x==min || x==min/10)
            return true;

        return false;
    }
}
