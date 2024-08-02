package leetcode._0x3f_.dp.base_line.a_LCS.LCS_DP_0001;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/delete-operation-for-two-strings
 * @title: 两个字符串的删除操作
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minDistance", "in.txt");
    }


    public int minDistance(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        return m + n - 2 * longestCommonSubsequence(text1, text2);
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

}