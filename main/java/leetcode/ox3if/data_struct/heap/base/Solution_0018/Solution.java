package leetcode.ox3if.data_struct.heap.base.Solution_0018;

import code_generation.bean.TreeNode;
import code_generation.utils.IoUtil;

import java.util.TreeSet;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/QO5KpG
 * @title: 二叉搜索树染色
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "getNumber", "in.txt");
    }


    // 分析
    // 二叉搜索树，使用中序遍历 转换为有序数组
    // 可以从后面遍历 因为最终是返回最后一次填充
    // 可以将最后一次的数据从数组中删除
    // 返回范围内数据 可以使用 红黑树 TreeSet
    TreeSet<Integer> set = new TreeSet<>();

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        set.add(node.val);
        dfs(node.right);
    }


    public int getNumber(TreeNode root, int[][] ops) {
        int cnt = 0, n = ops.length;
        dfs(root);
        for (int i = n - 1; i >= 0; i--) {
            int[] op = ops[i];
            TreeSet<Integer> o = new TreeSet<>(set.subSet(op[1] - 1, false, op[2] + 1, false));
            if (o.size() == 0) continue;
            if (op[0] == 1) {
                cnt += o.size();
            }
            for (Integer idx : o) {
                set.remove(idx);
            }
        }
        return cnt;
    }


}