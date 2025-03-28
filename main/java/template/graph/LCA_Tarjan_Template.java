package template.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: Targin 求 LCA 离线查询 复杂度 O(nm)
 */
public class LCA_Tarjan_Template {


    // Tarjan 链式前向星 建图方式遍历 求 lca
    static class template_1 {

        // =========================================模板开始=================================
        private static int N = (int) 5e5 + 100, NOT_EXIST_FLAG = -1;
        private static int M = N << 1; // 无向图
        private static int fa[] = new int[N]; // 并查集
        private static int ans[] = new int[N];
        private static boolean vis[] = new boolean[N];
        private static int head[] = new int[N], nxt[] = new int[M], to[] = new int[M], cnt;
        private static int qhead[] = new int[N], qnxt[] = new int[M], qto[] = new int[M], qno[] = new int[M], qcnt;


        public static void clear(int n) {
            for (int i = 0; i <= n + 10; i++) {
                head[i] = qhead[i] = ans[i] = NOT_EXIST_FLAG;
                vis[i] = false;
                fa[i] = i;
            }
            // 如果从0开始请初始化为0
            qcnt = cnt = 1;
        }

        public static int find(int x) {
            while (x != fa[x]) {
                fa[x] = fa[fa[x]];
                x = fa[x];
            }
            return x;
        }


        public static void addEdge(int u, int v) {
            nxt[cnt] = head[u];
            to[cnt] = v;
            head[u] = cnt++;
        }

        public static void addQuery(int u, int v, int i) {
            qnxt[qcnt] = qhead[u];
            qto[qcnt] = v;
            qno[qcnt] = i;
            qhead[u] = qcnt++;
        }

        public static void targin(int u) {
            vis[u] = true;
            for (int e = head[u], v = -1; e != NOT_EXIST_FLAG; e = nxt[e]) {
                v = to[e];
                if (!vis[v]) {
                    targin(v);
                    fa[v] = u;
                }
            }
            for (int e = qhead[u], v = -1, i = -1; e != NOT_EXIST_FLAG; e = qnxt[e]) {
                v = qto[e];
                i = qno[e];
                if (vis[v])  ans[i] = find(v);
            }
        }

        // =========================================模板结束=================================

        public static void example() {
            int n = 1000, m = 1000;
            clear(n);
            for (int i = 0, u = 0, v = 0; i < n; i++) {
                addEdge(u, v);
                addEdge(v, u);
            }
            for (int i = 0, u = 0, v = 0; i < m; i++) {
                addQuery(u, v, i);
                addQuery(v, u, i);
            }
            targin(0);
            for (int i = 0; i < m; i++) {
                System.out.println(ans[i]);
            }
        }
    }


    // Tarjan 邻接矩阵方式建图 求 lca
    static class template_2 {


        // =========================================模板开始=================================
        static class Targin {
            private List<Integer>[] g;
            private List<int[]>[] query;
            private int fa[], ans[];
            private boolean vis[];
            private static final int FLAG = 10;


            // n 边的数量
            // m 为查询数量
            public Targin(int n,int m) {
                int add = FLAG + n;
                int aq = FLAG + m;
                g  = new List[add];
                query  = new List[aq];
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


            public void addEdge(int u, int v) {
                g[u].add(v);
            }

            public void addQuery(int u, int v, int i) {
                query[u].add(new int[]{u,i});
            }

            public void targin(int u) {
                vis[u] = true;
                for (int v : g[u]) {
                    if (!vis[v]) {
                        targin(v);
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

            // =========================================模板开始=================================


        }

        public static void example() {
            int n = 100,m = 20;
            Targin lca = new Targin(n,m);
            for (int i = 0, u = 0, v = 0; i < n; i++) {
                lca.addEdge(u, v);
                // 无向图
                lca.addEdge(v, u);
            }
            for (int i = 0, u = 0, v = 0; i < m; i++) {
                // 必须双向建立查询
                lca.addQuery(u, v, i);
                lca.addQuery(v, u, i);
            }
            lca.targin(0);
            for (int i = 0; i < m; i++) {
                System.out.println(lca.ans[i]);
            }
        }
    }


}
