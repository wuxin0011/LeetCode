package leetcode.ox3if.dp.knapsack.a_01_knaspack.Solution_0000;

import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 2915. 和为目标值的最长子序列的长度
 * <p>
 * 给你一个下标从 0开始的整数数组nums和一个整数target。
 * 返回和为 target的 nums子序列中，子序列长度的最大值。如果不存在和为 target的子序列，返回 -1。
 * 子序列 指的是从原数组中删除一些或者不删除任何元素后，剩余元素保持原来的顺序构成的数组。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4,5], target = 9
 * 输出：3
 * 解释：总共有 3 个子序列的和为 9 ：[4,5] ，[1,3,5] 和 [2,3,4] 。最长的子序列是 [1,3,5] 和 [2,3,4] 。所以答案为 3 。
 * <p>
 * 示例 2：
 * 输入：nums = [4,1,3,2,1,5], target = 7
 * 输出：4
 * 解释：总共有 5 个子序列的和为 7 ：[4,3] ，[4,1,2] ，[4,2,1] ，[1,1,5] 和 [1,3,2,1] 。最长子序列为 [1,3,2,1] 。所以答案为 4 。
 * <p>
 * 示例 3：
 * 输入：nums = [1,1,5,4,5], target = 3
 * 输出：-1
 * 解释：无法得到和为 3 的子序列。
 * <p>
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * 1 <= target <= 1000
 *
 * @author: wuxin0011
 * @Description: 经典 01 背包 本题可变为求最小长度的子序列
 * @url: https://leetcode.cn/problems/length-of-the-longest-subsequence-that-sums-to-target
 * @title: 和为目标值的最长子序列的长度
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "lengthOfLongestSubsequence", "in.txt");
        IoUtil.testUtil(Solution.class, "lengthOfLongestSubsequence1", "in.txt");
        IoUtil.testUtil(Solution.class, "lengthOfLongestSubsequence2", "in.txt");
    }


    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int n = nums.size();
        int inf = 0x3fffff;
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -inf);
        // 为什么初始化为0 因为当为0 可以全都不选
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int c = target; c >= nums.get(i); c--) {
                dp[c] = Math.max(dp[c], dp[c - nums.get(i)] + 1);
            }
        }
        return dp[target] < 0 ? -1 : dp[target];
    }


    public int lengthOfLongestSubsequence1(List<Integer> nums, int target) {
        int n = nums.size();
        int inf = 0x3fffff;
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -inf);
        }
        // 为什么初始化为0 因为当为0 可以全都不选
        dp[0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int c = 0; c <= target; c++) {
                if (c < nums.get(i)) {
                    dp[i + 1][c] = dp[i][c];
                } else {
                    // 不选 容量不变 选 容量变小 + （内容 + 1 ）
                    dp[i + 1][c] = Math.max(dp[i][c], dp[i][c - nums.get(i)] + 1);
                }
            }
        }
        return dp[n][target] < 0 ? -1 : dp[n][target];
    }


    public int lengthOfLongestSubsequence2(List<Integer> nums, int target) {
        int n = nums.size();
        memo = new int[n][target + 1];
        this.values = nums;
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        int v = dfs(n - 1, target);
        return v < 0 ? -1 : v;
    }

    int[][] memo;

    List<Integer> values;
    public static int INF = 0x3fffff;

    // 完全背包
    public int dfs(int i, int c) {
        if (i < 0) {
            // c == 0 表示一种合法方案
            return c == 0 ? 0 : -INF;
        }
        if (memo[i][c] != -1) {
            return memo[i][c];
        }
        int res = 0;
        if (c < values.get(i)) {
            res = dfs(i - 1, c);
        } else {
            // 01背包 不选 容量不变 选 容量减小 价值 + 1 // dfs(i,c - values.get(i);
            res = Math.max(dfs(i - 1, c), dfs(i - 1, c - values.get(i)) + 1);
        }
        memo[i][c] = res;
        return res;
    }


}