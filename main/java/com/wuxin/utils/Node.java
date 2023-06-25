package com.wuxin.utils;

import java.util.StringJoiner;

/**
 * @author: wuxin0011
 * @Description: 二叉树
 */
public class Node {

    public int val;
    public Node left;
    public Node right;

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                .add("val=" + val)
                .toString();
    }


    public static Node fullTreeNode() {
        // 第一层节点
        Node node = new Node(0);

        // 第二层节点
        node.left = new Node(1);
        node.right = new Node(2);

        // 第三层节点
        node.left.left = new Node(3);
        node.left.right = new Node(4);
        
        node.right.left = new Node(5);
        node.right.right = new Node(6);

        // 第四层次节点
        node.left.left.left = new Node(7);
        node.left.left.right = new Node(8);

        node.left.right.left = new Node(9);
        node.left.right.right = new Node(10);

        node.right.left.left = new Node(11);
        node.right.left.right = new Node(12);

        node.right.right.left = new Node(13);
        node.right.right.right = new Node(14);

        return node;
    }


   
}
