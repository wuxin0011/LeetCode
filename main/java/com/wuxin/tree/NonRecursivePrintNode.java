package com.wuxin.tree;

import com.wuxin.utils.Node;

import java.util.Stack;

/**
 * @author: wuxin0011
 * @Description: 非递归的方式输出二叉树的前序遍历 中序遍历，后续遍历
 */
public class NonRecursivePrintNode {

    public static void main(String[] args) {

        RecursionPrintTreeNode recursionPrintTreeNode = new RecursionPrintTreeNode();
        NonRecursivePrintNode nonRecursivePrintNode = new NonRecursivePrintNode();

        Node node = Node.fullTreeNode();

        System.out.println("==================先序遍历====================");
        System.out.println("递归方式");
        recursionPrintTreeNode.preOrder(node);
        System.out.println("\n非递归方式");
        nonRecursivePrintNode.preOrder(node);


        System.out.println("======================中序遍历==================");
        System.out.println("递归方式");
        recursionPrintTreeNode.inOrder(node);
        System.out.println("\n非递归方式");
        nonRecursivePrintNode.inOrder(node);

        System.out.println("======================后续遍历==================");
        System.out.println("递归方式");
        recursionPrintTreeNode.postOrder(node);
        System.out.println("\n非递归方式");
        recursionPrintTreeNode.postOrder(node);


    }


    public void preOrder(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node popNode = stack.pop();
            System.out.print(popNode.val + " ");

            if (popNode.right != null) {
                stack.push(popNode.right);
            }
            if (popNode.left != null) {
                stack.push(popNode.left);
            }
        }
        System.out.println();
    }

    public void inOrder(Node head) {
        if( head == null ){
            return;
        }
        Stack<Node> stack = new Stack<>();
        while( head != null || !stack.isEmpty() ){
            if( head != null ){
                stack.push(head);
                head = head.left;
            }else{
                head = stack.pop();
                System.out.print(head.val + " ");
                head = head.right;
            }
        }
        System.out.println();
    }

    public void postOrder(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        // 先压入头节点
        s1.push(head);
        while (!s1.isEmpty()) {
            // 弹出栈一
            Node popNode = s1.pop();
            // 放入栈二中
            s2.push(popNode);
            // 弹出的节点有右节点
            if( popNode.right != null){
                s1.push(popNode.left);
            }
            // 弹出的节点有左节点
            if( popNode.left != null){
                s1.push(popNode.left);
            }

        }

        // 输出 s2 栈节点内容
        while (!s2.isEmpty()){
            Node pop = s2.pop();
            System.out.print(pop.val + " ");

        }
        System.out.println();
    }

}
