package leetcode.everyday;

import leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: 二叉树的锯齿形层序遍历
 */
public class Code_0001_103 {

    public static void main(String[] args) {
        Integer[] ls = {3, 9, 20, null, null, 15, 7};
        TreeNode treeNode = TreeNode.widthBuildTreeNode(ls);
        Code_0001_103 solution = new Code_0001_103();
        System.out.println(solution.zigzagLevelOrder(treeNode));
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Deque<TreeNode> q = new ArrayDeque<>();
        boolean f = true;
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> l = new ArrayList<>();
            while (size > 0) {
                size--;
                TreeNode node = q.poll();
                if (node == null) continue;
                if (f) {
                    l.add(node.val);
                } else {
                    l.add(0, node.val);
                }
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            f = !f;
            ans.add(l);
        }
        return ans;
    }
}
