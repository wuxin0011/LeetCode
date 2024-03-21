package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import code_generation.bean.TreeNode;

/**
 * @author: wuxin0011
 * @Description: 根据前序和后序遍历构造二叉树
 * @url https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 */
@SuppressWarnings("all")
public class Code_0003_107 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0003_107.class, "constructFromPrePost", "./txt_file/Code_0003_107.txt");
    }


    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;
        int[] index = new int[n + 1];
        for (int i = 0; i < n; i++) {
            index[postorder[i]] = i;
        }
        return dfs(preorder, 0, n, postorder, 0, n, index); // 左闭右开区间
    }

    private TreeNode dfs(int[] preorder, int preL, int preR, int[] postorder, int postL, int postR, int[] index) {
        if (preL == preR) { // 空节点
            return null;
        }
        if (preL + 1 == preR) { // 叶子节点
            return new TreeNode(preorder[preL]);
        }
        int leftSize = index[preorder[preL + 1]] - postL + 1; // 左子树的大小
        TreeNode left = dfs(preorder, preL + 1, preL + 1 + leftSize, postorder, postL, postL + leftSize, index);
        TreeNode right = dfs(preorder, preL + 1 + leftSize, preR, postorder, postL + leftSize, postR - 1, index);
        return new TreeNode(preorder[preL], left, right);
    }



}
