package leetcode._0x3f_.dp.base_line.a_LCS.LCS_DP_0002;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-ascii-delete-sum-for-two-strings
 * @title: minimum-ascii-delete-sum-for-two-strings
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minimumDeleteSum", "in.txt");
    }


    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int cost_s1 = 0;
        int cost_s2 = 0;
        for (int i = 0; i < m; i++) {
            cost_s1 += (int) s1.charAt(i);
        }
        for (int i = 0; i < n; i++) {
            cost_s2 += (int) s2.charAt(i);
        }
        return cost_s1 + cost_s2 - 2 * this.longestCommonSubsequence(s1, s2);
    }


    // 转化为最长公共子串
    @Deprecated
    public int longestCommonSubsequence(String text1, String text2) {
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        int n = t1.length, m = t2.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (t1[i] == t2[j]) {
                    f[i + 1][j + 1] = f[i][j] + (int) t1[i];
                } else {
                    f[i + 1][j + 1] = Math.max(f[i + 1][j], f[i][j + 1]);
                }
            }
        }
        return f[n][m];
    }


}