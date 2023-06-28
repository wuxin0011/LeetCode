package com.wuxin.tree;

import com.wuxin.annotation.Description;
import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.LogarithmicDevice;
import com.wuxin.utils.TreeNode;

/**
 * @author: wuxin0011
 * @Description: 递归方式前序遍历后续遍历 中序遍历
 */
@Description("\n递归方式前序遍历后续遍历 中序遍历")
public class RecursionPrintTreeNode implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(RecursionPrintTreeNode.class);
    }

    /**
     * 递归方式的前序遍历2
     *
     * @param head 头结点
     */
    public void preOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        preOrder(head.left);
        preOrder(head.right);
    }

    /**
     * 递归方式中序遍历
     *
     * @param head 头结点
     */
    public void inOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        inOrder(head.left);
        System.out.print(head.val + " ");
        inOrder(head.right);
    }

    /**
     * 递归方式 后序遍历
     *
     * @param head 头结点
     */
    public void postOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        postOrder(head.left);
        postOrder(head.right);
        System.out.print(head.val + " ");
    }


    @Override
    public void logarithmicDevice() {
        TreeNode treeNode = TreeNode.fullTreeNode();
        RecursionPrintTreeNode recursionPrintTreeNode = new RecursionPrintTreeNode();
        System.out.println("前序遍历");
        recursionPrintTreeNode.preOrder(treeNode);
        System.out.println("\n中序遍历");
        recursionPrintTreeNode.inOrder(treeNode);
        System.out.println("\n后续遍历");
        recursionPrintTreeNode.postOrder(treeNode);
    }
}
