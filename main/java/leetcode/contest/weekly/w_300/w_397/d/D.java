package leetcode.contest.weekly.w_300.w_397.d;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 100312. 找出分数最低的排列
 * <p>
 * <p>
 * 给你一个数组 nums ，它是 [0, 1, 2, ..., n - 1] 的一个排列 。
 * 对于任意一个 [0, 1, 2, ..., n - 1] 的排列 perm ，其 分数 定义为：
 * score(perm) = |perm[0] - nums[perm[1]]| + |perm[1] - nums[perm[2]]| + ... + |perm[n - 1] - nums[perm[0]]|
 * 返回具有 最低 分数的排列 perm 。如果存在多个满足题意且分数相等的排列，则返回其中字典序最小的一个。
 * <p>
 * 示例 1：
 * 输入：nums = [1,0,2]
 * 输出：[0,1,2]
 * 解释：
 * 字典序最小且分数最低的排列是 [0,1,2]。这个排列的分数是 |0 - 0| + |1 - 2| + |2 - 1| = 2 。
 * <p>
 * 示例 2：
 * 输入：nums = [0,2,1]
 * 输出：[0,2,1]
 * 解释：
 * 字典序最小且分数最低的排列是 [0,2,1]。这个排列的分数是 |0 - 1| + |2 - 2| + |1 - 0| = 2 。
 * <p>
 * 提示：
 * 2 <= n == nums.length <= 14
 * nums 是 [0, 1, 2, ..., n - 1] 的一个排列。
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-397/problems/find-the-minimum-cost-array-permutation
 * @title: 找出分数最低的排列
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "findPermutation", "D.txt");
    }


    public int[] findPermutation(int[] a) {
        int n = a.length;
        int[][] memo = new int[1 << n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        int[] ans = new int[n];
        makeAns(1, 0, a, memo, ans, 0);
        return ans;
    }

    private int dfs(int s, int j, int[] a, int[][] memo) {
        if (s == (1 << a.length) - 1) {
            return Math.abs(j - a[0]);
        }
        if (memo[s][j] != -1) { // 之前计算过
            return memo[s][j];
        }
        int res = Integer.MAX_VALUE;
        for (int k = 1; k < a.length; k++) {
            if ((s >> k & 1) == 0) { // k 之前没填过
                res = Math.min(res, dfs(s | 1 << k, k, a, memo) + Math.abs(j - a[k]));
            }
        }
        memo[s][j] = res; // 记忆化
        return res;
    }

    private void makeAns(int s, int j, int[] a, int[][] memo, int[] ans, int i) {
        ans[i] = j;
        if (s == (1 << a.length) - 1) {
            return;
        }
        int finalRes = dfs(s, j, a, memo);
        for (int k = 1; k < a.length; k++) {
            if ((s >> k & 1) == 0 && dfs(s | 1 << k, k, a, memo) + Math.abs(j - a[k]) == finalRes) {
                makeAns(s | 1 << k, k, a, memo, ans, i + 1);
                break;
            }
        }
    }

}