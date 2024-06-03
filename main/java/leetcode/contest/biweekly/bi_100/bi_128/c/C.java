package leetcode.contest.biweekly.bi_100.bi_128.c;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 3112. 访问消失节点的最少时间
 * <p>
 * <p>
 * 给你一个二维数组 edges表示一个 n个点的无向图，其中edges[i] = [ui, vi, lengthi]表示节点ui 和节点vi之间有一条需要lengthi单位时间通过的无向边。
 * 同时给你一个数组disappear，其中disappear[i]表示节点 i从图中消失的时间点，在那一刻及以后，你无法再访问这个节点。
 * 注意，图有可能一开始是不连通的，两个节点之间也可能有多条边。
 * 请你返回数组answer，answer[i]表示从节点 0到节点 i需要的 最少单位时间。如果从节点 0出发 无法 到达节点 i，那么 answer[i]为 -1。
 * <p>
 * 示例 1：
 * 输入：n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,1,5]
 * 输出：[0,-1,4]
 * 解释：
 * 我们从节点 0 出发，目的是用最少的时间在其他节点消失之前到达它们。
 * 对于节点 0 ，我们不需要任何时间，因为它就是我们的起点。
 * 对于节点 1 ，我们需要至少 2 单位时间，通过edges[0]到达。但当我们到达的时候，它已经消失了，所以我们无法到达它。
 * 对于节点 2 ，我们需要至少 4 单位时间，通过edges[2]到达。
 * <p>
 * 示例 2：
 * 输入：n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,3,5]
 * 输出：[0,2,3]
 * 解释：
 * 我们从节点 0 出发，目的是用最少的时间在其他节点消失之前到达它们。
 * 对于节点 0 ，我们不需要任何时间，因为它就是我们的起点。
 * 对于节点 1 ，我们需要至少 2 单位时间，通过edges[0]到达。
 * 对于节点 2，我们需要至少 3单位时间，通过edges[0]和 edges[1]到达。
 * <p>
 * 示例 3：
 * 输入：n = 2, edges = [[0,1,1]], disappear = [1,1]
 * 输出：[0,-1]
 * 解释：
 * 当我们到达节点 1 的时候，它恰好消失，所以我们无法到达节点 1 。
 * <p>
 * 提示：
 * 1 <= n <= 5 * 10^4
 * 0 <= edges.length <= 10^5
 * edges[i] == [ui, vi, lengthi]
 * 0 <= ui, vi <= n - 1
 * 1 <= lengthi <= 10^5
 * disappear.length == n
 * 1 <= disappear[i] <= 10^5
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-128/problems/minimum-time-to-visit-disappearing-nodes
 * @title: 访问消失节点的最少时间
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minimumTime", "C.txt");
    }



    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        int[] dis = new int[n];
        ArrayList<int[]>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            dis[i] = -1;
        }

        for (int[] edge : edges) {
            int v = edge[0], t = edge[1], wt = edge[2];
            g[v].add(new int[]{t, wt});
            g[t].add(new int[]{v, wt});
        }

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        q.add(new int[]{0, 0});

        dis[0] = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int v = p[0], d = p[1];

            if (d > dis[v]) continue;

            for (int[] e : g[v]) {
                int to = e[0];
                int cost = e[1] + d;
                if (cost < disappear[to] && (cost < dis[to] || dis[to] == -1)) {
                    dis[to] = cost;
                    q.add(new int[]{to, cost});
                }
            }

        }

        return dis;
    }


}