package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import code_generation.bean.TreeNode;

/**
 * @author: wuxin0011
 * @Description: 二叉搜索树的最近公共祖先
 * @url https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
@SuppressWarnings("all")
public class Code_0006_235 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0006_235.class, "lowestCommonAncestor", "./txt_file/Code_0006_235.txt");
    }




    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if( left != null && right != null){
            return root;
        }
        return left != null ? left : right;
    }


}
