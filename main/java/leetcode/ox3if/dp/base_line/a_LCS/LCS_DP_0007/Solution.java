package leetcode.ox3if.dp.base_line.a_LCS.LCS_DP_0007;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/shortest-common-supersequence
 * @title: 最短公共超序列
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "shortestCommonSupersequence", "in.txt");
    }


    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    f[i + 1][j + 1] = f[i][j] + 1;
                } else {
                    f[i + 1][j + 1] = Math.max(f[i][j + 1], f[i + 1][j]);
                }
            }
        }
        int tot = m + n - 2 * f[m][n];
        // System.out.println("ans :" + tot + ",s1:" + str1 + ",str2:" + str2);
        int i = m, j = n;
        StringBuilder ans = new StringBuilder();
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                ans.insert(0, str1.charAt(i - 1));
                i--;
                j--;
            } else if (f[i - 1][j] > f[i][j - 1]) {
                ans.insert(0, str1.charAt(i - 1));
                i--;
            } else {
                ans.insert(0, str2.charAt(j - 1));
                j--;
            }
        }

        return str1.substring(0, i) + str2.substring(0, j) + ans.toString();
    }


}