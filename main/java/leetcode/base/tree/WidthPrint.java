package leetcode.base.tree;

import code_generation.annotation.Description;
import code_generation.proxy.InvocationHandlerMethodTime;
import code_generation.LogarithmicDevice;
import code_generation.bean.TreeNode;

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
    public void widthPrint(TreeNode head) {
        if (head == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode pollTreeNode = queue.poll();
            System.out.print(pollTreeNode.val + " ");
            if (pollTreeNode.left != null) {
                queue.add(pollTreeNode.left);
            }
            if (pollTreeNode.right != null) {
                queue.add(pollTreeNode.right);
            }
        }

    }

    /**
     * 获取最大宽度 （使用hashmap 和 LinkedList）
     *
     * @param head 头结点
     * @return 最大宽度
     */
    public int getMaxWidth(TreeNode head) {
        if (head == null) {
            return 0;
        }
        // 存放节点的队列
        LinkedList<TreeNode> queue = new LinkedList<>();
        // 使用hash表记录当前几点层级
        HashMap<TreeNode, Integer> map = new HashMap<>();

        int max = 0;
        int curLevel = 1;
        int curLevelNodes = 0;
        queue.add(head);
        map.put(head, curLevel);
        while (!queue.isEmpty()) {
            TreeNode pollTreeNode = queue.poll();
            // 获取当前几点层级
            Integer level = map.get(pollTreeNode);
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
            if (pollTreeNode.left != null) {
                map.put(pollTreeNode.left, curLevel + 1);
                queue.add(pollTreeNode.left);
            }
            if (pollTreeNode.right != null) {
                map.put(pollTreeNode.right, curLevel + 1);
                queue.add(pollTreeNode.right);
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
    public int getMaxWidth2(TreeNode head) {
        if (head == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        int max = 1;
        int curLevelNodes = 0;
        // 下一个一个节点
        TreeNode nextEndTreeNode = head;
        // 当前节点最后一个节点
        TreeNode curEndTreeNode = head;
        queue.add(head);
        while (!queue.isEmpty()) {
            // 弹窗当前节点
            TreeNode pollTreeNode = queue.poll();
            // 最后一个入栈节点
            if (pollTreeNode.left != null) {
                nextEndTreeNode = pollTreeNode.left;
                queue.add(pollTreeNode.left);
            }
            // 最后一个入栈节点
            if (pollTreeNode.right != null) {
                nextEndTreeNode = pollTreeNode.right;
                queue.add(pollTreeNode.right);
            }
            // 取出的节点是否为末尾节点
            // 如果为末尾节点更新节点
            if (pollTreeNode == curEndTreeNode) {
                // 更新max
                max = Math.max(max, curLevelNodes);
                // 更新末尾节点
                curEndTreeNode = nextEndTreeNode;
                // 更新节点数
                curLevelNodes = 1;
                // null
                nextEndTreeNode = null;
            } else {
                // 没有到达尾节点
                curLevelNodes++;
            }
        }
        return Math.max(max, curLevelNodes);

    }

    /**
     * 获取最大宽度 （ LinkedList）最值得推荐！！！！
     *
     * @param head 头结点
     * @return 最大宽度
     */
    public int getMaxWidth3(TreeNode head) {
        if (head == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        int max = 1;
        queue.add(head);
        while (!queue.isEmpty()) {
            int size = queue.size();
            max = Math.max(max, size);
            while (size > 0) {
                size--;
                TreeNode node = queue.poll();
                if (node == null) continue;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return max;

    }


    // 计算二叉树的高度
    private int getHeight(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        } else {
            int leftHeight = getHeight(treeNode.left);
            int rightHeight = getHeight(treeNode.right);

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // 打印指定层级的节点值
    private void printLevel(TreeNode treeNode, int level) {
        if (treeNode == null) {
            return;
        }
        if (level == 1) {
            System.out.print(treeNode.val + " ");
        } else if (level > 1) {
            printLevel(treeNode.left, level - 1);
            printLevel(treeNode.right, level - 1);
        }
    }

    // 宽度优先遍历
    public void bfsTraversal() {
        TreeNode root = TreeNode.fullTreeNode();
        int height = getHeight(root);

        for (int i = 1; i <= height; i++) {
            printLevel(root, i);
        }
    }

    @Description("二叉树宽度优先遍历")
    @Override
    public void logarithmicDevice() {
        TreeNode treeNode = TreeNode.fullTreeNode();
        WidthPrint widthPrint = new WidthPrint();
        System.out.println("使用队列实现宽度优先遍历");
        widthPrint.widthPrint(treeNode);
        System.out.println("\n不使用任何数据结构打印的宽度优先遍历");
        widthPrint.bfsTraversal();
        System.out.println("\n使用队列和hashmap实现求最大宽度");
        System.out.println(widthPrint.getMaxWidth(treeNode));
        System.out.println("\n只使用队列实现求最大宽度");
        System.out.println(widthPrint.getMaxWidth2(treeNode));
    }
}
