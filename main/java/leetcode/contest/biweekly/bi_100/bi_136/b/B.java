package leetcode.contest.biweekly.bi_100.bi_136.b;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-136/problems/minimum-number-of-flips-to-make-binary-grid-palindromic-i
 * @title: 最少翻转次数使二进制矩阵回文 I
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "minFlips", "B.txt");
    }


    public int minFlips(int[][] g) {
        int cnt1 = 0;
        int m = g.length;
        int n = g[0].length;

        // 枚举行
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n / 2; j++) {
                int k = n - 1 - j;
                if (g[i][j] != g[i][k]) {
                    cnt1++;
                }
            }
        }

        // 枚举列
        int cnt2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m / 2; j++) {
                int k = m - 1 - j;
                if (g[j][i] != g[k][i]) {
                    cnt2++;
                }
            }
        }
        return Math.min(cnt1, cnt2);
    }


}