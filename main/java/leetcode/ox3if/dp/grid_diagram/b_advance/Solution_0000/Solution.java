package leetcode.ox3if.dp.grid_diagram.b_advance.Solution_0000;

import code_generation.utils.IoUtil;

/**
 * 1594. 矩阵的最大非负积
 * <p>
 * 给你一个大小为 m x n 的矩阵 grid 。
 * 最初，你位于左上角 (0, 0) ，每一步，你可以在矩阵中 向右 或 向下 移动。
 * 在从左上角 (0, 0) 开始到右下角 (m - 1, n - 1) 结束的所有路径中，找出具有 最大非负积 的路径。路径的积是沿路径访问的单元格中所有整数的乘积。
 * 返回 最大非负积 对 10^9+ 7 取余 的结果。如果最大积为 负数 ，则返回 -1 。
 * 注意，取余是在得到最大积之后执行的。
 * <p>
 * 示例 1：
 * 输入：grid = [[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]
 * 输出：-1
 * 解释：从 (0, 0) 到 (2, 2) 的路径中无法得到非负积，所以返回 -1 。
 * <p>
 * 示例 2：
 * 输入：grid = [[1,-2,1],[1,-2,1],[3,-4,1]]
 * 输出：8
 * 解释：最大非负积对应的路径如图所示 (1 * 1 * -2 * -4 * 1 = 8)
 * <p>
 * 示例 3：
 * 输入：grid = [[1,3],[0,-4]]
 * 输出：0
 * 解释：最大非负积对应的路径如图所示 (1 * 0 * -4 = 0)
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 15
 * -4 <= grid[i][j] <= 4
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-non-negative-product-in-a-matrix
 * @title: 矩阵的最大非负积
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxProductPath", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int maxProductPath(int[][] grid) {

        int m = grid.length, n = grid[0].length;

        // 最大值
        long[][] fx = new long[m][n];
        // 最小值
        long[][] fi = new long[m][n];
        fx[0][0] = fi[0][0] = grid[0][0];

        // 初始化
        for (int i = 1; i < m; i++) {
            fx[i][0] = fx[i - 1][0] * grid[i][0];
            fi[i][0] = fi[i - 1][0] * grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            fx[0][i] = fx[0][i - 1] * grid[0][i];
            fi[0][i] = fi[0][i - 1] * grid[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int v = grid[i][j];
                // 大于0不会变号
                if (v >= 0) {
                    fx[i][j] = (Math.max(fx[i - 1][j], fx[i][j - 1]) * 1L * v);
                    fi[i][j] = (Math.min(fi[i - 1][j], fi[i][j - 1]) * 1L * v);
                } else {
                    fx[i][j] = (Math.min(fi[i - 1][j], fi[i][j - 1]) * 1L * v);
                    fi[i][j] = (Math.max(fx[i - 1][j], fx[i][j - 1]) * 1L * v);
                }
            }
        }
        return fx[m - 1][n - 1] < 0 ? -1 : (int) (fx[m - 1][n - 1] % MOD);
    }


}