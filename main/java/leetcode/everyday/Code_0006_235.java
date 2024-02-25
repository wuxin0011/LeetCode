package leetcode.everyday;

import leetcode.utils.IoUtil;
import leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: 二叉搜索树最近节点查询
 * @url https://leetcode.cn/problems/closest-nodes-queries-in-a-binary-search-tree/description/
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
