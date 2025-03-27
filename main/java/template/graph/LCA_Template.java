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


    static class TreeAncestor {
        int deep[], st[][], power, n;
        List<Integer>[] g;

        public void pre(int n) {
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

        public TreeAncestor(int n, int[] parent) {
            this.pre(n);
            if (parent != null) {
                for (int i = 1; i < n; i++) {
                    addEdge(parent[i], i);
                }
                dfs(0, 0);
            }
        }

        public TreeAncestor(int n, int[][] edges) {
            pre(n);
            if (edges != null) {
                for (int[] e : edges) {
                    addEdge(e[0], e[1]);
//                addEdge(e[1],e[0]);
                }
                dfs(0, 0);
            }
        }

        public void dfs(int u, int fa) {
            deep[u] = (u != 0 ? deep[fa] : 0) + 1;
            st[u][0] = fa;
            for (int p = 1; (1 << p) <= deep[u]; p++) {
                st[u][p] = st[st[u][p - 1]][p - 1];
            }
            for (int v : g[u]) {
                if (v != u && v != fa) {
                    dfs(v, u);
                }
            }
        }

        /**
         * 求点 i 往上跳K步 的节点
         *
         * @param i 当前节点
         * @param k step
         * @return x
         */
        public int getKthAncestor(int i, int k) {
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
         *
         * @param a 点a
         * @param b 点b
         * @return lca
         */
        public int lca(int a, int b) {
            if (a == b) return a;
            if (deep[b] > deep[a]) {
                int temp = a;
                a = b;
                b = temp;
            }
            for (int p = power; p >= 0; p--) {
                // 处理完毕 ab处于同一高度
                if (deep[st[a][p]] >= deep[st[b][p]]) {
                    a = st[a][p];
                }
            }
            if (a == b) return a;
            for (int p = power; p >= 0; p--) {
                if (st[a][p] != st[b][p]) {
                    a = st[a][p];
                    b = st[b][p];
                }
            }
            // 返回a的父节点
            return st[a][0];
        }
    }


}
