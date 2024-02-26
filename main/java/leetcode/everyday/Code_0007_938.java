package leetcode.everyday;

import leetcode.utils.IoUtil;
import leetcode.utils.TreeNode;

/**
 * @author: wuxin0011
 * @Description: 二叉搜索树的范围和
 * @url https://leetcode.cn/problems/range-sum-of-bst/description/
 */
@SuppressWarnings("all")
public class Code_0007_938 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0007_938.class, "rangeSumBST", "./txt_file/Code_0007_938.txt");
    }


    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        } else if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        } else if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        } else if (root.val >= low && root.val <= high) {
            return root.val + rangeSumBST(root.right, low, high) + rangeSumBST(root.left, low, high);
        }
        return 0;
    }

}
