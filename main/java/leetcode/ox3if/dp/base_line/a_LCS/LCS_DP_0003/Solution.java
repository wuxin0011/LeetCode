package leetcode.ox3if.dp.base_line.a_LCS.LCS_DP_0003;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/edit-distance
 * @title: 编辑距离
 */
public class Solution {

    static class s2 {
        public static void main(String[] args) {
            // IoUtil.testUtil(s2.class, "minDistance", "in.txt");
            IoUtil.testUtil(s2.class, "minDistance1", "in.txt");
        }


        public int minDistance(String word1, String word2) {
            int m = word1.length(), n = word2.length();
            if (m == 0 || n == 0) {
                return Math.max(m, n);
            }
            int[][] f = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int res = 0;
                    if (word1.charAt(i) == word2.charAt(j)) {
                        res = dfs(i - 1, j - 1, f);
                    } else {
                        res = min(dfs(i - 1, j, f), dfs(i, j - 1, f), dfs(i - 1, j - 1, f)) + 1;
                    }
                    f[i][j] = res;
                }
            }
            return f[m - 1][n - 1];
        }


        public int dfs(int i, int j, int[][] f) {
            if (i < 0 || j < 0) {
                return Math.max(i, j) + 1;
            }
            return f[i][j];
        }


        public static int min(int... args) {
            int x = 0x3ffffff;
            for (int val : args) {
                if (val < x) {
                    x = val;
                }
            }
            return x;
        }

        public int minDistance1(String word1, String word2) {
            int m = word1.length(), n = word2.length();
            if (m == 0 || n == 0) {
                return Math.max(m, n);
            }
            int[][] f = new int[m + 1][n + 1];
            for (int i = 0; i <= m; i++) {
                f[i][0] = i;
            }
            for (int i = 0; i <= n; i++) {
                f[0][i] = i;
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (word1.charAt(i) == word2.charAt(j)) {
                        f[i + 1][j + 1] = f[i][j];
                    } else {
                        f[i + 1][j + 1] = min(f[i][j + 1], f[i + 1][j], f[i][j]) + 1;
                    }
                }
            }
            return f[m][n];
        }

    }


    static class s1 {

        public static void main(String[] args) {
            IoUtil.testUtil(s1.class, "minDistance", "in.txt");
        }

        public int minDistance(String word1, String word2) {
            int m = word1.length(), n = word2.length();
            memo = new int[m][n];
            this.word1 = word1;
            this.word2 = word2;
            for (int i = 0; i < m; i++) {
                Arrays.fill(memo[i], -1);
            }
            return dfs(m - 1, n - 1);
        }

        String word1, word2;
        int[][] memo;

        public int dfs(int i, int j) {
            if (i < 0 || j < 0) {
                return Math.max(i, j) + 1;
            }
            if (memo[i][j] != -1) {
                return memo[i][j];
            }
            int res = 0;
            if (word1.charAt(i) == word2.charAt(j)) {
                res = dfs(i - 1, j - 1);
            } else {
                res = min(dfs(i - 1, j), dfs(i, j - 1), dfs(i - 1, j - 1)) + 1;
            }
            return memo[i][j] = res;
        }


        public static int min(int... args) {
            int x = 0x3ffffff;
            for (int val : args) {
                if (val < x) {
                    x = val;
                }
            }
            return x;
        }


    }


}