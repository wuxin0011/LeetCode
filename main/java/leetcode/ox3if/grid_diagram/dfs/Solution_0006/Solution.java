package leetcode.ox3if.grid_diagram.dfs.Solution_0006;

import code_generation.utils.IoUtil;
/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-enclaves
 * @title: 飞地的数量
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "numEnclaves", "in.txt");
    }


    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // col
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) {
                dfs(grid, i, 0);
            }
            if (grid[i][n - 1] == 1) {
                dfs(grid, i, n - 1);
            }
        }

        // row
        for (int i = 0; i < n; i++) {
            if (grid[0][i] == 1) {
                dfs(grid, 0, i);
            }
            if (grid[m - 1][i] == 1) {
                dfs(grid, m - 1, i);
            }
        }

        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 2;
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }


}