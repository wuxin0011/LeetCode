package leetcode.contest.biweekly.bi_100.bi_136.c;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-136/problems/minimum-number-of-flips-to-make-binary-grid-palindromic-ii
 * @title: 最少翻转次数使二进制矩阵回文 II
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minFlips", "C.txt");
    }


    public int minFlips(int[][] g) {
        int m = g.length, n = g[0].length;
        int tot = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    tot++;
                }
            }
        }
        if (tot <= 2) {
            return tot;
        }


        return 0;
    }


    public static int updateRow(int[][] g, int tot, int a) {
        int cnt = 0;
        int m = g.length, n = g[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n / 2; j++) {
                int k = n - 1 - j;
                if (g[i][j] != g[i][k]) {
                    tot++;
                    cnt++;
                }
            }
        }
        if (tot % 4 == 0) {
            return cnt;
        }
        if (n % 2 == 1) {

        }
        return tot;
    }


    public static int updateCol(int[][] g, int tot, int a) {
        int cnt = 0;
        int m = g.length, n = g[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m / 2; j++) {
                int k = m - 1 - j;
                if (g[j][i] != g[k][i]) {
                    tot++;
                    cnt++;
                }
            }
        }
        if (tot % 4 == 0) {
            return cnt;
        }
        if (n % 2 == 1) {

        }
        return tot;
    }


}