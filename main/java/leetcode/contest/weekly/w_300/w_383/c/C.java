package leetcode.contest.weekly.w_300.w_383.c;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 区域的平均强度
 */
public class C {
    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"resultGrid");
    }

    public int[][] resultGrid(int[][] a, int threshold) {
        int m = a.length;
        int n = a[0].length;
        int[][] result = new int[m][n];
        int[][] cnt = new int[m][n];
        for (int i = 2; i < m; i++) {
            next:
            for (int j = 2; j < n; j++) {
                // 检查左右相邻格子
                for (int x = i - 2; x <= i; x++) {
                    if (Math.abs(a[x][j - 2] - a[x][j - 1]) > threshold || Math.abs(a[x][j - 1] - a[x][j]) > threshold) {
                        continue next; // 不合法，下一个
                    }
                }

                // 检查上下相邻格子
                for (int y = j - 2; y <= j; ++y) {
                    if (Math.abs(a[i - 2][y] - a[i - 1][y]) > threshold || Math.abs(a[i - 1][y] - a[i][y]) > threshold) {
                        continue next; // 不合法，下一个
                    }
                }

                // 合法，计算 3x3 子网格的平均值
                int avg = 0;
                for (int x = i - 2; x <= i; x++) {
                    for (int y = j - 2; y <= j; y++) {
                        avg += a[x][y];
                    }
                }
                avg /= 9;

                // 更新 3x3 子网格内的 result
                for (int x = i - 2; x <= i; x++) {
                    for (int y = j - 2; y <= j; y++) {
                        result[x][y] += avg; // 先累加，最后再求平均值
                        cnt[x][y]++;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cnt[i][j] == 0) { // (i,j) 不属于任何子网格
                    result[i][j] = a[i][j];
                } else {
                    result[i][j] /= cnt[i][j]; // 求平均值
                }
            }
        }
        return result;
    }

}
