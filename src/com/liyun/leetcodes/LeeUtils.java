package com.liyun.leetcodes;

import java.util.ArrayList;
import java.util.List;

public class LeeUtils {

    //打印二维数组
    public  static void printTwoDimensionalArray(int[][] arr){
        for(int i=0;i<arr.length;i++){
           for(int j=0;j<arr[0].length;j++){
               System.out.print(arr[i][j]+" ");
           }
            System.out.println();
        }
    }

    /**
     * 数组构建二叉树
     * int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9} 变为二叉树为：
     *
     */
    public static List<TreeNode> treeNodelist = new ArrayList<TreeNode>();      //用一个集合来存放每一个Node
    public static TreeNode createTree(int[] array) {
        for (int i = 0; i < array.length; i++) {
            TreeNode node = new TreeNode(array[i]);  //创建结点，每一个结点的左结点和右结点为null
            node.left = null;
            node.right = null;

            treeNodelist.add(node); // list中存着每一个结点
        }
        // 构建二叉树
        if (treeNodelist.size() > 0) {
            for (int i = 0; i < array.length / 2 - 1; i++) {       // i表示的是根节点的索引，从0开始
                if (treeNodelist.get(2 * i + 1) != null) {
                    // 左结点
                    treeNodelist.get(i).left = treeNodelist.get(2 * i + 1);
                }
                if (treeNodelist.get(2 * i + 2) != null) {
                    // 右结点
                    treeNodelist.get(i).right = treeNodelist.get(2 * i + 2);
                }
            }
            // 判断最后一个根结点：因为最后一个根结点可能没有右结点，所以单独拿出来处理
            int lastIndex = array.length / 2 - 1;
            // 左结点
            treeNodelist.get(lastIndex).left = treeNodelist.get(lastIndex * 2 + 1);
            // 右结点，如果数组的长度为奇数才有右结点
            if (array.length % 2 == 1) {
                treeNodelist.get(lastIndex).right = treeNodelist.get(lastIndex * 2 + 2);
            }
        }
        return treeNodelist.get(0);
    }

    // 遍历，先序遍历打印二叉树
    public static void print(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            print(node.left);
            print(node.right);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        LeeUtils.createTree(array);
        LeeUtils.print(treeNodelist.get(0));
    }
}
