package com.liyun.leetcodes.L00030;

import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-17 10:06
 */
public class SubstringWithConcatenationOfAllWords {
    public static void main(String[] args) {
        String s= "barfoothefoobarman";
        String[] words = {"foo","bar"};
        SubstringWithConcatenationOfAllWords substringWithConcatenationOfAllWords = new SubstringWithConcatenationOfAllWords();
        List<Integer> substring = substringWithConcatenationOfAllWords.findSubstring(s, words);
        System.out.println(substring.toString());
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        //每次滑动的长度
        int wordLenth = words[0].length();
        if(words.length==0 || StringUtils.isBlank(s)){
            return result;
        }

        Map<String,Integer> allWords = new HashMap();
        for (String word : words) {
           if(allWords.containsKey(word)){
               allWords.put(word,(allWords.get(word)+1));
           }else{
               allWords.put(word,1);
           }
        }




        return null;
    }
}
