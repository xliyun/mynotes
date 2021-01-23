package com.liyun.leetcodes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-11-12 15:53
 */

public class L094 {
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root==null){
            return new LinkedList<>();
        }
        List<Integer> result=new LinkedList<>();

        if(root.left!=null){
            result.addAll(inorderTraversal(root.left));
        }
        result.add(root.val);
        if(root.right!=null){
            result.addAll(inorderTraversal(root.right));
        }

        return result;
    }
}
