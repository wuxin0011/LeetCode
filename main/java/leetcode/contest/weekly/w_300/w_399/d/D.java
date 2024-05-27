package leetcode.contest.weekly.w_300.w_399.d;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-399/problems/maximum-sum-of-subsequence-with-non-adjacent-elements
 * @title: 不包含相邻元素的子序列的最大和
 */
public class D {


    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "maximumSumSubsequence", "D.txt");
        }


        // 先写个暴力

        public static int MOD = (int) 1e9 + 7;

        public int maximumSumSubsequence(int[] nums, int[][] queries) {

            int ans = 0;
            this.nums = nums;
            this.n = nums.length;
            memo = new int[n];
            for (int[] query : queries) {
                int pos = query[0];
                int x = query[1];
                nums[pos] = x;
                Arrays.fill(memo, -1);
                ans = (ans + dfs(n - 1)) % MOD;
            }

            return ans;
        }

        int[] nums;
        int n;

        int[] memo;

        public int dfs(int i) {
            if (i < 0) {
                return 0;
            }

            if (memo[i] != -1) {
                return memo[i];
            }
            int ans = Math.max(dfs(i - 1), dfs(i - 2) + nums[i]);
            memo[i] = ans;
            return ans;
        }
    }


    static class S2 {
        public static void main(String[] args) {
            IoUtil.testUtil(S2.class, "maximumSumSubsequence", "D.txt");
        }
        public static int MOD = (int) 1e9 + 7;

        public int maximumSumSubsequence(int[] nums, int[][] queries) {

            int n = nums.length;
            long[] dp = new long[n + 2];
            for (int i = 0; i < n; i++) {
                dp[i + 2] = Math.max(dp[i + 1], dp[i] + nums[i]);
            }
            long ans = 0;
            for (int[] query : queries) {
                int pos = query[0];
                nums[pos] = query[1];
                for (int i = pos; i < n; i++) {
                    dp[i + 2] = Math.max(dp[i + 1], dp[i] + nums[i]);
                }
                ans += dp[n + 1];
                ans %= MOD;
            }

            return (int) ans;
        }


    }


}