package leetcode.ox3if.data_struct.pre_sum.hash.hash_0005;

import code_generation.bean.TreeNode;
import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 437. 路径总和 III
 * <p>
 * 给定一个二叉树的根节点 root，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 示例 1：
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * <p>
 * 示例 2：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 * <p>
 * 提示:
 * 二叉树的节点个数的范围是 [0,1000]
 * -10^9<= Node.val <= 10^9
 * -1000<= targetSum<= 1000
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/path-sum-iii
 * @title: path-sum-iii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "pathSum", "in.txt");
    }

    int ans = 0;

    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(0L, 1); // 出现 0
        dfs(root,0,targetSum,map);
        return ans;
    }

    public void dfs(TreeNode node, long sum, int target, Map<Long, Integer> map) {
        if (node == null) {
            return;
        }
        sum += node.val; // 注意溢出
        ans += map.getOrDefault(sum * 1L - target * 1L, 0);
        int cnt = map.getOrDefault(sum * 1L, 0) + 1;
        map.put(sum * 1L, cnt);
        dfs(node.left, sum, target, map);
        dfs(node.right, sum, target, map);
        map.put(sum * 1L, cnt - 1); // 题目要求不必从根节点出发 因此需要回复现场
    }


}