package com.wuxin.tree;

import com.wuxin.annotation.Description;
import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.LogarithmicDevice;
import com.wuxin.utils.TestUtils;
import com.wuxin.utils.TreeNode;

/**
 * @author: wuxin0011
 * @Description: 是否是搜索二叉树
 */
@Description("搜索二叉树")
public class IsBST implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(IsBST.class);
    }

    public static int pre = Integer.MIN_VALUE;

    public boolean isBst(TreeNode root) {

        if (root == null) {
            return true;
        }
        boolean isLeftIsBst = isBst(root.left);
        if (!isLeftIsBst) {
            return false;
        }

        if (root.val <= pre) {
            return false;
        } else {
            pre = root.val;
        }

        return isBst(root.right);
    }


    @Override
    public void logarithmicDevice() {

        TreeNode treeNode = TreeNode.fullTreeNode();

        // test
        TestUtils.testBoolean(isBst(treeNode), false, "ok");


        TreeNode treeNode1 = new TreeNode(5);
        treeNode1.left = new TreeNode(4);
        treeNode1.left.left = new TreeNode(3);
        treeNode1.left.left.left = new TreeNode(2);
        treeNode1.left.left.left.left = new TreeNode(1);
        treeNode1.right = new TreeNode(6);
        treeNode1.right.left = new TreeNode(4);
        treeNode1.right.right = new TreeNode(8);

        // test2
        TestUtils.testBoolean(isBst(treeNode1), true, "ok");

        System.out.println("是否是搜索二叉树测试！");
    }
}
