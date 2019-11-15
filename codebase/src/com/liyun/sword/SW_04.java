package com.liyun.sword;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-05-06 9:14
 */
public class SW_04 {
    public static void main(String[] args) {

    }
    public String replaceSpace(StringBuffer str){
        if(str==null)
            return null;

        StringBuilder sb=new StringBuilder();

        for(int i=0;i<str.length();i++){
            if(String.valueOf(str.charAt(i)).equals(" "))
                sb.append("%20");
                else
                    sb.append(str.charAt(i));
        }
        return String.valueOf(sb);
    }
}
