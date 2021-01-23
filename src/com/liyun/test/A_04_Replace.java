package com.liyun.test;

public class A_04_Replace {
    public static void main(String[] args) {
        StringBuffer bb=new StringBuffer("测试哈哈");
        System.out.println(bb.charAt(0));
        System.out.println(String.valueOf(bb.charAt(0)));
    }
    public String replaceSpace(StringBuffer str){
        if(str==null)
            return null;
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<str.length();i++){
            if(String.valueOf(str.charAt(i)).equals(" ")){
                sb.append("%20");
            }else
                sb.append(str.charAt(i));
        }
        return String.valueOf(sb);
    }
}
