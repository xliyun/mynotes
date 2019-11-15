package com.liyun.leetcodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-11-14 14:13
 */
/*这个题主要有三种情况，遍历字符串数组时，
  在当前一行加入一个新的单词，正好将当前行填满
  在当前一行加入一个新的单词，超出了maxWidth，就要分配剩下的空格
  在当前一行加入一个新的单词，没有超出
  注意，每一行第一个单词计算长度的时候不用放空格
* */
public class L068 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        ArrayList<String> result = new ArrayList<>();

        //记录当前行的起点
        int start=0;
        //记录当前行已经放进几个单词去了
        int size=0;
        //遍历输入的字符串数组

        //记录每一行的长度
        int lineWidth=0;

        for(int i=0;i<words.length;){
            //第一种情况，加一个空格正好将一行填满
            if(lineWidth+words[i].length()==maxWidth){
                result.add(buildString(start,size,words,0));

                start=start+1;//新一行的起点是下一个单词了
                size=0;//单词个数加一
                lineWidth=0;
                i++;
            }
            //第二种情况，在当前一行加入一个新的单词，超出了maxWidth，就要分配剩下的空格
            else if(lineWidth+words[i].length()>maxWidth){
                result.add(buildString(start,size,words,maxWidth-lineWidth));
                start=start+1;//新一行的起点是下一个单词了
                size=0;//单词个数加一
                lineWidth=0;
                i++;
            //第三种情况，在当前一行加入一个新的单词，没有超出
            }else if(lineWidth+words[i].length()<maxWidth){
                start=start+1;
                size=size+1;
                lineWidth=lineWidth+words[i].length()+1;
            }

        }
        return result;
    }
    //通过新的一行起点，和要放入的单词的个数，以及剩下的空格数，
    public String buildString(int start,int size,String[] words,int blank_space){

        //TODO
        return "";
    }

    public static void main(String[] args) {
        L068 l068 = new L068();
        String[] words= {"This", "is", "an", "example", "of", "text", "justification."};
        List<String> list = l068.fullJustify(words, 16);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
