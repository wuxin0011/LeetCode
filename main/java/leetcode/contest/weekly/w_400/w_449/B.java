package leetcode.contest.weekly.w_400.w_449;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-449/problems/equal-sum-grid-partition-i">等和矩阵分割 I</a>
 * @title: 等和矩阵分割 I
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "canPartitionGrid", "B.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public boolean canPartitionGrid(int[][] g) {
        int m = g.length, n = g[0].length;
        long[] r = new long[m];
        long[] c = new long[n];
        long tot = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                r[i] += g[i][j];
                c[j] += g[i][j];
                tot += g[i][j];
            }
        }
        if (tot % 2 == 1) return false;
        for (int i = 0; i < m; i++) {
            if (i > 0) r[i] += r[i - 1];
            if (tot / 2 == r[i]) return true;
        }
        for (int i = 0; i < n; i++) {
            if (i > 0) c[i] += c[i - 1];
            if (tot / 2 == c[i]) return true;
        }
        return false;
    }


}