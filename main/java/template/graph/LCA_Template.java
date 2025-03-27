package template.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/kth-ancestor-of-a-tree-node/">树节点的第 K 个祖先</a>
 * <a href="https://leetcode.cn/problems/minimum-edge-weight-equilibrium-queries-in-a-tree//">边权重均等查询</a>
 *
 * @author: wuxin0011
 * @Description: 树上倍增 最低公共祖先
 */
public class LCA_Template {

// lca 模板开始===================================================

    /**
     * 使用方法 1、初始化 2、建图 3、调用dfs
     */
    static class Lca {
        int deep[], st[][], power, n;
        List<Integer>[] g;

        public void init(int n) {
            this.n = n;
            deep = new int[n];
            g = new List[n];
            power = (int) (Math.floor(Math.log(n) / Math.log(2)));
            st = new int[n][power + 1];
            Arrays.setAll(g, i -> new ArrayList<>());
        }

        public void addEdge(int u, int v) {
            g[u].add(v);
        }


        public void dfs(int u, int fa, int height) {
            deep[u] = height + 1;
            st[u][0] = fa;
            for (int p = 1; p <= power; p++) {
                st[u][p] = st[st[u][p - 1]][p - 1];
            }
            for (int v : g[u]) {
                if (v != u && v != fa) {
                    dfs(v, u, height + 1);
                }
            }
        }

        public int kth(int i, int k) {
            if (deep[i] <= k)
                return -1;
            int high = deep[i] - k;
            for (int p = power; p >= 0; p--) {
                if (deep[st[i][p]] >= high) {
                    i = st[i][p];
                }
            }
            return i;
        }

        /**
         * 求 点 a，b 最低公共祖先
         */
        public int lca(int a, int b) {
            if (deep[a] < deep[b]) {
                int tmp = a;
                a = b;
                b = tmp;
            }
            for (int p = power; p >= 0; p--) {
                if (deep[st[a][p]] >= deep[b]) {
                    a = st[a][p];
                }
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
    }

    // lca 模板结束===================================================


    // lca 模板开始===================================================


    /**
     * 链式前向星建图
     * <p>
     * 使用方法 1、初始化 2、建图 3、调用dfs
     */
    static class Lca_2 {

        static int REMOVE_FLAG = 10,NOT_EXIST_FLAG = -1;
        int deep[], st[][], power, n, nxt[], to[], head[], cnt;

        // n 表示点数
        // m 表示边的数量
        public void init(int n, int m) {
            this.n = n;
            deep = new int[n + REMOVE_FLAG];
            nxt = new int[m + REMOVE_FLAG];
            to = new int[m + REMOVE_FLAG];
            head = new int[n + REMOVE_FLAG];
            Arrays.fill(head, NOT_EXIST_FLAG);
            power = (int) (Math.floor(Math.log(n) / Math.log(2)));
            st = new int[n][power + 1];
            cnt = 0;
        }

        public void clear(int n) {
            cnt = 0;
            for (int i = 0; i < n; i++) {
                head[i] = NOT_EXIST_FLAG;
                deep[i] = 0;
            }
        }

        public void addEdge(int u, int v) {
            nxt[cnt] = head[u];
            to[cnt] = v;
            head[u] = cnt;
            cnt++;
        }


        // dfs(0,0,1)
        public void dfs(int u, int fa, int height) {
            deep[u] = height;
            st[u][0] = fa;
            for (int p = 1; p <= power; p++) {
                st[u][p] = st[st[u][p - 1]][p - 1];
            }
            for (int e = head[u]; e != NOT_EXIST_FLAG; e = nxt[e]) {
                int v = to[e];
                if (v != u && v != fa) {
                    dfs(v, u, height + 1);
                }
            }
        }

        public int kth(int i, int k) {
            if (deep[i] <= k)
                return -1;
            int high = deep[i] - k;
            for (int p = power; p >= 0; p--) {
                if (deep[st[i][p]] >= high) {
                    i = st[i][p];
                }
            }
            return i;
        }

        public int lca(int a, int b) {
            if (deep[a] < deep[b]) {
                int tmp = a;
                a = b;
                b = tmp;
            }
            for (int p = power; p >= 0; p--) {
                if (deep[st[a][p]] >= deep[b]) {
                    a = st[a][p];
                }
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
    }


    // lca 模板结束======================================================

    // example
    //    static Lca_2 lca = new Lca_2();
    //    static int N = (int)6e4;
    //    static {
    //        lca.init(N,N);
    //    }
    //    void run_method(int n,int[][] edges){
    //        lca.clear(n);
    //        for(int[] e : edges) lca.addEdge(e[0],e[1]);
    //        lca.dfs(0,0,1);
    //    }


}
