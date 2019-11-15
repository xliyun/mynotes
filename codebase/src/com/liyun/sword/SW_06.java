package com.liyun.sword;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-05-06 12:25
 */
public class SW_06 {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val=x;}

        public int getVal() {
            return val;
        }

    }

    public static void main(String[] args) {
        int[] pre={1,2,4,7,3,5,6,8};
        int[] mid={4,7,2,1,5,3,8,6};

        TreeNode root=reConstructBinaryTree(pre,mid);

        levelOrder(root);

    }

    public static TreeNode reConstructBinaryTree(int[] pre,int[] in){
        //判断是否为空和长短是否为0
        if(pre==null || in==null){
            return null;
        }
        if(pre.length==0 || in.length==0)
            return null;

        TreeNode root=new TreeNode(pre[0]);
        for(int i=0;i<in.length;i++){
            if(pre[0]==in[i]){
                //左子树需要传递前序遍历和中序遍历 先放前序遍历
                root.left=reConstructBinaryTree(Arrays.copyOfRange(pre,1,i+1),Arrays.copyOfRange(in,0,i));
                //copyOfRange的from to to不包含在要复制的数组内
                root.right=reConstructBinaryTree(Arrays.copyOfRange(pre,i+1,pre.length),Arrays.copyOfRange(in,i+1,in.length));
            }
        }
        return root;
    }

    //层序遍历
    public static void levelOrder(TreeNode root){
        TreeNode temp;
        Queue<TreeNode> queue=new LinkedList<>();
        //queue.add()
        queue.offer(root);
        while(!queue.isEmpty()){
            //获取并移除
            temp=queue.poll();
            System.out.println(temp.getVal());
            if(null!=temp.left)
                queue.offer(temp.left);
            if(null!=temp.right)
                queue.offer(temp.right);

        }
    }
}
