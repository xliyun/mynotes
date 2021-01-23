package com.liyun.leetcodes.L00103;

import com.liyun.leetcodes.LeeUtils;
import com.liyun.leetcodes.TreeNode;
import com.sun.deploy.util.ArrayUtil;

import java.util.*;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-20 16:42
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    /**
     * [1,2,3,4,null,null,5]
     * [0,2,4,1,null,3,-1,5,1,null,6,null,8]
     */
    public static void main(String[] args) {
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        BinaryTreeZigzagLevelOrderTraversal binaryTreeZigzagLevelOrderTraversal = new BinaryTreeZigzagLevelOrderTraversal();
        List<List<Integer>> lists = binaryTreeZigzagLevelOrderTraversal.zigzagLevelOrder(root);
        System.out.println(lists.toString());
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        List<Integer> arr = new ArrayList<>();
        arr.add(root.val);
        result.add(arr);
        levelTraversal(result,list,false);
        return result;
    }


    public void levelTraversal(List<List<Integer>> result, List<TreeNode> arryList,boolean odd){
        List<Integer> arr = new ArrayList<>();
        List<TreeNode> treeNodeList = new ArrayList<>();
        //奇数层是顺序的
        if(odd){
            for(int i=0;i<arryList.size();i++){
                if(arryList.get(i)!=null){
                        if(arryList.get(i).left!=null){
                            treeNodeList.add(arryList.get(i).left);
                            arr.add(arryList.get(i).left.val);
                        }
                        if(arryList.get(i).right!=null){
                            treeNodeList.add(arryList.get(i).right);
                            arr.add(arryList.get(i).right.val);
                        }
                }
            }
        }else{
            for(int i=arryList.size()-1;i>=0;i--){
                if(arryList.get(i)!=null){
                        if(arryList.get(i).right!=null){
                            treeNodeList.add(arryList.get(i).right);
                            arr.add(arryList.get(i).right.val);
                        }
                        if(arryList.get(i).left!=null){
                            treeNodeList.add(arryList.get(i).left);
                            arr.add(arryList.get(i).left.val);
                        }
                    }
            }
        }

        if(treeNodeList.size()>0){
            result.add(arr);
            if(!odd)
            Collections.reverse(treeNodeList);
            levelTraversal(result,treeNodeList,!odd);
        }
    }
}
