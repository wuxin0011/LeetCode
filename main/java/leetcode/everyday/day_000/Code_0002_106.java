package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import code_generation.bean.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wuxin0011
 * @Description: 从中序与后序遍历序列构造二叉树
 * @url https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 */
@SuppressWarnings("all")
public class Code_0002_106 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0002_106.class, "buildTree", "./txt_file/Code_0003_106.txt");
    }

    private Map<Integer, Integer> d = new HashMap<>();
    private int[] postorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        int n = inorder.length;
        for (int i = 0; i < n; ++i) {
            d.put(inorder[i], i);
        }
        return dfs(0, 0, n);
    }

    private TreeNode dfs(int i, int j, int n) {
        if (n <= 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[j + n - 1]);
        int k = d.get(root.val);
        root.left = dfs(i, j, k - i);
        root.right = dfs(k + 1, j + k - i, n - k + i - 1);
        return root;
    }


    /**************************回顾下二叉树中序遍历和后续遍历**********************************/


    public static void inOrder(TreeNode node, List<Integer> in) {
        if (node == null) {
            return;
        }
        inOrder(node.left, in);
        in.add(node.val);
        inOrder(node.right, in);
    }


    public static void postOrder(TreeNode node, List<Integer> in) {
        if (node == null) {
            return;
        }
        postOrder(node.left, in);
        postOrder(node.right, in);
        in.add(node.val);
    }
}
