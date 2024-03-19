package leetcode.contest.weekly.w_300.w_387.b;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @title: 元素和小于等于 k 的子矩阵的数目
 * @Description: 二维前缀和
 * @url https://leetcode.cn/problems/count-submatrices-with-top-left-element-and-sum-less-than-k/
 */
public class B {
    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "countSubmatrices", "in.txt");
    }

    public int countSubmatrices(int[][] grid, int k) {
        if (grid == null || grid.length == 0 || grid[0][0] > k) {
            return 0;
        }
        // System.out.println(Arrays.deepToString(grid));
        int cnt = 0, m = grid.length, n = grid[0].length;

        // 计算每一行和 不参与其他行计算
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = (j == 0 ? 0 : grid[i][j - 1]) + grid[i][j];
            }
        }

        // 从第二行开始 每一个格子val = 上一行的这一列
        // grid[row][col] = grid[row-1][col] + grid[row][col]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] += i == 0 ? 0 : grid[i - 1][j];
                if (grid[i][j] <= k) {
                    cnt++;
                } else {
                    break;
                }
            }
        }
        // System.out.println(Arrays.deepToString(grid));

        return cnt;
    }
}
