package leetcode.ox3if.grid_diagram.dfs.Solution_0001;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/max-area-of-island
 * @title: 岛屿的最大面积
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxAreaOfIsland", "in.txt");
    }


    int cnt = 0;
    int ans = 0;

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    cnt = 0;
                    dfs(grid, i, j);
                    ans = Math.max(cnt, ans);
                }
            }
        }
        return ans;
    }

    public void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return;
        }
        cnt++;
        grid[i][j] = 0;
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }


}