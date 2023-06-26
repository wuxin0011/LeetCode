package com.wuxin.tree;

import com.wuxin.annotation.Description;
import com.wuxin.utils.LogarithmicDevice;
import com.wuxin.utils.Node;
import com.wuxin.utils.InvocationHandlerMethodTime;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author: wuxin0011
 * @Description: 二叉树宽度有限遍历
 */

public class WidthPrint implements LogarithmicDevice {

    public static void main(String[] args) {
        WidthPrint widthPrint = new WidthPrint();
        InvocationHandlerMethodTime.getRunTime(widthPrint);
    }


    /**
     * 使用宽度优先遍历 使用队列（LinkedList）
     *
     * @param head 头结点
     */
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

    /**
     * 获取最大宽度 （使用hashmap 和 LinkedList）
     *
     * @param head 头结点
     * @return 最大宽度
     */
    public int getMaxWidth(Node head) {
        if (head == null) {
            return 0;
        }
        // 存放节点的队列
        LinkedList<Node> queue = new LinkedList<>();
        // 使用hash表记录当前几点层级
        HashMap<Node, Integer> map = new HashMap<>();

        int max = 0;
        int curLevel = 1;
        int curLevelNodes = 0;
        queue.add(head);
        map.put(head, curLevel);
        while (!queue.isEmpty()) {
            Node pollNode = queue.poll();
            // 获取当前几点层级
            Integer level = map.get(pollNode);
            // 判断和当前层级是否一致
            if (level == curLevel) {
                // 当前节点nodes++
                curLevelNodes += 1;
            } else {
                // 更新max
                max = Math.max(max, curLevelNodes);
                // nodes重置
                curLevelNodes = 1;
                // 更新层级
                curLevel += 1;
            }

            // 存入节点 记录当前节点左右子树的节点层级
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

    /**
     * 获取最大宽度 （ LinkedList）
     *
     * @param head 头结点
     * @return 最大宽度
     */
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
            // 弹窗当前节点
            Node pollNode = queue.poll();
            // 最后一个入栈节点
            if (pollNode.left != null) {
                nextEndNode = pollNode.left;
                queue.add(pollNode.left);
            }
            // 最后一个入栈节点
            if (pollNode.right != null) {
                nextEndNode = pollNode.right;
                queue.add(pollNode.right);
            }
            // 取出的节点是否为末尾节点
            // 如果为末尾节点更新节点
            if (pollNode == curEndNode) {
                // 更新max
                max = Math.max(max, curLevelNodes);
                // 更新末尾节点
                curEndNode = nextEndNode;
                // 更新节点数
                curLevelNodes = 1;
                // null
                nextEndNode = null;
            } else {
                // 没有到达尾节点
                curLevelNodes++;
            }
        }
        return Math.max(max, curLevelNodes);

    }


    // 计算二叉树的高度
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = getHeight(node.left);
            int rightHeight = getHeight(node.right);

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // 打印指定层级的节点值
    private void printLevel(Node node, int level) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            System.out.print(node.val + " ");
        } else if (level > 1) {
            printLevel(node.left, level - 1);
            printLevel(node.right, level - 1);
        }
    }

    // 宽度优先遍历
    public void bfsTraversal() {
        Node root = Node.fullTreeNode();
        int height = getHeight(root);

        for (int i = 1; i <= height; i++) {
            printLevel(root, i);
        }
    }

    @Description("二叉树宽度优先遍历")
    @Override
    public void logarithmicDevice() {
        Node node = Node.fullTreeNode();
        WidthPrint widthPrint = new WidthPrint();
        System.out.println("使用队列实现宽度优先遍历");
        widthPrint.widthPrint(node);
        System.out.println("\n不使用任何数据结构打印的宽度优先遍历");
        widthPrint.bfsTraversal();
        System.out.println("\n使用队列和hashmap实现求最大宽度");
        System.out.println(widthPrint.getMaxWidth(node));
        System.out.println("\n只使用队列实现求最大宽度");
        System.out.println(widthPrint.getMaxWidth2(node));
    }
}
