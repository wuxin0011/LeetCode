package leetcode._0x3f_.grid_diagram.dfs.Solution_0004;

import code_generation.utils.IoUtil;
/**
 * 2658. 网格图中鱼的最大数目
 * <p>
 * 给你一个下标从 0开始大小为 m x n的二维整数数组grid，其中下标在(r, c)处的整数表示：
 * 如果grid[r][c] = 0，那么它是一块 陆地。
 * 如果grid[r][c] > 0，那么它是一块水域，且包含grid[r][c]条鱼。
 * 一位渔夫可以从任意 水域格子(r, c)出发，然后执行以下操作任意次：
 * 捕捞格子(r, c)处所有的鱼，或者
 * 移动到相邻的 水域格子。
 * 请你返回渔夫最优策略下，最多可以捕捞多少条鱼。如果没有水域格子，请你返回 0。
 * 格子(r, c)相邻的格子为(r, c + 1)，(r, c - 1)，(r + 1, c) 和(r - 1, c)，前提是相邻格子在网格图内。
 * <p>
 * 示例 1：
 * 输入：grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
 * 输出：7
 * 解释：渔夫可以从格子 (1,3) 出发，捕捞 3 条鱼，然后移动到格子 (2,3)，捕捞 4 条鱼。
 * <p>
 * 示例 2：
 * 输入：grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
 * 输出：1
 * 解释：渔夫可以从格子 (0,0) 或者 (3,3) ，捕捞 1 条鱼。
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10^
 * 0 <= grid[i][j] <= 10^
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-number-of-fish-in-a-grid
 * @title: 网格图中鱼的最大数目
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "findMaxFish", "in.txt");
    }


    public int findMaxFish(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                ans = Math.max(dfs(grid, i, j), ans);
            }
        }
        return ans;
    }

    public int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        int cnt = grid[i][j];
        grid[i][j] = 0;
        cnt += dfs(grid, i - 1, j);
        cnt += dfs(grid, i + 1, j);
        cnt += dfs(grid, i, j - 1);
        cnt += dfs(grid, i, j + 1);
        return cnt;
    }


}