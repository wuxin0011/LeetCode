package template.graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author: wuxin0011
 * @Description: dijkstra 是求最短路径(权重)的一种算法 具体配合是优先级队列 适用于网格图和图
 * @see leetcode._0x3f_.graph.dijkstra
 * @see leetcode._0x3f_.data_struct.heap.advanced.Solution_0005
 * @see leetcode._0x3f_.graph.dijkstra.Dijkstra_0000.Solution
 * @see leetcode.contest.biweekly.bi_100.bi_128.c.C
 */
public class Dijkstra {


    // 引用网格图中
    // 不仅适用于四个方向还适用于八个方向

    /**
     * @param grid
     * @param st
     * @param ed
     * @return
     * @see leetcode._0x3f_.data_struct.heap.advanced.Solution_0004.Solution
     * @see leetcode._0x3f_.data_struct.heap.advanced.Solution_0005.Solution

     */
    public static int dijkstraGrid(int[][] grid, int[] st, int[] ed) {
        int m = grid.length, n = grid[0].length;
        int[][] dis = new int[m][n];
        int inf = 0x7ffffff;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dis[i][j] = inf;
            }
        }

        boolean[][] vis = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // 根据权重排序

        int x0 = st[0], y0 = st[1]; // 起始点
        dis[x0][y0] = 0; // 自己到自己距离为0
        pq.add(new int[]{x0, y0, 0});


        int[] dirs = {-1, 0, 1, 0, 1};

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            x0 = p[0];
            y0 = p[1];
            int wt0 = p[2];
            if (x0 == ed[0] && y0 == ed[1]) {
                return dis[ed[0]][ed[1]]; // 找到目标点
            }
            if (vis[x0][y0]) {
                continue;
            }
            vis[x0][y0] = true;

            // 分层搜索
            for (int k = 0; k < 4; k++) {
                int x1 = dirs[k] + x0, y1 = dirs[k + 1] + x1;
                if (x1 < 0 || x1 >= m || y1 < 0 || y1 >= n || vis[x1][y1]) {
                    continue;
                }
                // 这个步骤为核心 求 两个点之间花费
                int cost = dis[x0][y0] + grid[x1][y1];

                if (cost < dis[x1][y1]) {
                    dis[x1][y1] = cost;
                    pq.add(new int[]{x1, y1, cost});
                }
            }

        }
        return inf;
    }


    /**
     * 单源最短路径
     *
     * @param edges 图
     * @param n     节点个数
     * @param s     源点
     * @return min cost
     * @see leetcode._0x3f_.graph.dijkstra.Dijkstra_0000.Solution
     */
    public static int dijkstraGrid2(int[][] edges, int n, int s) {

        int[] dis = new int[n + 1];
        int inf = 0x7ffffff;
        ArrayList<int[]>[] g = new ArrayList[n + 1];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            dis[i] = inf;
        }

        boolean[] vis = new boolean[n + 1];

        for (int[] edge : edges) {
            int from = edge[0], v = edge[1], wt = edge[2];
            g[from].add(new int[]{v, wt});
        }

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        dis[s] = 0;
        q.add(new int[]{s, 0});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int u = p[0];
            if (vis[u]) {
                continue;
            }
            vis[u] = true;
            for (int[] e : g[p[0]]) {
                int v = e[0], wt = e[1];
                if (!vis[v] && dis[u] + wt < dis[v]) {
                    dis[v] = dis[u] + wt;
                    q.add(new int[]{v, dis[v]});
                }
            }
        }


        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (dis[i] == inf) {
                return -1;
            }
            if (ans < dis[i]) {
                ans = dis[i];
            }
        }
        return ans;
    }


    // 求次大值的dj
    // https://leetcode.cn/problems/second-minimum-time-to-reach-destination/submissions/571668490/


}
