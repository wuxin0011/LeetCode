package com.wuxin.utils;

import java.util.StringJoiner;

/**
 * @author: wuxin0011
 * @Description: 二叉树
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this(val, null, null);
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TreeNode.class.getSimpleName() + "[", "]")
                .add("val=" + val)
                .toString();
    }


    public static TreeNode fullTreeNode() {
        // 第一层节点
        TreeNode treeNode = new TreeNode(0);

        // 第二层节点
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(2);

        // 第三层节点
        treeNode.left.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(4);

        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(6);

        // 第四层次节点
        treeNode.left.left.left = new TreeNode(7);
        treeNode.left.left.right = new TreeNode(8);

        treeNode.left.right.left = new TreeNode(9);
        treeNode.left.right.right = new TreeNode(10);

        treeNode.right.left.left = new TreeNode(11);
        treeNode.right.left.right = new TreeNode(12);

        treeNode.right.right.left = new TreeNode(13);
        treeNode.right.right.right = new TreeNode(14);

        return treeNode;
    }


   
}
