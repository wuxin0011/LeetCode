package leetcode._0x3f_.dp.grid_diagram.b_advance.Solution_0006;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-increasing-paths-in-a-grid
 * @title: 网格图中递增路径的数目
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "countPaths", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int countPaths(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        memo = new long[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans += dfs(i, j);
                ans %= MOD;
            }
        }
        return ans;
    }

    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int m, n;
    int[][] grid;
    long[][] memo;

    boolean valid(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }


    long dfs(int i, int j) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        long ans = 1;

        for (int[] dir : dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            if (!valid(x, y) || grid[x][y] <= grid[i][j]) {
                continue;
            }
            ans += dfs(x, y);
            ans %= MOD;
        }

        return memo[i][j] = ans;
    }


}