package leetcode.contest.weekly.w_300.w_397.d;

import code_generation.utils.IoUtil;

import java.util.Arrays;

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
 * @Description: 状压缩DP
 * @url: https://leetcode.cn/contest/weekly-contest-397/problems/find-the-minimum-cost-array-permutation
 * @title: 找出分数最低的排列
 */
public class D {


    /**
     * 记忆化搜索解法
     */
    public static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "findPermutation", "D.txt");
        }

        int n;
        int[] ans;
        int[][] memo;
        int[] arr;

        public int[] findPermutation(int[] arr) {
            this.n = arr.length;
            this.arr = arr;
            this.memo = new int[n][1 << n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(this.memo[i], -1);
            }
            this.ans = new int[n];
            build(0, 0, 1); // 第一个必须填写0
            return ans;

        }

        private int dfs(int pre, int mask) {
            if (mask == (1 << n) - 1) {
                // System.out.println("dfs mask end " + mask);`
                return Math.abs(pre - arr[0]);
            }
            if (memo[pre][mask] != -1) {
                return memo[pre][mask];
            }
            int res = Integer.MAX_VALUE;
            for (int k = 1; k < n; k++) {
                if ((mask >> k & 1) == 1) {
                    continue;
                }
                res = Math.min(res, dfs(k, mask | (1 << k)) + Math.abs(pre - arr[k]));
            }
            memo[pre][mask] = res;
            return res;
        }

        private void build(int idx, int pre, int mask) {
            ans[idx] = pre;
            if (mask == (1 << n) - 1) {
                // System.out.println("mask = " + mask + ",ans = " + Arrays.toString(ans));
                return;
            }
            int res = dfs(pre, mask);
            for (int k = 1; k < n; k++) {
                if ((mask >> k & 1) == 1) {
                    continue;
                }
                int val = dfs(k, mask | (1 << k)) + Math.abs(pre - arr[k]);
                if (res == val) {
                    build(idx + 1, k, mask | (1 << k));
                    break;
                }
            }

        }
    }

    /**
     * 递推解法
     */
    public static class S2 {

        public static void main(String[] args) {
            IoUtil.testUtil(S2.class, "findPermutation", "D.txt");
        }


        public int[] findPermutation(int[] arr) {
            // TODO 递推实现
            int n = arr.length;
            int[][] dp = new int[n][1 << n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            for (int k = 0; k < n; k++) {
                dp[k][(1 << n) - 1] = Math.abs(k - arr[0]);
            }

            for (int mask = (1 << n) - 2; mask >= 0; mask -= 2) {
                for (int i = 0; i < n; i++) {
                    // dp[k][mask] = Math.min(dp[k][mask], dp[k][mask | (1 << k)] + Math.abs(k - arr[k]));
                    if ((mask >> i & 1) == 0) {
                        continue;
                    }
                    for (int k = 0; k < n; k++) {
                        if ((mask >> k & 1) == 1) {
                            continue;
                        }

                    }

                }
            }

            int[] ans = new int[n];

            return ans;

        }
    }

}