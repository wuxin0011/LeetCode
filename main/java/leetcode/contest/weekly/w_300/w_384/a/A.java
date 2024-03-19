package leetcode.contest.weekly.w_300.w_384.a;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"modifiedMatrix");
    }
    public int[][] modifiedMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1) {
                    // find maxt
                    ans[i][j] = max(matrix, i, j);
                } else {
                    ans[i][j] = matrix[i][j];
                }

            }
        }
        return ans;
    }

    public static int max(int[][] matrix, int row, int col) {
        int mx = -1;
        for (int[] ints : matrix) {
            mx = Math.max(mx, ints[col]);
        }
        return mx;
    }
}
