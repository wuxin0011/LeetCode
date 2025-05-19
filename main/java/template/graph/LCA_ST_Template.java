package template.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/kth-ancestor-of-a-tree-node/">树节点的第 K 个祖先</a>
 * <a href="https://leetcode.cn/problems/minimum-edge-weight-equilibrium-queries-in-a-tree//">边权重均等查询</a> 如果题目权重很大 但是类型不多，可以用 list(set) 保存遍历
 *
 * @author: wuxin0011
 * @Description: 树上倍增 最低公共祖先 复杂度 nmlog(m)
 * @see Lca_1 邻接矩阵建图求LCA
 * @see Lca_2 链式前向星方式建图求LCA
 * @see BuildEdge.Lca_3 离散化建图
 */
@SuppressWarnings("all")
public class LCA_ST_Template {


    /**
     * 使用方法 1、初始化 2、建图 3、调用dfs
     */
    static class Lca_1 {


        // lca 模板开始===================================================
        @SuppressWarnings("all")
        static class LCA {
            static int ADD_FLAG = 10;// 偏移量
            int h[], st[][], power, n, point[];
            List<int[]>[] g;

            long[] sums;

            public LCA(int n) {
                this.n = n;
                h = new int[n];
                g = new List[n];
                sums = new long[n];
                power = (int) (Math.floor(Math.log(n) / Math.log(2)));
                st = new int[n][power + 1];
                Arrays.setAll(g, i -> new ArrayList<>());
            }

            public void addEdge(int u, int v,int w) {
                g[u].add(new int[]{v,w});
            }

            // 无向图的带边权的更新需要使用这种方式
//            public void addEdge(int u, int v,int w) {
//                g[u].add(new int[]{v, w, g[v].size()});
//                g[v].add(new int[]{u, w, g[u].size() - 1});
//            }


