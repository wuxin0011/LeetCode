package leetcode._0x3f_.dp.interval.a_LPS.LPS_0000;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 516. 最长回文子序列
 *
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 * 示例 1：
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 *
 * 提示：
 * 	1 <= s.length <= 1000
 * 	s 仅由小写英文字母组成
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/longest-palindromic-subsequence
 * @title: 最长回文子序列
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "longestPalindromeSubseq", "in.txt");
    }


    public int longestPalindromeSubseq(String s) {
        this.s = s.toCharArray();
        int n = s.length();
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(0, n - 1);
    }

    int[][] memo;

    char[] s;

    public int dfs(int l, int r) {
        if (l > r) {
            return 0;
        }

        if (memo[l][r] != -1) {
            return memo[l][r];
        }

        int res = 0;
        if (l == r) {
            res = 1;
        } else if (s[l] == s[r]) {
            res = 2 + dfs(l + 1, r - 1);
        } else {
            // dfs(l + 1, r-1) 包含在 Math.max(dfs(l + 1, r), dfs(l, r - 1)) 中
            res = Math.max(dfs(l + 1, r), dfs(l, r - 1));
        }
        memo[l][r] = res;
        return res;
    }


}