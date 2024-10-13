package leetcode.contest.weekly.w_400.w_419.b;

import code_generation.bean.TreeNode;
import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-419/problems/k-th-largest-perfect-subtree-size-in-binary-tree
 * @title: 第 K 大的完美二叉子树的大小
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "kthLargestPerfectSubtree", "B.txt");
    }


    List<Integer> ls = new ArrayList<>();

    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        dfs(root);
        ls.sort((a,b)->b - a);
        if (k > ls.size()) {
            return -1;
        }
        return (1 << ls.get(k - 1)) - 1;
    }

    int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int lh = dfs(node.left);
        int rh = dfs(node.right);
        // -1 表示后代出现不是完美二叉树 那么这条树的父级节点都不是完美二叉树
        if (lh < 0 || rh < 0) {
            return -1;
        }
        // 通过左右子树高度表示二叉树个数(1 << n) - 1
        if (lh != rh) {
            return -1;
        }
        int cur = lh + 1;
        ls.add(cur);
        return cur;
    }



}