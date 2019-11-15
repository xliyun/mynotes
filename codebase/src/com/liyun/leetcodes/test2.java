package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-17 17:06
 */
public class test2 {
    public int lengthOfLongestSubstring(String s) {


        int left=0;
        int[] m =new int[256];
        int len=0;

        for(int j=0;j<s.length();j++){
            m[j]=-1;
        }

        for(int i=0;i<s.length();i++){
            if(m[s.charAt(i)]!=-1){
                len=Math.max(len,i-left+1);
                m[s.charAt(i)]=i;
                left=i;
            }else{
                m[s.charAt(i)]=i;
            }
        }

        return 1;
    }
    public static void main(String[] args) {
        System.out.println('a');
        int[] array=new int[256];
        String str="a";
        array[str.charAt(0)]=1;
/*        for (int i : array) {
            System.out.println(i);
        }*/
        System.out.println(str.charAt(0)-' ');
        System.out.println((byte)str.charAt(0));
    }
}
