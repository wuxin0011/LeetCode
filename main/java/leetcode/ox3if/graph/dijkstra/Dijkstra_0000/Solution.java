package leetcode.ox3if.graph.dijkstra.Dijkstra_0000;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


/**
 * 743. 网络延迟时间
 * <p>
 * 有 n 个网络节点，标记为1到 n。
 * 给你一个列表times，表示信号经过 有向 边的传递时间。times[i] = (ui, vi, wi)，其中ui是源节点，vi是目标节点， wi是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点K发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回-1 。
 * <p>
 * 示例 1：
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 * <p>
 * 提示：
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/network-delay-time
 * @title: 网络延迟时间
 */
public class Solution {
    static class Solution1 {

        public static void main(String[] args) {
            IoUtil.testUtil(Solution1.class, "networkDelayTime", "in.txt");
        }


        // 分析
        // 从源点到到所有距离最小
        // dis[n] 为初始到所有距离 为 无穷大
        // vis[n] 记录是否访问
        // 建图 注意有相向图
        // 使用优先级列队列排序
        // 最后检查 dis[i] == inf ?
        public int networkDelayTime(int[][] edges, int n, int k) {

            int[] dis = new int[n + 1]; // 题目要求是 1-n
            int inf = 0x7ffffff;
            ArrayList<int[]>[] g = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                g[i] = new ArrayList<>();
                dis[i] = inf;
            }

            // boolean[] vis = new boolean[n + 1];

            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], wt = edge[2];
                g[u].add(new int[]{v, wt});
                // g[v].add(new int[]{u, wt});
            }

            PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            dis[k] = 0;
            q.add(new int[]{k, 0});
            while (!q.isEmpty()) {
                int[] p = q.poll();
                int u = p[0];
                if (dis[u] < p[1]) {
                    continue;
                }
                //vis[u] = true;
                for (int[] e : g[p[0]]) {
                    int v = e[0], wt = e[1];
                    // if (!vis[v] && dis[u] + wt < dis[v]) {
                    if (dis[u] + wt < dis[v]) {
                        dis[v] = dis[u] + wt;
                        q.add(new int[]{v, dis[v]});
                    }
                }
            }


            int ans = 0;

            for (int i = 1; i <= n; i++) {
                if (dis[i] == inf) {
                    return -1;
                }
                if (ans < dis[i]) {
                    ans = dis[i];
                }
            }
            return ans;
        }

    }

    public static class Solution2 {

        public static void main(String[] args) {
            IoUtil.testUtil(Solution2.class, "networkDelayTime", "in.txt");
        }

        // 链式前向星+反向索引堆的实现
        public static int networkDelayTime(int[][] times, int n, int s) {
            build(n);
            for (int[] edge : times) {
                addEdge(edge[0], edge[1], edge[2]);
            }
            addOrUpdateOrIgnore(s, 0);
            while (!isEmpty()) {
                int u = pop();
                for (int ei = head[u]; ei > 0; ei = next[ei]) {
                    addOrUpdateOrIgnore(to[ei], distance[u] + weight[ei]);
                }
            }
            int ans = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                if (distance[i] == Integer.MAX_VALUE) {
                    return -1;
                }
                ans = Math.max(ans, distance[i]);
            }
            return ans;
        }

        public static int MAXN = 101;

        public static int MAXM = 6001;

        // 链式前向星
        public static int[] head = new int[MAXN];

        public static int[] next = new int[MAXM];

        public static int[] to = new int[MAXM];

        public static int[] weight = new int[MAXM];

        public static int cnt;

        // 反向索引堆
        public static int[] heap = new int[MAXN];

        // where[v] = -1，表示v这个节点，从来没有进入过堆
        // where[v] = -2，表示v这个节点，已经弹出过了
        // where[v] = i(>=0)，表示v这个节点，在堆上的i位置
        public static int[] where = new int[MAXN];

        public static int heapSize;

        public static int[] distance = new int[MAXN];

        public static void build(int n) {
            cnt = 1;
            heapSize = 0;
            Arrays.fill(head, 1, n + 1, 0);
            Arrays.fill(where, 1, n + 1, -1);
            Arrays.fill(distance, 1, n + 1, Integer.MAX_VALUE);
        }

        // 链式前向星建图
        public static void addEdge(int u, int v, int w) {
            next[cnt] = head[u];
            to[cnt] = v;
            weight[cnt] = w;
            head[u] = cnt;
            cnt++;
        }

        public static void addOrUpdateOrIgnore(int v, int c) {
            if (where[v] == -1) {
                heap[heapSize] = v;
                where[v] = heapSize++;
                distance[v] = c;
                heapInsert(where[v]);
            } else if (where[v] >= 0) {
                distance[v] = Math.min(distance[v], c);
                heapInsert(where[v]);
            }
        }

        public static void heapInsert(int i) {
            while (distance[heap[i]] < distance[heap[(i - 1) / 2]]) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        public static int pop() {
            int ans = heap[0];
            swap(0, --heapSize);
            heapify(0);
            where[ans] = -2;
            return ans;
        }

        public static void heapify(int i) {
            int l = 1;
            while (l < heapSize) {
                int best = l + 1 < heapSize && distance[heap[l + 1]] < distance[heap[l]] ? l + 1 : l;
                best = distance[heap[best]] < distance[heap[i]] ? best : i;
                if (best == i) {
                    break;
                }
                swap(best, i);
                i = best;
                l = i * 2 + 1;
            }
        }

        public static boolean isEmpty() {
            return heapSize == 0;
        }

        public static void swap(int i, int j) {
            int tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
            where[heap[i]] = i;
            where[heap[j]] = j;
        }
    }


}