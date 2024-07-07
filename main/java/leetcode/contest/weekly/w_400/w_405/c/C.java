package leetcode.contest.weekly.w_400.w_405.c;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-405/problems/count-submatrices-with-equal-frequency-of-x-and-y
 * @title: 统计 X 和 Y 频数相等的子矩阵数量
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "numberOfSubmatrices", "C.txt");
    }


    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        ArraySum a = new ArraySum(grid);
        CalcXArraySum x = new CalcXArraySum(grid);
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int s = a.queryDouble(0, 0, i, j);
                int xcnt = x.queryDouble(0, 0, i, j);
                if (s == 0 && xcnt > 0) cnt++;

            }
        }
        return cnt;
    }

    static class ArraySum {

        int[][] sum;

        ArraySum(char[][] grid) {
            this.sum = init(grid);
        }

        /**
         * 二维前缀和
         *
         * @param grid
         * @return
         */
        public int[][] init(char[][] grid) {
            int row = grid.length, col = grid[0].length;
            int[][] sum = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int left = j > 0 ? sum[i][j - 1] : 0;
                    int top = i > 0 ? sum[i - 1][j] : 0;
                    int left_top = i > 0 && j > 0 ? sum[i - 1][j - 1] : 0;
                    int cur = grid[i][j] == '.' ? 0 : grid[i][j] == 'X' ? 1 : -1;
                    sum[i][j] = left + top - left_top + cur;
                }
            }
            return sum;
        }

        /**
         * 给定左上坐标和右边下坐标 求 区域和
         * 坐上坐标 a,b
         * 右下坐标 c,d
         */
        public int queryDouble(int a, int b, int c, int d) {
            if (sum == null) {
                return 0;
            }
            int top = a > 0 ? sum[a - 1][d] : 0;
            int tot = sum[c][d];
            int left = b > 0 ? sum[c][b - 1] : 0;
            int left_top = a > 0 && b > 0 ? sum[a - 1][b - 1] : 0;
            return tot + left_top - top - left;
        }
    }
    static class CalcXArraySum {

        int[][] sum;

        CalcXArraySum(char[][] grid) {
            this.sum = init(grid);
        }

        /**
         * 二维前缀和
         *
         * @param grid
         * @return
         */
        public int[][] init(char[][] grid) {
            int row = grid.length, col = grid[0].length;
            int[][] sum = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int left = j > 0 ? sum[i][j - 1] : 0;
                    int top = i > 0 ? sum[i - 1][j] : 0;
                    int left_top = i > 0 && j > 0 ? sum[i - 1][j - 1] : 0;
                    int cur = grid[i][j] == 'X' ? 1 : 0;
                    sum[i][j] = left + top - left_top + cur;
                }
            }
            return sum;
        }

        /**
         * 给定左上坐标和右边下坐标 求 区域和
         * 坐上坐标 a,b
         * 右下坐标 c,d
         */
        public int queryDouble(int a, int b, int c, int d) {
            if (sum == null) {
                return 0;
            }
            int top = a > 0 ? sum[a - 1][d] : 0;
            int tot = sum[c][d];
            int left = b > 0 ? sum[c][b - 1] : 0;
            int left_top = a > 0 && b > 0 ? sum[a - 1][b - 1] : 0;
            return tot + left_top - top - left;
        }
    }

}