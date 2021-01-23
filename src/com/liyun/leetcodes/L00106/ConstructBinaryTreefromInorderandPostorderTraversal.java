package com.liyun.leetcodes.L00106;

import com.liyun.leetcodes.L00105.ConstructBinaryTreefromPreorderandInorderTraversal;
import com.liyun.leetcodes.TreeNode;

import java.util.Arrays;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-21 11:10
 */
public class ConstructBinaryTreefromInorderandPostorderTraversal {
    public static void main(String[] args) {
        ConstructBinaryTreefromInorderandPostorderTraversal c = new ConstructBinaryTreefromInorderandPostorderTraversal();
        int[] inorder = {9,3,15,20,7};
        int[] preorder = {9,15,7,20,3};

        c.buildTree(inorder,preorder);
    }

    //后序遍历preorder 第一个就是根节点
    //中序遍历根节点左面的就是左子树
    public TreeNode buildTree( int[] inorder,int[] preorder) {
        if(preorder.length==0 || inorder.length==0)
            return null;
        TreeNode root = new TreeNode(preorder[preorder.length-1]);
        //找根节点位置
        for(int i=0;i<inorder.length;i++){
            if(inorder[i]==root.val){
                //中序编译从根节点一分为二，
                int[] leftTree = Arrays.copyOf(inorder ,i);
                int[] rightTree = Arrays.copyOfRange(inorder,i+1,inorder.length);
                //后序遍历i表示左子树有多少节点，所以这个长度就是左子树的个数
                int[] preorderLeft = Arrays.copyOfRange(preorder,0,i);

                int[] preorderRight = Arrays.copyOfRange(preorder,i,preorder.length-1);
                root.left = buildTree(leftTree,preorderLeft);
                root.right = buildTree(rightTree,preorderRight);
                break;
            }
        }
        return root;
    }

}
