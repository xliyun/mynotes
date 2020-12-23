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
//        String s= "barfoothefoobarman";
//        String[] words = {"foo","bar"}; //[0,9]
//        String s=  "wordgoodgoodgoodbestword";
//        String[] words= {"word","good","best","word"};//
//        String s="barfoofoobarthefoobarman";
//        String[] words = {"bar","foo","the"};//[6,9,12]
//        String s= "wordgoodgoodgoodbestword";
//        String[] words= {"word","good","best","good"};

//        String s=  "lingmindraboofooowingdingbarrwingmonkeypoundcake";
//        String[] words= {"fooo","barr","wing","ding","wing"};//[13]

        String s=   "abaababbaba";
        String[] words= {"ab","ba","ab","ba"};//[1,3]
        SubstringWithConcatenationOfAllWords substringWithConcatenationOfAllWords = new SubstringWithConcatenationOfAllWords();
        List<Integer> substring = substringWithConcatenationOfAllWords.findSubstring(s, words);
        System.out.println(substring.toString());
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        //每次滑动的长度,单词长度
        int wordLength = words[0].length();
        //单词的个数
        int sumLength = words.length;
        if(words.length==0
            //leecode默认没引这个方法        || StringUtils.isBlank(s)
        ){
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

        int cur = 0;
        int sumcount = sumLength;//还剩多少个单词需要匹配
        Map<String,Integer>  countWords = new HashMap<>(allWords);
        while(cur<=(s.length()-wordLength)){
                //当前需要检查的单词
                String nowStr = s.substring(cur,cur+wordLength);

                Integer count = countWords.get(nowStr);
                  //1.如果不包含直接跳过
                 if(count==null){
                     int matchcount = sumLength-sumcount;
                     cur = cur - (matchcount * wordLength)+1;
                     sumcount=sumLength;
                     countWords = new HashMap<>(allWords);
                  }
                //2.如果包含，但是单词已经被减成0，就从这个单词第一次出现的位置再次开始
                 //sumLength-sumcount 已经匹配的单词个数
                else if(count ==0){
                int matchcount = sumLength-sumcount;
                cur = cur - (matchcount * wordLength)+1;
//                   for(int k=0;k<matchcount;k++){
//                       if(nowStr.equals(s.substring(cur+k,wordLength))){
//                           continue;
//                       }
//                   }
                sumcount=sumLength;
                countWords = new HashMap<>(allWords);
                 }
                 //3.如果包含就将命中的单词减1
                else if(count!=null && count>0){
                    countWords.put(nowStr,--count);
                    cur=cur+wordLength;
                    sumcount--;
                }

                //sumcount==0就是匹配完成
                //从最初匹配的位置后移一个单词，接着匹配
                if(sumcount==0){
                    result.add(cur-(sumLength*wordLength));
                    sumcount=sumLength;
                    countWords = new HashMap<>(allWords);
                    cur =cur - (sumLength * wordLength)+1;
                }

        }

        return result;
    }
}
