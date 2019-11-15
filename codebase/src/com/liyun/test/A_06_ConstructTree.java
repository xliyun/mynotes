package com.liyun.test;

import sun.reflect.generics.tree.Tree;

import java.util.Arrays;

public class A_06_ConstructTree {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int xx){val=xx;}
    }
    public TreeNode reConstructBinaryTree(int pre[],int []in){
        if(pre==null || in==null)
            return null;

        TreeNode root=new TreeNode(pre[0]);
        for(int i=0;i<pre.length;i++){
            if(pre[0]==in[i]){
                root.left=reConstructBinaryTree(Arrays.copyOfRange(pre,1,i+1),Arrays.copyOfRange(in,0,i));
                        root.right=reConstructBinaryTree(Arrays.copyOfRange(pre,i+1,pre.length),Arrays.copyOfRange(in,i+1,in.length));
            }
        }
        return root;
    }
}
