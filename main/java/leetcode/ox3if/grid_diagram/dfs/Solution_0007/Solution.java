package leetcode.ox3if.grid_diagram.dfs.Solution_0007;

import code_generation.utils.IoUtil;

/**
 * 1254. 统计封闭岛屿的数目
 * <p>
 * 二维矩阵 grid由 0（土地）和 1（水）组成。
 * 岛是由最大的4个方向连通的 0组成的群，封闭岛是一个完全 由1包围（左、上、右、下）的岛。
 * 请返回 封闭岛屿 的数目。
 * <p>
 * 示例 1：
 * 输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * 输出：2
 * 解释：
 * 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
 * <p>
 * 示例 2：
 * 输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：grid = [[1,1,1,1,1,1,1],
 * [1,0,0,0,0,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,1,0,1,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,0,0,0,0,1],
 * [1,1,1,1,1,1,1]]
 * 输出：2
 * <p>
 * 提示：
 * 1 <= grid.length, grid[0].length <= 10^0
 * 0 <= grid[i][j] <=1
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-closed-islands
 * @title: 统计封闭岛屿的数目
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "closedIsland", "in.txt");
    }


    public int closedIsland(int[][] grid) {
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 && check(grid, i, j)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static boolean check(int[][] grid, int i, int j) {
        if ( i < 0 || j < 0 || i >=grid.length || j >= grid[0].length) {
            return false;
        }
        if (grid[i][j] == 0) {
            grid[i][j] = 1;
            boolean a = check(grid, i - 1, j);
            boolean b = check(grid, i + 1, j);
            boolean c = check(grid, i, j + 1);
            boolean d = check(grid, i, j - 1);
            return a && b && c && d;
        }
        return true;
    }


}