package leetcode._0x3f_.graph.floyd.floyd_0000;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/design-graph-with-shortest-path-calculator
 * @title: 设计可以求最短路径的图类
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Graph.class, ParseCodeInfo.ConstructorClass, "in.txt");
    }


    public static class Graph {
        int n;
        int[][] edges;
        int[][] g;

        public Graph(int n, int[][] edges) {
            this.n = n;
            this.edges = edges;
            this.init();
        }

        static int inf = Integer.MAX_VALUE / 3;

        void init() {
            g = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(g[i], inf);
                g[i][i] = 0;
            }
            for (int[] edge : edges) {
                int f = edge[0], t = edge[1], wt = edge[2];
                g[f][t] = wt;
            }
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (g[i][k] == inf) continue;
                        if (i == j || i == k || j == k) continue;
                        g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                    }
                }
            }
        }

        public void addEdge(int[] e) {
            int x = e[0], y = e[1], w = e[2];
            if (w >= g[x][y]) { // 无需更新
                return;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    g[i][j] = Math.min(g[i][j], g[i][x] + w + g[y][j]);
                }
            }
        }

        public int shortestPath(int node1, int node2) {
            int ans = g[node1][node2];
            return ans < inf ? ans : -1;
        }


    }

}