package com.liyun.leetcodes.L00102;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-20 15:29
 */
public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null){
            return result;
        }

        List<Integer> oneList = new ArrayList<>();
        oneList.add(root.val);
        result.add(oneList);
        LinkedList<TreeNode> l = new LinkedList<>();
        l.add(root);
        levelTraversal(result,l);
        return result;
    }

    public void levelTraversal(List<List<Integer>> result,LinkedList<TreeNode> linkedList){
        List<Integer> arryList = new ArrayList<>();
        LinkedList<TreeNode> l = new LinkedList<>();
        for (TreeNode treeNode : linkedList) {
            if(treeNode.left!=null){
               arryList.add(treeNode.left.val);
               l.add(treeNode.left);
            }
            if(treeNode.right!=null){
                arryList.add(treeNode.right.val);
                l.add(treeNode.right);
            }
        }

        if(arryList.size()>0){
            result.add(arryList);
            levelTraversal(result,l);
        }
    }
}
