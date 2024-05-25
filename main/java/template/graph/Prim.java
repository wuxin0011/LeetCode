package template.graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Prim {

    // 图
    // 求最小生成树
    public static int prim(int[][] edges, int n) {
        ArrayList<int[]>[] g = new ArrayList[n + 1];
        boolean[] vis = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        // 构建无向图
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], wt = edge[2];
            g[from].add(new int[]{to, wt});
            g[to].add(new int[]{from, wt});
        }

        // 根据权重排序
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        pq.addAll(g[0]);
        int ans = 0;

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int from = p[0];
            if (vis[from]) {
                continue;
            }
            vis[from] = true;
            // 图的深度遍历
            for (int[] e : g[from]) {
                int to = e[0], wt = e[1];
                if (!vis[to]) {
                    ans += wt;
                    pq.add(new int[]{to, wt});
                }
            }

        }

        return ans;
    }
}
