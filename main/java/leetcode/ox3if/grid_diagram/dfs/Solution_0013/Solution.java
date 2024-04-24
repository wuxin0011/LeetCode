package leetcode.ox3if.grid_diagram.dfs.Solution_0013;

import code_generation.utils.IoUtil;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 827. 最大人工岛
 * <p>
 * 给你一个大小为 n x n 二进制矩阵 grid 。
 * 最多 只能将一格0 变成1 。
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 * 岛屿 由一组上、下、左、右四个方向相连的1 形成。
 * <p>
 * 示例 1:
 * 输入: grid = [[1, 0], [0, 1]]
 * 输出: 3
 * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
 * <p>
 * 示例 2:
 * 输入: grid = [[1, 1], [1, 0]]
 * 输出: 4
 * 解释: 将一格0变成1，岛屿的面积扩大为 4。
 * <p>
 * 示例 3:
 * 输入: grid = [[1, 1], [1, 1]]
 * 输出: 4
 * 解释: 没有0可以让我们变成1，面积依然为 4。
 * <p>
 * 提示：
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] 为 0 或 1
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/making-a-large-island
 * @title: 最大人工岛
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "largestIsland", "in.txt");
    }


    int size = 0;

    public static final int[] dirs = {-1, 0, 1, 0, -1};

    public int largestIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        int idx = 1;
        boolean[] vis = new boolean[m * n + 10];
        int[] sizes = new int[m * n + 10];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    idx++;
                    size = 0;
                    dfs(grid, i, j, idx);
                    sizes[idx] = size;
                } else if (grid[i][j] == 0) {
                    q.add(new int[]{i, j});
                }
            }
        }

        if (q.isEmpty()) {
            return m * n;
        }

        int ans = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int tot = 1; // 当前位置变为1
            for (int k = 0; k <= 3; k++) {
                int x = dirs[k] + p[0], y = dirs[k + 1] + p[1];
                if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
                    continue;
                }
                int v = grid[x][y]; // 获取当前ID
                if (vis[v]) {
                    continue;
                }
                tot += sizes[v];
                vis[v] = true;
            }

            ans = Math.max(ans, tot);

            for (int k = 0; k <= 3; k++) {
                int x = dirs[k] + p[0], y = dirs[k + 1] + p[1];
                if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
                    continue;
                }
                vis[grid[x][y]] = false;
            }

        }
        return ans;
    }

    public void dfs(int[][] grid, int i, int j, int id) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return;
        }
        size++;
        grid[i][j] = id;
        dfs(grid, i - 1, j, id);
        dfs(grid, i + 1, j, id);
        dfs(grid, i, j - 1, id);
        dfs(grid, i, j + 1, id);
    }


}