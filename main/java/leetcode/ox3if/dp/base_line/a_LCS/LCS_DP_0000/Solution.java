package leetcode.ox3if.dp.base_line.a_LCS.LCS_DP_0000;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 1143. 最长公共子序列
 * <p>
 * 给定两个字符串text1 和text2，返回这两个字符串的最长 公共子序列 的长度。
 * 如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * <p>
 * 示例 1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * <p>
 * 示例 2：
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * <p>
 * 示例 3：
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 * <p>
 * 提示：
 * 1 <= text1.length, text2.length <= 1000
 * text1 和text2 仅由小写英文字符组成。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/longest-common-subsequence
 * @title: 最长公共子序列
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "longestCommonSubsequence", "in.txt");
        IoUtil.testUtil(Solution.class, "longestCommonSubsequence1", "in.txt");
    }


    public int longestCommonSubsequence(String text1, String text2) {
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        int n = t1.length, m = t2.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (t1[i] == t2[j]) {
                    f[i + 1][j + 1] = f[i][j] + 1;
                } else {
                    f[i + 1][j + 1] = Math.max(f[i + 1][j], f[i][j + 1]);
                }
            }
        }
        return f[n][m];
    }


    public int longestCommonSubsequence1(String text1, String text2) {
        s1 = text1.toCharArray();
        s2 = text2.toCharArray();
        int n = s1.length, m = s2.length;
        memo = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(n - 1, m - 1);
    }

    char[] s1, s2;
    int[][] memo;

    public int dfs(int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = 0;
        if (s1[i] == s2[j]) {
            res = dfs(i - 1, j - 1) + 1;
        } else {
            res = Math.max(dfs(i - 1, j), dfs(i, j - 1));
        }
        memo[i][j] = res;
        return res;
    }


}