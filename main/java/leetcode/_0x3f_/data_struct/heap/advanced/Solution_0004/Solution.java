package leetcode._0x3f_.data_struct.heap.advanced.Solution_0004;

import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/swim-in-rising-water
 * @title: 水位上升的泳池中游泳
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "swimInWater", "in.txt");
    }


    public int swimInWater(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        dis[0][0] = grid[0][0];
        q.add(new int[]{0, 0, grid[0][0]});
        boolean[][] vis = new boolean[m][n];
        int[] dirs = new int[]{-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x0 = p[0], y0 = p[1], cost = p[2];
            if (x0 == m - 1 && y0 == n - 1) {
                return dis[x0][y0];
            }
            if (vis[x0][y0]) {
                continue;
            }
            vis[x0][y0] = true;
            for (int k = 0; k < dirs.length - 1; k++) {
                int x1 = dirs[k] + x0, y1 = dirs[k + 1] + y0;
                if (x1 < 0 || x1 >= m || y1 < 0 || y1 >= n) {
                    continue;
                }
                if (vis[x1][y1]) {
                    continue;
                }
                int c = Math.max(cost, grid[x1][y1]);
                if (c < dis[x1][y1]) {
                    dis[x1][y1] = c;
                    q.add(new int[]{x1, y1, c});
                }

            }

        }
        return Integer.MAX_VALUE;
    }


}