package leetcode.everyday;

import leetcode.utils.IoUtil;
import leetcode.utils.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wuxin0011
 * @Description: 从前序与中序遍历序列构造二叉树
 * @url https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class Code_0002_105 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0002_105.class, "buildTree", "./txt_file/Code_0002_105.txt");
    }

    public Map<Integer,Integer> hashMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            // 存储对应节点 num
            hashMap.put(inorder[i],i);
        }
        return myBuildTree(preorder,inorder,0,n-1,0,n-1);
    }
    public TreeNode myBuildTree(int[] preOrder,int[] inOrder,int preOrder_left,int preOrder_right,int inOrder_left,int inOrder_right ){
        if (preOrder_left > preOrder_right) {
            return null;
        }
        // 找到前序遍历根节点的值
        int inorder_root = hashMap.get(preOrder[preOrder_left]);
        // 建立根节点
        TreeNode treeNode = new TreeNode(preOrder[preOrder_left]);
        // 得到左子树中的数目
        int inorder_left_size = inorder_root-inOrder_left;
        treeNode.left = myBuildTree(preOrder,inOrder,preOrder_left+1,preOrder_left+inorder_left_size,inOrder_left,inorder_root-1);
        treeNode.right = myBuildTree(preOrder,inOrder,preOrder_left+inorder_left_size+1,preOrder_right,inorder_root+1,inOrder_right);
        return treeNode;
    }







    /**************************回顾下二叉树的三种遍历方式**********************************/


    public static void preOrder(TreeNode node, List<Integer> in) {
        if (node == null) {
            return;
        }
        in.add(node.val);
        preOrder(node.left,in);
        preOrder(node.right,in);
    }


    public static void inOrder(TreeNode node, List<Integer> in) {
        if (node == null) {
            return;
        }
        inOrder(node.left,in);
        in.add(node.val);
        inOrder(node.right,in);
    }


    public static void postOrder(TreeNode node, List<Integer> in) {
        if (node == null) {
            return;
        }
        postOrder(node.left,in);
        postOrder(node.right,in);
        in.add(node.val);
    }
}
