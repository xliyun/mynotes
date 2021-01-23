package com.liyun.leetcodes.L00105;

import com.liyun.leetcodes.TreeNode;

import java.util.Arrays;

/**
 * @description: 从前序与中序遍历序列构造二叉树
 * @author: xiaoliyu
 * @date: 2021-01-21 9:46
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal {
    public static void main(String[] args) {
        ConstructBinaryTreefromPreorderandInorderTraversal c = new ConstructBinaryTreefromPreorderandInorderTraversal();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        c.buildTree(preorder,inorder);
    }

    //前序遍历preorder 第一个就是根节点
    //中序遍历根节点左面的就是左子树
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0 || inorder.length==0)
            return null;
        TreeNode root = new TreeNode(preorder[0]);
        //找根节点位置
        for(int i=0;i<inorder.length;i++){
            if(inorder[i]==root.val){
                //中序编译从根节点一分为二，
                int[] leftTree = Arrays.copyOf(inorder ,i);
                int[] rightTree = Arrays.copyOfRange(inorder,i+1,inorder.length);
                //前序遍历i表示左子树有多少节点，所以这个长度就是左子树的个数
                int[] preorderLeft = Arrays.copyOfRange(preorder,1,i+1);
                int[] preorderRight = Arrays.copyOfRange(preorder,i+1,preorder.length);
                root.left = buildTree(preorderLeft,leftTree);
                root.right = buildTree(preorderRight,rightTree);
                break;
            }
        }
        return root;
    }


}
