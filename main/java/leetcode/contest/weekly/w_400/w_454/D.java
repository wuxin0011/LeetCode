package leetcode.contest.weekly.w_400.w_454;

import code_generation.utils.IoUtil;

/**
 *
 * 3585. 树中找到带权中位节点
 *
 * 给你一个整数 n，以及一棵无向带权树，根节点为节点 0，树中共有 n 个节点，编号从 0 到 n - 1。
 * 该树由一个长度为 n - 1的二维数组 edges 表示，其中 edges[i] = [ui, vi, wi] 表示存在一条从节点 ui 到 vi 的边，权重为 wi。
 * 带权中位节点定义为从 ui 到 vi 路径上的第一个节点 x，使得从 ui 到 x 的边权之和大于等于该路径总权值和的一半。
 * 给你一个二维整数数组 queries。对于每个 queries[j] = [uj, vj]，求出从 uj 到 vj 路径上的带权中位节点。
 * 返回一个数组 ans，其中 ans[j] 表示查询 queries[j] 的带权中位节点编号。
 *
 * 示例 1：
 * 输入： n = 2, edges = [[0,1,7]], queries = [[1,0],[0,1]]
 * 输出： [0,1]
 * 解释：
 * 查询路径边权总路径权值和一半解释答案[1, 0]1 → 0[7]73.5从 1 → 0 的权重和为 7 >= 3.5，中位节点是 0。0[0, 1]0 → 1[7]73.5从 0 → 1 的权重和为 7 >= 3.5，中位节点是 1。1
 *
 * 示例 2：
 * 输入： n = 3, edges = [[0,1,2],[2,0,4]], queries = [[0,1],[2,0],[1,2]]
 * 输出： [1,0,2]
 * 解释：
 * 查询路径边权总路径权值和一半解释答案[0, 1]0 → 1[2]21从 0 → 1 的权值和为 2 >= 1，中位节点是 1。1[2, 0]2 → 0[4]42从 2 → 0 的权值和为 4 >= 2，中位节点是 0。0[1, 2]1 → 0 → 2[2, 4]63从 1 → 0 = 2 < 3，
 * 			从 1 → 2 = 6 >= 3，中位节点是 2。2
 *
 * 示例 3：
 * 输入： n = 5, edges = [[0,1,2],[0,2,5],[1,3,1],[2,4,3]], queries = [[3,4],[1,2]]
 * 输出： [2,2]
 * 解释：
 * 查询路径边权总路径权值和一半解释答案[3, 4]3 → 1 → 0 → 2 → 4[1, 2, 5, 3]115.5从 3 → 1 = 1 < 5.5，
 * 			从 3 → 0 = 3 < 5.5，
 * 			从 3 → 2 = 8 >= 5.5，中位节点是 2。2[1, 2]1 → 0 → 2[2, 5]73.5从 1 → 0 = 2 < 3.5，
 * 			从 1 → 2 = 7 >= 3.5，中位节点是 2。2
 *
 * 提示:
 * 	2 <= n <= 10^5
 * 	edges.length == n - 1
 * 	edges[i] == [ui, vi, wi]
 * 	0 <= ui, vi < n
 * 	1 <= wi <= 10^9
 * 	1 <= queries.length <= 10^5
 * 	queries[j] == [uj, vj]
 * 	0 <= uj, vj < n
 * 	输入保证 edges 表示一棵合法的树。
 *
 *
 * https://leetcode.cn/problems/find-weighted-median-node-in-tree/description/
 *
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-454/problems/find-weighted-median-node-in-tree">树中找到带权中位节点</a>
 * @title: 树中找到带权中位节点
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"findMedian","D.txt");
    }
    private static final int MOD = (int)1e9 + 7;

    // lca 模板开始======================================================
    static class LCA {
        static int ADD_FLAG = 20, NOT_EXIST_FLAG = -1;
        int h[], st[][], power, n, m, nxt[], to[], head[], wt[], cnt, point[];
        long weightDis[];

        // n 表示点数
        // m 表示边数
        public LCA(int n, int m) {
            this.n = n;
            this.m = m;
            h = new int[n + ADD_FLAG];
            nxt = new int[m + ADD_FLAG];
            to = new int[m + ADD_FLAG];
            wt = new int[m + ADD_FLAG];
            weightDis = new long[n + ADD_FLAG];
            head = new int[n + ADD_FLAG];
            power = (int) (Math.floor(Math.log(n) / Math.log(2))) + 1;
            st = new int[n][power + 1];
            clear(n);
        }

        public void clear(int n) {
            cnt = 0;
            power = (int) (Math.floor(Math.log(n) / Math.log(2))) + 1;
            for (int i = 0; i <= n + 5; i++) {
                head[i] = NOT_EXIST_FLAG;
                h[i] = 0;
                weightDis[i] = 0;
            }
        }

        public void addEdge(int u, int v,int w) {
            cnt++;
            nxt[cnt] = head[u];
            to[cnt] = v;
            wt[cnt] = w;
            head[u] = cnt;
        }

        // dfs(0,0,1)
        public void dfs(int u, int fa) {
            dfs(u, fa, 1);
        }

        public void dfs(int u, int fa, int d) {
            h[u] = d;
            st[u][0] = fa;
            for (int p = 1; p <= power && st[u][p - 1] >= 0; p++) {
                st[u][p] = st[st[u][p - 1]][p - 1];
            }
            for (int e = head[u]; e > 0; e = nxt[e]) {
                int v = to[e], w = wt[e];
                if (v != u && v != fa) {
                    // 如果是带权边
                    weightDis[v] = weightDis[u] + w;
                    dfs(v, u, d + 1);
                }
            }
        }

        // 第i个节点往上k步的节点
        public int kth(int i, int k) {
            if (h[i] <= k) return -1;
            int high = h[i] - k;
            for (int p = power; p >= 0; p--) {
                if (h[st[i][p]] >= high) i = st[i][p];
            }
            return i;
        }

        public int lca(int a, int b) {
            if (a == b) return a;
            if (h[a] < h[b]) {
                int tmp = a;
                a = b;
                b = tmp;
            }
            for (int p = power-1; p >= 0 && a != b; p--) {
                if (st[a][p]>=0 && h[st[a][p]] >= h[b]) a = st[a][p];
            }
            if (a == b) return a;
            for (int p = power-1; p >= 0; p--) {
                if (st[a][p] != st[b][p]) {
                    a = st[a][p];
                    b = st[b][p];
                }
            }
            return Math.max(st[a][0],0);
        }

        // 两点距离
        public int getDis(int x, int y) {
            return h[x] + h[y] - 2 * h[lca(x, y)];
        }

        // 两点距离的权值
        public long getWeight(int x, int y) {
            return weightDis[x] + weightDis[y] - 2L * weightDis[lca(x, y)];
        }

        // 点差分部分 u += w v += w lca -= w lcalca-= w
        public void pointDiff(int u, int v, int w) {
            if (point == null) point = new int[n + ADD_FLAG];
            if (u == v) {
                point[u] += w;
                return;
            }
            point[u] += w;
            point[v] += w;
            int uvLca = lca(u, v);
            if (uvLca != NOT_EXIST_FLAG) {
                point[uvLca] -= w;
                if (st[uvLca][0] != NOT_EXIST_FLAG) {
                    point[st[uvLca][0]] -= w;
                }
            }

        }

        public void pointDiffDfs(int u, int f) {
            for (int e = head[u]; e != NOT_EXIST_FLAG; e = nxt[e]) {
                int v = to[e], w = wt[e];
                if (v != u && v != f) {
                    pointDiffDfs(v, u);
                    point[u] += point[v];
                }
            }
        }


        // 边差分 边差分通过点来更新

        // 添加过程
        // point[u] += w
        // point[v] += w
        // point[uvlca] -= w * 2

        // 更新过程
        // point[u] += point[v]
        // weithg[uv] += point[v]
        public void edgeDiff(int u, int v, int w) {
            if (u == v) return;
            if (point == null) point = new int[n + ADD_FLAG];
            point[u] += w;
            point[v] += w;
            int uvLca = lca(u, v);
            if (uvLca != NOT_EXIST_FLAG) {
                point[uvLca] -= w * 2;
            }

        }

        public void edgeDiffDFS(int u, int f) {
            for (int e = head[u]; e != NOT_EXIST_FLAG; e = nxt[e]) {
                int v = to[e], w = wt[e];
                if (v != u && v != f) {
                    edgeDiffDFS(v, u);
                    // 更新点权
                    point[u] += point[v];
                    // 更新边权
                    wt[e] += point[v];
                }
            }
        }

        // 在边权的边上，往上跳多少权重就能到达的点
        public  int upToDis(int u,long d){
            long cur = weightDis[u];
            for(int i = power-1;i>=0;i--){
                if(st[u][i]>=0 && cur - weightDis[st[u][i]] <=d) {
                    u = st[u][i];
                }
            }
            return u;
        }

        public void init(int n,int[][] edges){
            clear(n);
            for (int[] edge : edges) {
                int u = edge[0],v = edge[1],w = edge[2];
                addEdge(u,v,w);
                addEdge(v,u,w);
            }
            dfs(0,0,1);
        }
    }

    // lca 模板结束======================================================




    static int N = (int)1e5 + 10;
    static LCA g = new LCA(N,N << 1);

    public int[] findMedian(int n, int[][] edges, int[][] queries) {
        g.init(n,edges);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0],v = queries[i][1];
            if(u==v){
                ans[i]=u;
                continue;
            }
            long dis = g.getWeight(u,v);
            long target = (dis + 1) / 2;
            // System.out.println("u = " + u + ",v = " + v + ",lca = " + g.lca(u,v));
            // 目标点再 u->lca中间
            if(g.weightDis[u] - g.weightDis[g.lca(u,v)] >= target){
                int to = g.upToDis(u,target - 1);
//                System.out.println("to = >" + to);
                ans[i] = g.st[to][0]; // 往上再跳一步
            }else{
                ans[i] = g.upToDis(v,dis - target);
            }
        }
        return ans;
    }




  

}