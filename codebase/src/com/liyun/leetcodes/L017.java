package com.liyun.leetcodes;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-11-14 11:02
 */
public class L017 {
    //双括号初始化 （匿名内部类）
    //慎用， 非静态内部类/ 匿名内部类包含了外围实例的引用， 如果拥有比外部类更长的生命周期，有内存泄露隐患
    //也就是做题，不然还是静态代码块
    Map<String,String> map=new HashMap<String,String>(){
        {
            put("2", "abc");
            put("3", "def");
            put("4", "ghi");
            put("5", "jkl");
            put("6", "mno");
            put("7", "pqrs");
            put("8", "tuv");
            put("9", "wxyz");
        }
    };

    private  List<String> list=new ArrayList<>();




    public List<String> letterCombinations(String digits) {
        if(digits==null || digits.length()==0){
            return new ArrayList<>();
        }

       combinationNumber("",digits);
        return list;
    }

    //不剪枝的回溯
    private void combinationNumber(String result,String digits) {
        if(digits.length()==0){
            list.add(result);
        }else {
            //获取数字对应的字符串
            String letters = map.get(digits.substring(0, 1));
            for (int i = 0; i < letters.length(); i++) {
                String letter=letters.substring(i,i+1);
                combinationNumber(result + letter, digits.substring(1));
            }
        }
    }

    public static void main(String[] args) {
        L017 l017 = new L017();
        System.out.println(l017.letterCombinations("2"));
    }

}
