package leetcode.ox3if.grid_diagram.dfs.Solution_0005;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1034. 边界着色给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。
 * 另给你三个整数row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
 * 如果两个方块在任意 4 个方向上相邻，则称它们相邻 。
 * 如果两个方块具有相同的颜色且相邻，它们则属于同一个 连通分量 。
 * 连通分量的边界 是指连通分量中满足下述条件之一的所有网格块：
 * 在上、下、左、右任意一个方向上与不属于同一连通分量的网格块相邻
 * 在网格的边界上（第一行/列或最后一行/列）
 * 请你使用指定颜色color 为所有包含网格块grid[row][col] 的 连通分量的边界 进行着色。
 * 并返回最终的网格grid 。
 * <p>
 * 示例 1：
 * 输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
 * 输出：[[3,3],[3,2]]
 * <p>
 * 示例 2：
 * 输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
 * 输出：[[1,3,3],[2,3,3]]
 * <p>
 * 示例 3：
 * 输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
 * 输出：[[2,2,2],[2,1,2],[2,2,2]]
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j], color <= 1000
 * 0 <= row < m
 * 0 <= col < n
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/coloring-a-border
 * @title: 边界着色
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "colorBorder", "in.txt");
    }

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        List<int[]> borders = new ArrayList<>();
        int originalColor = grid[row][col];
        visited[row][col] = true;
        dfs(grid, row, col, visited, borders, originalColor);
        for (int i = 0; i < borders.size(); i++) {
            int x = borders.get(i)[0], y = borders.get(i)[1];
            grid[x][y] = color;
        }
        return grid;
    }

    private void dfs(int[][] grid, int x, int y, boolean[][] visited, List<int[]> borders, int originalColor) {
        int m = grid.length, n = grid[0].length;
        boolean isBorder = false;
        int[][] direc = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 0; i < 4; i++) {
            int nx = direc[i][0] + x, ny = direc[i][1] + y;
            if (!(nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == originalColor)) {
                isBorder = true;
            } else if (!visited[nx][ny]){
                visited[nx][ny] = true;
                dfs(grid, nx, ny, visited, borders, originalColor);
            }
        }
        if (isBorder) {
            borders.add(new int[]{x, y});
        }
    }


}