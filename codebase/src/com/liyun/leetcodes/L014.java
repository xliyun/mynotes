package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-11-12 15:31
 */
public class L014 {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0){
            return "";
        }
        if(strs.length==1){
            return strs[0];
        }
        StringBuilder stringBuilder=new StringBuilder("");

        int min=strs[0].length();

        for(int i=1;i<strs.length;i++){
            if(strs[i].length()<min) {
                min = strs[i].length();
            }
        }

        for(int n=0;n<min;n++){
            boolean flag=true;
            for(int m=1;m<strs.length;m++){
                if(strs[m].charAt(n)!=strs[m-1].charAt(n)){
                    flag=false;
                    break;
                }
            }
            if(flag){
                stringBuilder.append(strs[0].charAt(n));
            }else{
                break;
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        String[] strs={"flower","flow","flight"};
        String[] strs2={"dog","racecar","car"};
        L014 l014 = new L014();
        System.out.println(l014.longestCommonPrefix(strs2));
    }
}
