package template.graph;


/**
 * @see leetcode._0x3f_.graph.topological_sorting.Solution_0001.Solution
 * @see leetcode._0x3f_.graph.topological_sorting.Solution_0002.Solution
 * @see leetcode._0x3f_.graph.topological_sorting.Solution_0003.Solution
 * @author: wuxin0011
 * @Description: 链式向前星建图
 */

@SuppressWarnings("all")
public class LinkStartBuildGraph {




    static class template_1 {
        // 模板
        // https://leetcode.cn/problems/course-schedule-ii/submissions/571351678/
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            clear(numCourses);

            for (int[] e : prerequisites) {
                int u = e[1], v = e[0], w = 0;
                addEdge(u, v, w);
                in[v]++;
            }
            for (int i = 0; i < numCourses; i++) {
                if (in[i] == 0) {
                    q[r++] = i;
                }
            }
            int[] ans = new int[numCourses];
            int i = 0;
            while (l < r) {
                int x = q[l++];
                ans[i++] = x;
                numCourses--;
                for (int e = head[x]; e >= 0; e = edges[e].nxt) {
                    int v = edges[e].to;
                    in[v]--;
                    if (in[v] == 0) {
                        q[r++] = v;
                    }
                }
            }
            return numCourses == 0 ? ans : empty;
        }


        // =====================================链式前向星建图开始

        private static final int MAXN = (int) 1e5 + 10,NOT_EXIST_FLAG = -1;
        private static final int EDGE_MAXN = (int) 1e5 + 10;


        private static int[] in = new int[MAXN], q = new int[MAXN], head = new int[MAXN], empty = new int[]{};
        private static int cnt, l, r;
        private static Edge[] edges = new Edge[EDGE_MAXN];

        private static void clear(int n) {
            for (int i = 0; i <= n; i++) {
                in[i] = 0;
                head[i] = NOT_EXIST_FLAG;
            }
            cnt = 0;
            l = r = 0;
        }

        private static void addEdge(int u, int v, int w) {
            if (edges[cnt] == null) {
                edges[cnt] = new Edge(head[u], v, w);
            } else {
                edges[cnt].update(head[u], v, w);
            }
            head[u] = cnt++;
        }

        private static class Edge {
            int w, nxt, to;
            Edge() {}
            Edge(int next, int to, int w) {
                this.update(next, to, w);
            }
            void update(int nxt, int to, int w) {
                this.nxt = nxt;
                this.to = to;
                this.w = w;
            }
        }

        // =====================================链式前向星建图结束

    }




    // 不使用结构体方式更加节省空间
    static class template_2 {

        // =========================================链式向前星建图模板开始=================================
        private static int N = (int) 1e5 + 100, NOT_EXIST_FLAG = -1;
        private static int M = N << 1; // 无向图
        private static int head[] = new int[N], nxt[] = new int[M], to[] = new int[M],weight[] = new int[M], cnt;


        public static void clear(int n) {
            for (int i = 0; i <= n + 10; i++) {
                head[i] = NOT_EXIST_FLAG;
            }
            cnt=0;
        }
        public static void addEdge(int u, int v,int w) {
            nxt[cnt] = head[u];
            to[cnt] = v;
            weight[cnt] = w;
            head[u] = cnt++;
        }


        // =========================================模板结束=================================

        public static void example() {
            int n = 1000, m = 1000;
            clear(n);
            for (int i = 0, u = 0, v = 0; i < n; i++) {
                addEdge(u, v,0);
                addEdge(v, u,0);
            }

            // 遍历
            int u = 0;

            //
            for (int e = head[u], v = -1, w = 0; e != NOT_EXIST_FLAG; e = nxt[e]) {
                v = to[e];
                w = weight[e];
            }

        }
    }


    // 封装成结构体 适合多图
    static class template_3 {

        // =========================================链式向前星建图模板开始=================================


        public static class Edge {
            int head[], nxt[], to[], weight[], cnt;

            Edge(int n, int m) {
                head = new int[n];
                nxt = new int[m];
                weight = new int[m];
                to = new int[m];
                cnt = 0;
            }

        }

        static void addEdge(Edge e, int u, int v, int w) {
            e.cnt++;
            e.nxt[e.cnt] = e.head[u];
            e.to[e.cnt] = v;
            e.weight[e.cnt] = w;
            e.head[u] = e.cnt;
        }

        private static final int N = (int) 1e5 + 10, NO_EXIST_FLAG = -1;
        private static final Edge E1 = new Edge(N, N << 1);

        private static void clearEdge(int n) {
            for (int i = 0; i <= n; i++) {
                E1.head[i] = NO_EXIST_FLAG;
            }
            E1.cnt = 0;
        }
        // =========================================链式向前星建图模板结束=================================


        public static void dfs(Edge e, int u, int f) {
            for (int curId = e.head[u]; curId != NO_EXIST_FLAG; curId = e.nxt[curId]) {
                int v = e.to[curId];
                int w = e.weight[curId];
                if (v != f) {
                    dfs(e, v, u);
                }
            }
        }

        private static void example(int[][] edges) {
            int n = edges.length;
            clearEdge(n);
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                addEdge(E1, u, v, w);
                addEdge(E1, u, u, w);
            }
            dfs(E1, 0, -1);
        }


    }

}
