package leetcode.ox3if.dp.base_line.a_LCS.LCS_DP_0006;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/max-dot-product-of-two-subsequences
 * @title: 两个子序列的最大点积
 */
public class Solution {

    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "maxDotProduct", "in.txt");
        }


        public int maxDotProduct(int[] a, int[] b) {
            int res = Integer.MIN_VALUE;
            int m = a.length, n = b.length;
            int[][] f = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    res = max(res, a[i - 1] * b[j - 1] + f[i - 1][j - 1]);
                    f[i][j] = max(a[i - 1] * b[j - 1] + f[i - 1][j - 1], f[i - 1][j], f[i][j - 1]);
                }
            }
            return res;
        }


        public static int max(int... args) {
            int x = Integer.MIN_VALUE;
            for (int val : args) {
                if (val > x) {
                    x = val;
                }
            }
            return x;
        }
    }


    // 记忆化搜索版本
    static class S2 {
        public static void main(String[] args) {
            IoUtil.testUtil(S2.class, "maxDotProduct", "in.txt");
        }


        public int maxDotProduct(int[] a, int[] b) {
            this.a = a;
            this.b = b;
            int m = a.length, n = b.length;
            memo = new int[m][n][2];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    Arrays.fill(memo[i][j], -1);
                }
            }
            return dfs(m - 1, n - 1, 0);
        }


        int[] a, b;
        int[][][] memo;

        public int dfs(int i, int j, int u) {
            if (i < 0 || j < 0) {
                return u > 0 ? 0 : Integer.MIN_VALUE;
            }
            if (memo[i][j][u] != -1) {
                return memo[i][j][u];
            }
            int res = 0;
            res = max(dfs(i - 1, j - 1, 1) + a[i] * b[j], dfs(i - 1, j, u), dfs(i, j - 1, u));
            return memo[i][j][u] = res;
        }

        public static int max(int... args) {
            int x = Integer.MIN_VALUE;
            for (int val : args) {
                if (val > x) {
                    x = val;
                }
            }
            return x;
        }
    }


}