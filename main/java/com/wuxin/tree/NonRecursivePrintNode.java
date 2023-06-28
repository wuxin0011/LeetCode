package com.wuxin.tree;

import com.wuxin.annotation.Description;
import com.wuxin.utils.LogarithmicDevice;
import com.wuxin.utils.TreeNode;
import com.wuxin.utils.InvocationHandlerMethodTime;

import java.util.Stack;

/**
 * @author: wuxin0011
 * @Description: 非递归的方式输出二叉树的前序遍历 中序遍历，后续遍历
 */
@Description("\n非递归的方式输出二叉树的前序遍历 中序遍历，后续遍历")
public class NonRecursivePrintNode implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(new NonRecursivePrintNode());
    }


    /**
     * 非递归方式前序遍历 使用一个栈
     *
     * @param head 头结点
     */
    public void preOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode popTreeNode = stack.pop();
            System.out.print(popTreeNode.val + " ");

            if (popTreeNode.right != null) {
                stack.push(popTreeNode.right);
            }
            if (popTreeNode.left != null) {
                stack.push(popTreeNode.left);
            }
        }
        System.out.println();
    }

    /**
     * 非递归方式中序遍历 使用一个栈
     *
     * @param head 头结点
     */
    public void inOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (head != null || !stack.isEmpty()) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.print(head.val + " ");
                head = head.right;
            }
        }
        System.out.println();
    }


    /**
     * 非递归方式后序遍历 使用一个栈
     *
     * @param head 头结点
     */
    public void postOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        // 先压入头节点
        s1.push(head);
        while (!s1.isEmpty()) {
            // 弹出栈一
            TreeNode popTreeNode = s1.pop();
            // 放入栈二中
            s2.push(popTreeNode);
            // 弹出的节点有右节点
            if (popTreeNode.right != null) {
                s1.push(popTreeNode.left);
            }
            // 弹出的节点有左节点
            if (popTreeNode.left != null) {
                s1.push(popTreeNode.left);
            }
        }

        // 输出 s2 栈节点内容
        while (!s2.isEmpty()) {
            TreeNode pop = s2.pop();
            System.out.print(pop.val + " ");

        }
        System.out.println();
    }

    @Override
    public void logarithmicDevice() {
        RecursionPrintTreeNode recursionPrintTreeNode = new RecursionPrintTreeNode();
        NonRecursivePrintNode nonRecursivePrintNode = new NonRecursivePrintNode();

        TreeNode treeNode = TreeNode.fullTreeNode();

        System.out.println("==================先序遍历====================");
        System.out.println("递归方式");
        recursionPrintTreeNode.preOrder(treeNode);
        System.out.println("\n非递归方式");
        nonRecursivePrintNode.preOrder(treeNode);


        System.out.println("======================中序遍历==================");
        System.out.println("递归方式");
        recursionPrintTreeNode.inOrder(treeNode);
        System.out.println("\n非递归方式");
        nonRecursivePrintNode.inOrder(treeNode);

        System.out.println("======================后续遍历==================");
        System.out.println("递归方式");
        recursionPrintTreeNode.postOrder(treeNode);
        System.out.println("\n非递归方式");
        recursionPrintTreeNode.postOrder(treeNode);
    }
}
