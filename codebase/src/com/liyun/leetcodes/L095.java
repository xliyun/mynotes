package com.liyun.leetcodes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-11-12 16:11
 */


public class L095 {
    public List<TreeNode> generateTrees(int n) {
        if(n==0){
            return new LinkedList<>();
        }

        return getTrees(1,n);
    }

    private List<TreeNode> getTrees(int start, int end) {
        List<TreeNode> result=new LinkedList<>();
        if(start>end){
            return null;
        }
        if(start==end){
            result.add(new TreeNode(end));
            return result;
        }


        //递归的边界应该由for循环这里来确定，不然先定死了递归边界，后面循环 写起来麻烦
        for(int i=start;i<=end;i++) {

            //左子树
            List<TreeNode> left = getTrees(start, i - 1);

            //右子树
            List<TreeNode> right = getTrees(i + 1, end);

            //如果左子树为空，只遍历右子树  ,, 因为递归边界的问题，不存在左右子树都为空的情况
            if (left==null) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = null;
                    root.right = r;
                    result.add(root);
                }
            } else if (right==null) {
                for (TreeNode l : left) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = null;
                    result.add(root);
                }
            } else {//左右子树都不为空
                for (TreeNode l : left) {
                    for (TreeNode r : right) {
                        TreeNode root = new TreeNode(i);
                        root.left = l;
                        root.right = r;
                        result.add(root);
                    }
                }
            }
        }
        return result;
    }
}
