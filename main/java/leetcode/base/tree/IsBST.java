package leetcode.base.tree;

import leetcode.annotation.Description;
import leetcode.utils.*;

/**
 * @author: wuxin0011
 * @Description: 是否是搜索二叉树
 */
@Description("搜索二叉树和平衡二叉树")
public class IsBST implements LogarithmicDevice {

    public static void main(String[] args) {
         // InvocationHandlerMethodTime.getRunTime(IsBST.class);
        IoUtil.testUtil(IsBST.class, "isBalanced");
//        System.out.println("===========================");
//        IoUtil.testUtil(IsBST.class, "isBst");
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int l = getHeight(root.left);
        int r = getHeight(root.right);
        if (Math.abs(l - r) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.right), getHeight(root.left)) + 1;
    }

    public static int pre = Integer.MIN_VALUE;

    public boolean isSearch(TreeNode root) {

        if (root == null) {
            return true;
        }
        boolean isLeftIsBst = isSearch(root.left);
        if (!isLeftIsBst) {
            return false;
        }

        if (root.val <= pre) {
            return false;
        } else {
            pre = root.val;
        }

        return isSearch(root.right);
    }


    public boolean isSearch1(TreeNode root) {
        if( root == null ){
            return true;
        }
        return f(root,Long.MAX_VALUE,Long.MIN_VALUE);

    }

    public boolean f(TreeNode node, long min, long max) {
        if( node == null)
            return true;
        if( node.val <= min && node.val >= max){
            return false;
        }
        return f(node.left,max,node.val) && f(node.right,node.val,min);
    }


    @Override
    public void logarithmicDevice() {

        TreeNode treeNode = TreeNode.fullTreeNode();

        // test
        TestUtils.testBoolean(isSearch(treeNode), false, "ok");


        TreeNode treeNode1 = new TreeNode(5);
        treeNode1.left = new TreeNode(4);
        treeNode1.left.left = new TreeNode(3);
        treeNode1.left.left.left = new TreeNode(2);
        treeNode1.left.left.left.left = new TreeNode(1);
        treeNode1.right = new TreeNode(6);
        treeNode1.right.left = new TreeNode(4);
        treeNode1.right.right = new TreeNode(8);

        // test2
        TestUtils.testBoolean(isSearch(treeNode1), true, "ok");
        TestUtils.testBoolean(isSearch1(treeNode1), true, "ok");

        System.out.println("是否是搜索二叉树测试！");
    }
}
