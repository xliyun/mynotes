package com.liyun.leetcodes.L00101;

import com.liyun.leetcodes.LeeUtils;
import com.liyun.leetcodes.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-20 16:10
 */
public class SymmetricTree {
    public static void main(String[] args) {
        SymmetricTree symmetricTree = new SymmetricTree();
        int[] arr = {1,2,2,3,4,4,3};
        //int[] arr1 = {1,2,2,null,3,null,3};
        TreeNode root = LeeUtils.createTree(arr);
        symmetricTree.isSymmetric(root);
    }

    public boolean isSymmetric(TreeNode root) {
        if(root==null)
            return true;

        List<TreeNode> oneList= new ArrayList<>();

        oneList.add(root.left);
        oneList.add(root.right);
        return levelTraversal(oneList);
    }

    public boolean levelTraversal(List<TreeNode> arrayList){
        List<TreeNode> child = new ArrayList<>();
        int size = arrayList.size();
        int ha = size/2;
        for(int i=0;i<ha;i++){
            if(arrayList.get(i) ==null && arrayList.get(size-i-1)==null){

            }else if(arrayList.get(i)!=null && arrayList.get(size-i-1)!=null && arrayList.get(i).val==arrayList.get(size-i-1).val){
                child.add(arrayList.get(i).left);
                child.add(arrayList.get(i).right);
            }else{
                return false;
            }
        }

        for(int j=ha;j<size;j++){
            if(arrayList.get(j)!=null){
                child.add(arrayList.get(j).left);
                child.add(arrayList.get(j).right);
            }
        }

        if(child.size()>0){
          return   levelTraversal(child);
        }

        return true;
    }
}
