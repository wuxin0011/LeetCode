package leetcode.ox3if.graph.floyd.floyd_0001;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 1334. 阈值距离内邻居最少的城市
 * <p>
 * 有 n个城市，按从 0 到 n-1编号。
 * 给你一个边数组edges，其中 edges[i] = [fromi, toi, weighti]代表fromi和toi两个城市之间的双向加权边，距离阈值是一个整数distanceThreshold。
 * 返回在路径距离限制为 distanceThreshold 以内可到达城市最少的城市。如果有多个这样的城市，则返回编号最大的城市。
 * 注意，连接城市 i 和 j 的路径的距离等于沿该路径的所有边的权重之和。
 * <p>
 * 示例 1：
 * 输入：n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
 * 输出：3
 * 解释：城市分布图如上。
 * 每个城市阈值距离 distanceThreshold = 4 内的邻居城市分别是：
 * 城市 0 -> [城市 1, 城市 2]
 * 城市 1 -> [城市 0, 城市 2, 城市 3]
 * 城市 2 -> [城市 0, 城市 1, 城市 3]
 * 城市 3 -> [城市 1, 城市 2]
 * 城市 0 和 3 在阈值距离 4 以内都有 2 个邻居城市，但是我们必须返回城市 3，因为它的编号最大。
 * <p>
 * 示例 2：
 * 输入：n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
 * 输出：0
 * 解释：城市分布图如上。
 * 每个城市阈值距离 distanceThreshold = 2 内的邻居城市分别是：
 * 城市 0 -> [城市 1]
 * 城市 1 -> [城市 0, 城市 4]
 * 城市 2 -> [城市 3, 城市 4]
 * 城市 3 -> [城市 2, 城市 4]
 * 城市 4 -> [城市 1, 城市 2, 城市 3]
 * 城市 0 在阈值距离 2 以内只有 1 个邻居城市。
 * <p>
 * 提示：
 * 2 <= n <= 100
 * 1 <= edges.length <= n * (n - 1) / 2
 * edges[i].length == 3
 * 0 <= fromi < toi < n
 * 1 <= weighti,distanceThreshold <= 10^4
 * 所有 (fromi, toi)都是不同的。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance
 * @title: 阈值距离内邻居最少的城市
 */
public class Solution {


    // 最优版本
    // 01背包 从前往递推 滚动更新
    static class S0 {

        public static void main(String[] args) {
            IoUtil.testUtil(S0.class, "findTheCity", "in.txt");
        }


        public int findTheCity(int n, int[][] edges, int distanceThreshold) {
            int inf = 0x3ffff;
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
            int No = 0;
            int mx = inf;
            for (int i = 0; i < n; i++) {
                int cnt = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j && g[i][j] <= distanceThreshold) {
                        cnt++;
                    }
                }
                if (cnt <= mx) {
                    mx = cnt;
                    No = i;
                }
            }
            return No;
        }
    }


    // 递推版本
    static class S1 {

        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "findTheCity", "in.txt");
        }


        public int findTheCity(int n, int[][] edges, int distanceThreshold) {
            int inf = 0x3ffff;
            int[][] g = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(g[i], inf);
            }
            for (int[] e : edges) {
                int f = e[0], t = e[1], wt = e[2];
                g[f][t] = g[t][f] = wt;
            }
            int[][][] f = new int[n + 1][n][n];
            f[0] = g;
            for (int p = 0; p < n; p++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        // 不选 + 选
                        f[p + 1][i][j] = Math.min(f[p][i][j], f[p][i][p] + f[p][p][j]);
                    }
                }
            }

            int No = 0;
            int mx = inf;
            for (int i = 0; i < n; i++) {
                int cnt = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j && f[n][i][j] <= distanceThreshold) {
                        cnt++;
                    }
                }
                if (cnt <= mx) {
                    mx = cnt;
                    No = i;
                }
            }
            return No;
        }
    }


    static class S2 {

        public static void main(String[] args) {
            IoUtil.testUtil(S2.class, "findTheCity", "in.txt");
        }

        int[][] g;
        int[][][] memo;

        /**
         * 记忆化搜索
         *
         * @param p 当前桥接点
         * @param i 断点1
         * @param j 断点2
         * @return 最小距离
         */

        public int dfs(int p, int i, int j) {
            if (p < 0) {
                return g[i][j];
            }
            if (memo[p][i][j] != -1) {
                return memo[p][i][j];
            }
            int res = Math.min(dfs(p - 1, i, j), dfs(p - 1, i, p) + dfs(p - 1, p, j));
            memo[p][i][j] = res;
            return res;
        }


        public int findTheCity(int n, int[][] edges, int distanceThreshold) {
            int inf = 0x3ffff;
            g = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(g[i], inf);
            }
            for (int[] e : edges) {
                int f = e[0], t = e[1], wt = e[2];
                g[f][t] = g[t][f] = wt;
            }
            memo = new int[n][n][n];
            for (int[][] ints : memo) {
                for (int[] anInt : ints) {
                    Arrays.fill(anInt, -1);
                }
            }

            int No = 0;
            int mx = inf;
            for (int i = 0; i < n; i++) {
                int cnt = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j && dfs(n - 1, i, j) <= distanceThreshold) {
                        cnt++;
                    }
                }
                if (cnt <= mx) {
                    mx = cnt;
                    No = i;
                }
            }
            return No;
        }
    }


}