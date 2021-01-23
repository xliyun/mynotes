package com.liyun.leetcodes.L00049;

import java.util.*;

public class GroupAnagrams {
    public static void main(String[] args) {
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        String[] arr = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams.groupAnagrams(arr);
        for (List<String> list : lists) {
            for (String s : list) {
                System.out.print(s+" ");
            }
            System.out.println();
        }

    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> hashMap = new HashMap<>();

        int[] temp = new int[26];
        for(int k= 0;k<26;k++){
            temp[k]=0;
        }
        for (String str : strs) {
            for(int k= 0;k<26;k++){
                temp[k]=0;
            }
            for(int i=0;i<str.length();i++){
                int index = str.charAt(i)-'a';
                temp[index]++;
            }

            StringBuffer k = new StringBuffer();
            for(int s=0;s<temp.length;s++){
                char t = (char) (s+'a');
                char t2= (char) (temp[s]+'0');
                k.append(t).append(t2);
            }

            String key = k.toString();
            if(hashMap.containsKey(key)){
                List<String> list = hashMap.get(key);
                list.add(str);
            }else{
                List<String> list2 = new ArrayList<String>();
                list2.add(str);
                hashMap.put(key,list2);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> list : hashMap.values()) {
            result.add(list);
        }
        return result;
    }
}
