package leetcode.ox3if.dp.base_line.a_LCS.LCS_DP_0004;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 *
 * 115. 不同的子序列
 *
 * 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数，结果需要对10^9 + 7 取模。
 *
 * 示例1：
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * rabbbit
 * rabbbit
 * rabbbit
 *
 * 示例2：
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 *
 * 提示：
 * 	1 <= s.length, t.length <= 1000
 * 	s 和 t 由英文字母组成
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/distinct-subsequences
 * @title: 不同的子序列
 */
public class Solution {
    static class s0 {
        public static void main(String[] args) {
            IoUtil.testUtil(s0.class, "numDistinct", "in.txt");
        }


        static int MOD = (int) 1e9 + 7;

        public int numDistinct(String s, String t) {

            int m = s.length(), n = t.length();
            if (m == 0 || m < n) {
                return 0;
            }
            this.s = s;
            this.t = t;
            int[][] f = new int[m][n];
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (s.charAt(i) == t.charAt(j)) {
                        // 不选 + 选择
                        f[i][j] = (dfs(i + 1, j, f) + dfs(i + 1, j + 1, f)) % MOD;
                    } else {
                        // 不选
                        f[i][j] = dfs(i + 1, j, f) % MOD;
                    }
                }
            }

            return f[0][0];
        }

        String s, t;

        public int dfs(int i, int j, int[][] f) {
            if (s.length() - i < t.length() - j) {
                return 0;
            }
            if (i >= s.length() || j >= t.length()) {
                return j == t.length() ? 1 : 0;
            }
            return f[i][j];
        }
    }


    static class s1 {
        public static void main(String[] args) {
            IoUtil.testUtil(s1.class, "numDistinct", "in.txt");
        }


        static int MOD = (int) 1e9 + 7;

        public int numDistinct(String s, String t) {

            int m = s.length(), n = t.length();
            if (m == 0 || m < n) {
                return 0;
            }
            this.s = s;
            this.t = t;
            memo = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(memo[i], -1);
            }
            //System.out.println("source : " + s + "   target : " + t + "    count = " + vj);
            return dfs(0, 0);
        }

        String s, t;
        int[][] memo;

        public int dfs(int i, int j) {
            // 减枝1
            if (s.length() - i < t.length() - j) {
                return 0;
            }
            if (i >= s.length() || j >= t.length()) {
                return j == t.length() ? 1 : 0;
            }
            if (memo[i][j] != -1) {
                return memo[i][j];
            }
            int res = 0;
            if (s.charAt(i) == t.charAt(j)) {
                // 不选 + 选择
                res = (dfs(i + 1, j) + dfs(i + 1, j + 1)) % MOD;
            } else {
                // 不选
                res = dfs(i + 1, j) % MOD;
            }
            return memo[i][j] = res;
        }
    }


}