package template.graph;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description: Floyd算法： 任意两点之间最短距离 注意不能有负环
 * @see leetcode._0x3f_.graph.floyd.floyd_0000
 * @see leetcode._0x3f_.graph.floyd.floyd_0001
 * @see leetcode._0x3f_.graph.floyd.floyd_0002 floyd + 状压dp
 */
public class Floyd {

    public static int FloydTemplate(int n, int[][] edges, int dis) {
        int inf = 0x7ffffff;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], inf);
        }
        for (int[] e : edges) {
            int f = e[0], t = e[1], wt = e[2];
            g[f][t] = g[t][f] = wt;
        }
        // floyd core
        for (int b = 0; b < n; b++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j || i == b || j == b) continue;
                    g[i][j] = Math.min(g[i][j], g[i][b] + g[b][j]);
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && g[i][j] <= dis) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
