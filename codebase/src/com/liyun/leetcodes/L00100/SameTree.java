package com.liyun.leetcodes.L00100;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-20 14:50
 */
public class SameTree {
    /**
     测试用例
     [1,2]
     [1,null,2]
     ======
     []
     []
     ======
     [1,2,3,null,null,4,5]
     [1,2,3]
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
     if(p==null && q==null){
         return true;
     }else if(p==null || q==null){
         return false;
     }else if(p.val!=q.val){
         return false;
     }else{
         return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
     }
    }

}