            public void dfs(int u, int fa, int height) {
                h[u] = height + 1;
                st[u][0] = fa;
                for (int p = 1; p <= power; p++) {
                    st[u][p] = st[st[u][p - 1]][p - 1];
                }
                for (int[] e : g[u]) {
                    int v = e[0],w = e[1];
                    if (v != u && v != fa) {
                        sums[v] = sums[u] + w;
                        dfs(v, u, height + 1);
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

            /**
             * 求 点 a，b 最低公共祖先
             */
            public int lca(int a, int b) {
                if (a == b) return a;
                if (h[a] < h[b]) {
                    int tmp = a;
                    a = b;
                    b = tmp;
                }
                for (int p = power; p >= 0; p--) {
                    if (h[st[a][p]] >= h[b]) a = st[a][p];
                }
                if (a == b) return a;
                for (int p = power; p >= 0; p--) {
                    if (st[a][p] != st[b][p]) {
                        a = st[a][p];
                        b = st[b][p];
                    }
                }
                return st[a][0];
            }

            public int getDis(int x, int y) {
                return h[x] + h[y] - 2 * h[lca(x,y)];
            }

            public long getWegith(int x, int y) {
                return sums[x] + sums[y] - 2 * sums[lca(x,y)];
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
                if (uvLca != -1) {
                    point[uvLca] -= w;
                    if (st[uvLca][0] != -1) {
                        point[st[uvLca][0]] -= w;
                    }
                }
            }

            public void pointDiffDfs(int u, int f) {
                for (int[] e : g[u]) {
                    int v = e[0], w = e[1];
                    if (v != u && v != f) {
                        pointDiffDfs(v, u);
                        if (v != u && v != f) {
                            point[u] += point[v];
                        }
                    }
                }
            }


            public void edgeDiff(int u, int v, int w) {
                if (u == v) return;
                if (point == null) point = new int[n + ADD_FLAG];
                point[u] += w;
                point[v] += w;
                int uvLca = lca(u, v);
                if (uvLca != -1) {
                    point[uvLca] -= w * 2;
                }
            }

            public void edgeDiffDFS(int u, int f) {
                for (int[] e : g[u]) {
                    int v = e[0], w = e[1];
                    if (v != u && v != f) {
                        edgeDiffDFS(v, u);
                        point[u] += point[v];
                        e[1] += point[v];
                        // 如果是无向图需要关闭下面注释 同时上面addEdge方法需要附带索引
                        // g[v].get(e[2])[1] += point[v]; // 同步更新v→u的边权
                    }
                }
            }

        }

        // lca 模板结束===================================================



        // example
        public void example_test(int n,int[][] edges,int[][] queries) {
            LCA lca = new LCA(n);
            for(int[] e : edges){
                int u = e[0],v = e[1];
                lca.addEdge(u,v,0);
            }
            lca.dfs(0,0,1);
            for(int[] q : queries ) {
                int u = q[0],v = q[1];
                // 求最低公共祖先
                System.out.println(lca.lca(u,v));
            }

        }

    }


    /**
     * 链式前向星建图
     * <p>
     * 使用方法 1、初始化 2、建图 3、调用dfs
     */
    static class Lca_2 {


        // lca 模板开始===================================================

        @SuppressWarnings("all")
        static class LCA {
            static int ADD_FLAG = 20, NOT_EXIST_FLAG = -1;
            int h[], st[][], power, n, m, nxt[], to[], head[], weight[], cnt, point[];
            long sums[];

            // n 表示点数
            // m 表示边数
            public LCA(int n, int m) {
                this.n = n;
                this.m = m;
                h = new int[n + ADD_FLAG];
                nxt = new int[m + ADD_FLAG];
                to = new int[m + ADD_FLAG];
                weight = new int[m + ADD_FLAG];
                sums = new long[m + ADD_FLAG];
                head = new int[n + ADD_FLAG];
                power = (int) (Math.floor(Math.log(n) / Math.log(2)));
                st = new int[n][power + 1];
                clear(n);
            }

            public void clear(int n) {
                cnt = 0;
                power = (int) (Math.floor(Math.log(n) / Math.log(2)));
                for (int i = 0; i <= n + 5; i++) {
                    head[i] = NOT_EXIST_FLAG;
                    h[i] = 0;
                }
            }

            public void addEdge(int u, int v,int w) {
                cnt++;
                nxt[cnt] = head[u];
                to[cnt] = v;
                weight[cnt] = w;
                head[u] = cnt;
            }

            // dfs(0,0,1)
            public void dfs(int u, int fa) {
                dfs(u, fa, 1);
            }

            public void dfs(int u, int fa, int d) {
                h[u] = d;
                st[u][0] = fa;
                for (int p = 1; p <= power; p++) {
                    st[u][p] = st[st[u][p - 1]][p - 1];
                }
                for (int e = head[u]; e > 0; e = nxt[e]) {
                    int v = to[e], w = weight[e];
                    if (v != u && v != fa) {
                        // 如果是带权边
                        sums[v] = sums[u] + w;
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
                for (int p = power; p >= 0; p--) {
                    if (h[st[a][p]] >= h[b]) a = st[a][p];
                }
                if (a == b) return a;
                for (int p = power; p >= 0; p--) {
                    if (st[a][p] != st[b][p]) {
                        a = st[a][p];
                        b = st[b][p];
                    }
                }
                return st[a][0];
            }

            // 两点距离
            public int getDis(int x, int y) {
                return h[x] + h[y] - 2 * h[lca(x, y)];
            }

            // 两点距离的权值
            public long getWeight(int x, int y) {
                return sums[x] + sums[y] - 2 * sums[lca(x, y)];
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
                    int v = to[e], w = weight[e];
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
                    int v = to[e], w = weight[e];
                    if (v != u && v != f) {
                        edgeDiffDFS(v, u);
                        // 更新点权
                        point[u] += point[v];
                        // 更新边权
                        weight[e] += point[v];
                    }
                }
            }


        }

        // lca 模板结束======================================================


        // example


        void example_test(int n, int[][] edges) {
            int N = (int)1e5;
            LCA lca = new LCA(N,N<<1);
            // 必须调用步骤
            // 1 如果是静态需要clear
            lca.clear(n);
            // 2 建图
            for (int[] e : edges) lca.addEdge(e[0], e[1], e[2]);
            // 3 dfs
            lca.dfs(0, 0, 1);

            //  根据题意修改
            // a 查询
            System.out.println(lca.lca(0, 1));
            // b、求两点距离
            System.out.println("dis = " + (lca.getDis(0, 1)));
            // c、求两点权值
            System.out.println("dis = " + (lca.getWeight(0, 1)));
        }

    }



}
