package template.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: Targin 求 LCA 离线查询 复杂度 O(nm)
 */
public class LCA_Tarjan_Template {


    // 参考链接 https://leetcode.cn/problems/minimum-weighted-subgraph-with-the-required-paths-ii/submissions/630736263/
    // Tarjan 链式前向星 建图方式遍历 求 lca
    static class template_1 {

        // =========================================模板开始=================================
        private static final int N = (int) 1e5 + 100, NOT_EXIST_FLAG = -1;
        private static final int M = N * 2;
        private static final int QUEYR_CNT = M * 2; // 求lca的询问次数
        private static final int[] fa = new int[N];
        private static final int height[] = new int[N];
        private static final long sums[] = new long[N];
        private static final boolean vis[] = new boolean[N];
        private static  int head[] = new int[N], nxt[] = new int[M], to[] = new int[M], weight[] = new int[M], cnt;
        private static  int qhead[] = new int[N], qnxt[] = new int[QUEYR_CNT], qto[] = new int[QUEYR_CNT], qno[] = new int[QUEYR_CNT], qcnt, lcaAns[] = new int[QUEYR_CNT];


        public static void clear(int n) {
            for (int i = 0; i <= n + 10; i++) {
                head[i] = qhead[i] = lcaAns[i] = NOT_EXIST_FLAG;
                vis[i] = false;
                fa[i] = i;
                height[i] = 0;
                sums[i] = 0;
            }
            qcnt = cnt = 0;
        }

        public static int find(int x) {
            while (x != fa[x]) {
                fa[x] = fa[fa[x]];
                x = fa[x];
            }
            return x;
        }


        public static void addEdge(int u, int v,int w) {
            cnt++;
            nxt[cnt] = head[u];
            to[cnt] = v;
            weight[cnt] = w;
            head[u] = cnt;
        }

        public static void addQuery(int u, int v, int i) {
            qcnt++;
            qnxt[qcnt] = qhead[u];
            qto[qcnt] = v;
            qno[qcnt] = i;
            qhead[u] = qcnt;
        }

        public static int lca(int i) {
            return lcaAns[i];
        }

        // 求两点的距离
        public static int getDis(int x, int y, int xyLca) {
            return height[x] + height[y] - 2 * height[xyLca];
        }

        public static long getWeight(int x, int y, int xyLca) {
            return sums[x] + sums[y] - 2 * sums[xyLca];
        }

        // 首次遍历需要传入原点 原点的父节点 以及初始 高度
        public static void tarjan(int u, int f, int d) {
            vis[u] = true;
            height[u] = d;
            for (int e = head[u], v = -1, w = 0; e != NOT_EXIST_FLAG; e = nxt[e]) {
                v = to[e];
                // 处理权重逻辑
                w = weight[e];
                if (!vis[v] && v != f) {
                    sums[v] = sums[u] + w;
                    tarjan(v, u, d + 1);
                    fa[v] = u;
                }
            }
            for (int e = qhead[u], v = -1, i = -1; e != NOT_EXIST_FLAG; e = qnxt[e]) {
                v = qto[e];
                i = qno[e];
                if (vis[v]) lcaAns[i] = find(v);
            }
        }

        // =========================================模板结束=================================

        public static void example() {
            int n = 1000, m = 1000;
            clear(n);
            for (int i = 0, u = 0, v = 0; i < n; i++) {
                addEdge(u, v,0);
                addEdge(v, u,0);
            }
            for (int i = 0, u = 0, v = 0; i < m; i++) {
                addQuery(u, v, i);
                addQuery(v, u, i);
            }
            tarjan(0,-1,1);
            for (int i = 0; i < m; i++) {
                System.out.println(lcaAns[i]);
            }
        }
    }


    // Tarjan 邻接矩阵方式建图 求 lca
    static class template_2 {


        // =========================================模板开始=================================
        @SuppressWarnings("all")
        static class Targin {
            private List<int[]>[] g,query;
            private int fa[], ans[],height[];
            private boolean vis[];
            private static final int FLAG = 10;


            // n 边的数量
            // m 为查询数量
            public Targin(int n,int m) {
                int add = FLAG + n;
                int aq = FLAG + m;
                g  = new List[add];
                query  = new List[aq];
                height  = new int[n];
                fa = new int[n];
                vis = new boolean[n];
                ans = new int[n];
                for (int i = 0; i < Math.max(aq,add); i++) {
                   if(i < add){
                       g[i] = new ArrayList<>();
                       fa[i] = i;
                       vis[i] = false;
                   }
                   if(i < aq) {
                       query[i] = new ArrayList<>();
                   }
                }
            }

            public int find(int x) {
                while (x != fa[x]) {
                    fa[x] = fa[fa[x]];
                    x = fa[x];
                }
                return x;
            }


            public void addEdge(int u, int v,int w) {
                g[u].add(new int[]{v,w});
            }

            public void addQuery(int u, int v, int i) {
                query[u].add(new int[]{v,i});
            }

            public void targin(int u,int f,int d) {
                height[u] = d;
                vis[u] = true;
                for (int[] e : g[u]) {
                    int v = e[0],w = e[1];
                    if (!vis[v] && f != v) {
                        targin(v,f,d + 1);
                        fa[v] = u;
                    }
                }
                for (int[] qs : query[u]) {
                    int v = qs[0], i = qs[1];
                    if (vis[v]) {
                        ans[i] = find(v);
                    }
                }
            }

            // 两点距离
            public int getDis(int x,int y,int lcaXY) {
                return height[x] + height[y] - 2 * height[lcaXY];
            }

            // =========================================模板开始=================================


        }

        public static void example() {
            int n = 100,m = 20;
            Targin lca = new Targin(n,m);
            for (int i = 0, u = 0, v = 0; i < n; i++) {
                lca.addEdge(u, v,0);
                // 无向图
                lca.addEdge(v, u,0);
            }
            for (int i = 0, u = 0, v = 0; i < m; i++) {
                // 必须双向建立查询
                lca.addQuery(u, v, i);
                lca.addQuery(v, u, i);
            }
            lca.targin(0,-1,1);
            for (int i = 0; i < m; i++) {
                System.out.println(lca.ans[i]);
            }
        }
    }


}
