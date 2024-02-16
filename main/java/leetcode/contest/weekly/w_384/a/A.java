package leetcode.contest.weekly.w_384.a;

/**
 * @author: wuxin0011
 * @Description:
 */
public class A {
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
        for (int i = 0; i < matrix.length; i++) {
            mx = Math.max(mx, matrix[i][col]);
        }
        return mx;
    }
}
