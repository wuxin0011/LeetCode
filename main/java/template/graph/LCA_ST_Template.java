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
 */
public class LCA_ST_Template {


    /**
     * 使用方法 1、初始化 2、建图 3、调用dfs
     */
    static class Lca_template1 {


        // lca 模板开始===================================================
        @SuppressWarnings("all")
        static class LCA {
            int h[], st[][], power, n;
            List<int[]>[] g;

            public LCA(int n) {
                this.n = n;
                h = new int[n];
                g = new List[n];
                power = (int) (Math.floor(Math.log(n) / Math.log(2)));
                st = new int[n][power + 1];
                Arrays.setAll(g, i -> new ArrayList<>());
            }

            public void addEdge(int u, int v,int w) {
                g[u].add(new int[]{v,w});
            }


            public void dfs(int u, int fa, int height) {
                h[u] = height + 1;
                st[u][0] = fa;
                for (int p = 1; p <= power; p++) {
                    st[u][p] = st[st[u][p - 1]][p - 1];
                }
                for (int[] e : g[u]) {
                    int v = e[0],w = e[1];
                    if (v != u && v != fa) {
                        dfs(v, u, height + 1);
                    }
                }
            }

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

            public int getDis(int x,int y,int lcaXY) {
                return h[x] + h[y] - 2 * h[lcaXY];
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
            int h[], st[][], power, n, m, nxt[], to[], head[],weight[], cnt;

            // n 表示点数
            // m 表示边数
            public LCA(int n, int m) {
                this.n = n;
                this.m = m;
                h = new int[n + ADD_FLAG];
                nxt = new int[m + ADD_FLAG];
                to = new int[m + ADD_FLAG];
                weight = new int[m + ADD_FLAG];
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
                nxt[cnt] = head[u];
                to[cnt] = v;
                weight[cnt] = w;
                head[u] = cnt;
                cnt++;
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
                for (int e = head[u]; e != NOT_EXIST_FLAG; e = nxt[e]) {
                    int v = to[e],w = weight[e];
                    if (v != u && v != fa) {
                        dfs(v, u, d + 1);
                    }
                }
            }

            public int kth(int i, int k) {
                if (h[i] <= k) return -1;
                int high = h[i] - k;
                for (int p = power; p >= 0; p--) {
                    if (h[st[i][p]] >= high) i = st[i][p];
                }
                return i;
            }

            public int lca(int a, int b) {
                if (h[a] < h[b]) {
                    int tmp = a;
                    a = b;
                    b = tmp;
                }
                for (int p = power; p >= 0; p--) {
                    if (h[st[a][p]] >= h[b]) a = st[a][p];
                }
                if (a == b) {
                    return a;
                }
                for (int p = power; p >= 0; p--) {
                    if (st[a][p] != st[b][p]) {
                        a = st[a][p];
                        b = st[b][p];
                    }
                }
                return st[a][0];
            }

            public int getDis(int x,int y,int lcaXY) {
                return h[x] + h[y] - 2 * h[lcaXY];
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
            for (int[] e : edges) lca.addEdge(e[0], e[1],e[2]);
            // 3 dfs
            lca.dfs(0, 0, 1);

            //  根据题意修改
            // a 查询
            System.out.println(lca.lca(0,1));
            // b、求两点距离
            System.out.println("dis = " + (lca.getDis(0,1,lca.lca(0,1))));
        }

    }





}
