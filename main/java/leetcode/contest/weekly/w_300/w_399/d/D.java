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


    /**
     * 这种解法已经超时了！！
     */
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


    /**
     * 线段树版本
     */
    static class S3 {
        public static void main(String[] args) {
            IoUtil.testUtil(S3.class, "maximumSumSubsequence", "D.txt");
        }

        public static int MOD = (int) 1e9 + 7;


        public int maximumSumSubsequence(int[] nums, int[][] queries) {
            long ans = 0;
            this.n = nums.length;
            this.t = new long[n * 4 + 1][4];
            this.nums = nums;
            build(1, n, 1);
            for (int[] q : queries) {
                int index = q[0], val = q[1];
                update(1, n, 1, index + 1, val);
                ans += t[1][3]; // 获取线段树顶点 也就是整个区间的最大值 整个区间应该是都选 f(11)
                ans %= MOD;
            }
            return (int) (ans % MOD);
        }

        long[][] t;
        int[] nums;
        int n;


        void build(int l, int r, int i) {
            if (l == r) {
                t[i][3] = Math.max(this.nums[l - 1], 0);
                return;
            }
            int mid = l + ((r - l) >> 1);
            build(l, mid, i << 1);
            build(mid + 1, r, i << 1 | 1);
            up(i);
        }

        void update(int l, int r, int i, int index, int val) {
            if (l == r) {
                t[i][3] = Math.max(val, 0);
            } else {
                int mid = l + ((r - l) >> 1);
                // 修改那边的区间
                if (index <= mid) {
                    update(l, mid, i << 1, index, val);
                } else {
                    update(mid + 1, r, i << 1 | 1, index, val);
                }
                up(i);
            }
        }


        // f00 不选择第一个区间的第一个 不选择第一个区间的区间最后一个
        // f01 不选择第一个区间的第一个 选择第一个区间的区间最后一个(可选或不选)
        // f10 选择第一个区间的区间第一个(可选或不选) 第二个不选
        // f11 选择第一个区间的区间第一个(可选或不选) 选择第二个区间的区间最后一个(可选或不选)

        void up(int i) {
            long[] a = t[i << 1];
            long[] b = t[i << 1 | 1];
            // max(fa(00) + fb(10),fa(01) + fb(00))
            t[i][0] = Math.max(a[0] + b[2], a[1] + b[0]);
            // max(fa(00) + fb(11),fa(01) + fb(01))
            t[i][1] = Math.max(a[0] + b[3], a[1] + b[1]);
            // max(fa(10) + fb(10),fa(11) + fb(00))
            t[i][2] = Math.max(a[2] + b[2], a[3] + b[0]);
            // max(fa(10) + fb(11),fa(11) + fb(01)
            t[i][3] = Math.max(a[2] + b[3], b[3] + b[1]);
        }

    }


}