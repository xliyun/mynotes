package com.liyun.leetcodes.L00104;

import com.liyun.leetcodes.TreeNode;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-21 9:34
 */
public class MaximumDepthofBinaryTree {
    public static void main(String[] args) {

    }

    public int deep = 0;
    public int maxDepth(TreeNode root) {
        if(root==null)
            return deep;

        LDR(root.left,1);
        LDR(root.right,1);
        return deep;
    }

    public void LDR(TreeNode node,int d){
        if(node==null){
            if(d>deep)
                deep = d;
        }else{
            LDR(node.left,d+1);
            LDR(node.right,d+1);
        }
    }
}
