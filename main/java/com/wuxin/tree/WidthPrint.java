package com.wuxin.tree;

import com.wuxin.utils.Node;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author: wuxin0011
 * @Description: 二叉树宽度有限遍历
 */
public class WidthPrint {

    public static void main(String[] args) {
        Node node = Node.fullTreeNode();
        WidthPrint widthPrint = new WidthPrint();
        widthPrint.widthPrint(node);
        System.out.println("\nnodes");
        System.out.println(widthPrint.getMaxWidth(node));
        System.out.println(widthPrint.getMaxWidth2(node));
    }

    public void widthPrint(Node head) {
        if (head == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node pollNode = queue.poll();
            System.out.print(pollNode.val + " ");
            if (pollNode.left != null) {
                queue.add(pollNode.left);
            }
            if (pollNode.right != null) {
                queue.add(pollNode.right);
            }
        }

    }

    public int getMaxWidth(Node head) {
        if (head == null) {
            return 0;
        }
        LinkedList<Node> queue = new LinkedList<>();
        HashMap<Node, Integer> map = new HashMap<>();

        int max = 0;
        int curLevel = 1;
        int curLevelNodes = 0;

        queue.add(head);
        map.put(head, curLevel);
        while (!queue.isEmpty()) {
            Node pollNode = queue.poll();
            Integer level = map.get(pollNode);
            if (level == curLevel) {
                curLevelNodes+=1;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 1;
                curLevel += 1;
            }

            if (pollNode.left != null) {
                map.put(pollNode.left, curLevel + 1);
                queue.add(pollNode.left);
            }
            if (pollNode.right != null) {
                map.put(pollNode.right, curLevel + 1);
                queue.add(pollNode.right);
            }
        }
        return Math.max(max, curLevelNodes);

    }

    public int getMaxWidth2(Node head) {
        if (head == null) {
            return 0;
        }
        LinkedList<Node> queue = new LinkedList<>();
        int max = 1;
        int curLevelNodes = 0;
        // 下一个一个节点
        Node nextEndNode = head;
        // 当前节点最后一个节点
        Node curEndNode = head;

        queue.add(head);
        while (!queue.isEmpty()) {
            Node pollNode = queue.poll();
            if (pollNode.left != null) {
                // 最后一个入栈节点
                nextEndNode = pollNode.left;
                queue.add(pollNode.left);
            }
            if (pollNode.right != null) {
                // 最后一个入栈节点
                nextEndNode = pollNode.right;
                queue.add(pollNode.right);
            }
            // 取出的节点是否为末尾节点
            // 如果为末尾节点更新节点
            if( pollNode == curEndNode ){
                // 更新max
                max = Math.max(max,curLevelNodes);
                // 更新末尾节点
                curEndNode = nextEndNode;
                // 更新节点数
                curLevelNodes = 1;
                // null
                nextEndNode = null;
            }else{
                // 没有到达尾节点
                curLevelNodes++;
            }
        }
        return Math.max(max, curLevelNodes);

    }
}
