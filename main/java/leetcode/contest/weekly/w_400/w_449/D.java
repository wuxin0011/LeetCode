package leetcode.contest.weekly.w_400.w_449;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-449/problems/equal-sum-grid-partition-ii">等和矩阵分割 II</a>
 * @title: 等和矩阵分割 II
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"canPartitionGrid","D.txt");
    }
    private static final int MOD = (int)1e9 + 7;

    public boolean canPartitionGrid(int[][] g) {
        long tot = 0;
        for (int[] row : g) {
            for (int num : row) {
                tot += num;
            }
        }
        int m = g.length;
        int n = g[0].length;
        long[] row = new long[m];
        long[] col = new long[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                row[i] += g[i][j];
                col[j] += g[i][j];
            }
        }

        int a = g[0][0];
        int b = g[0][n - 1];
        int c = g[m - 1][0];
        int d = g[m - 1][n - 1];

        for (int i = 0; i < m; i++) {
            if (i > 0) {
                row[i] += row[i - 1];
            }
            if (tot % 2 == 0 && row[i] == tot / 2) {
                return true;
            }
            if (rowDelUp(tot, row[i], a) || rowDelUp(tot, row[i], b)) {
                return true;
            }
            if (rowDelDown(tot, row[i], c) || rowDelDown(tot, row[i], d)) {
                return true;
            }
            if (n == 1 && i > 0) {
                int val = g[i][0];
                if ((tot - val) % 2 == 0 && (tot - val) / 2 == row[i] - val) {
                    return true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                col[i] += col[i - 1];
            }
            if (tot % 2 == 0 && col[i] == tot / 2) {
                return true;
            }
            if (colDelLeft(tot, col[i], a) || colDelLeft(tot, col[i], c)) {
                return true;
            }
            if (colDelRight(tot, col[i], b) || colDelRight(tot, col[i], d)) {
                return true;
            }
            if (m == 1 && i > 0) {
                int val = g[0][i];
                if ((tot - val) % 2 == 0 && (tot - val) / 2 == col[i] - val) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean rowDelUp(long tot, long rowSum, int x) {
        return (tot - x) % 2 == 0 && (tot - x) / 2 == rowSum - x;
    }

    private boolean rowDelDown(long tot, long rowSum, int x) {
        return (tot - x) % 2 == 0 && (tot - x) / 2 == rowSum;
    }

    private boolean colDelLeft(long tot, long colSum, int x) {
        return (tot - x) % 2 == 0 && (tot - x) / 2 == colSum - x;
    }

    private boolean colDelRight(long tot, long colSum, int x) {
        return (tot - x) % 2 == 0 && (tot - x) / 2 == colSum;
    }

}