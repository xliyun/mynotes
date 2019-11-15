package com.liyun.test;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-05-07 10:25
 */
public class t5 {
    public static void main(String[] args) {

    }
    public static String  replaceSpace(StringBuffer str){
        if(str==null)
            return null;
        if(str.length()==0)
            return null;

        StringBuilder sb=new StringBuilder();

        for(int i=0;i<str.length();i++)
        {
            if(String.valueOf(str.charAt(i)).equals(" "))
                sb.append("%20");
            else
                sb.append(str.charAt(i));
        }

        return String.valueOf(sb);
    }
}
