package leetcode.contest.weekly.w_400.w_447;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-447/problems/path-existence-queries-in-a-graph-ii">针对图的路径存在性查询 II</a>
 * @title: 针对图的路径存在性查询 II
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"pathExistenceQueries","D.txt");
    }
     

    public int[] pathExistenceQueries(int n, int[] a, int maxDiff, int[][] queries) {
        Union uf = new Union(n + 10);
        Integer[] ids = new Integer[a.length];
        Arrays.setAll(ids,i->i);
        Arrays.sort(ids, Comparator.comparingInt(x -> a[x]));
        for(int i = 0,r = 0;i < a.length;i++) {
            while(r < a.length && a[ids[r]] - a[ids[i]] <= maxDiff){
                uf.union(ids[i] + 1,ids[r] + 1);
                r++;
            }
        }
        int[] ans = new int[queries.length];
        LCA lca = new LCA(n + 10);
        for (int[] query : queries) {
            int u = query[0], v = query[1];
            u++;v++;
            lca.addEdge(u, v, 0);
        }
        lca.dfs(1,0,0);
        Arrays.fill(ans,-1);
        for(int i = 0;i < queries.length;i++){
            int u = queries[i][0],v = queries[i][1];
            u++;v++;
            if(!uf.isSame(u,v))continue;
            int lc = lca.lca(u,v);
            if(lc==u || lc == v) {
                ans[i] = Math.abs(lca.kth(u,v))<<1;
            }else{
                ans[i] = Math.abs(lca.getDis(u,v, lca.lca(u,v)));
            }
        }
        return ans;
    }



    public static class Union {
        int[] fa;
        int[] s;
        int size;

        public Union(int n) {
            fa = new int[n];
            s = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
                s[i] = 1;
            }
            size = n;
        }

        public int find(int i) {
            while (i != fa[i]) {
                fa[i] = fa[fa[i]];
                i = fa[i];
            }
            return i;
        }


        public boolean isSame(int x, int y) {
            return find(x) == find(y);
        }


        public boolean union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return false;
            } else {
                size--;
                int sx = s[x];
                int sy = s[y];
                if (sx < sy) {
                    int temp = y;
                    y = x;
                    x = temp;
                }
                fa[y] = x;
                s[x] += s[y];
                s[y] = 0;
                return true;
            }
        }
    }




    static class LCA {
        static int ADD_FLAG = 10;// 偏移量
        int h[], st[][], power, n, point[];
        List<int[]>[] g;

        public LCA(int n) {
            this.n = n;
            h = new int[n];
            g = new List[n];
            power = (int) (Math.floor(Math.log(n) / Math.log(2)));
            st = new int[n][power + 1];
            Arrays.setAll(g, i -> new ArrayList<>());
        }

//        public void addEdge(int u, int v,int w) {
//            g[u].add(new int[]{v,w});
//        }

        // 无向图的带边权的更新需要使用这种方式
            public void addEdge(int u, int v,int w) {
                g[u].add(new int[]{v, w, g[v].size()});
                g[v].add(new int[]{u, w, g[u].size() - 1});
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

        public int getDis(int x, int y, int lcaXY) {
            return h[x] + h[y] - 2 * h[lcaXY];
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
}