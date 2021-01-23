package com.liyun.leetcodes;

public class L044 {
    public boolean isMatch(String s, String p) {
        int i=0,j=0,iStar=-1,jStar=-1;
        while(i<s.length()){
            System.out.println("i:"+i+"--j:"+j+"===iStar:"+iStar+"--jStar"+jStar);
            if(j<p.length() && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='?')){
                ++i;++j;
            }else if(j<p.length() && p.charAt(j)=='*'){
                iStar=i;
                jStar=j++;
            }else if(iStar>=0){
                i=++iStar;
                j=jStar+1;
            }else return false;
        }
        while(j<p.length() && p.charAt(j)=='*')++j;
        return j==p.length();
    }

    public static void main(String[] args) {
        L044 demo=new L044();
        System.out.println(demo.isMatch("abbbbbbbc","a*bb*bb*bbc"));
    }
}
