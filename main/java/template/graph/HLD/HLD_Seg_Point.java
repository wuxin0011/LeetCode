package template.graph.HLD;


/**
 *
 * 参考链接 https://leetcode.cn/problems/palindromic-path-queries-in-a-tree/submissions/700598503/
 * 树链剖分 与 线段树结合 单点修改
 * @author: wuxin0011
 * @Description:
 */
public class HLD_Seg_Point {


    // 树链剖分模板开始



    static Info op(Info l, Info r) {
        Info info = new Info();
        info.x = l.x ^ r.x;
        return info;
    }


    static Info e() {
        return new Info();
    }


    static class Info {
        int x;

        public Info() {
            this.x = 0;
        }

        public Info(int c) {
            this.x = c;
        }

    }

    static class Segment {
        private Info[] tree;
        private int n;

        public Segment(int size) {
            this.n = size;
            tree = new Info[4 * size];
            for (int i = 0; i < 4 * size; i++) {
                tree[i] = e();
            }
        }

        public void build(int[] arr, int[] segId, int l, int r, int i) {
            if (l == r) {
                // segId[l] 是原始比编号
                tree[i] = new Info(arr[segId[l]]);
            } else {
                int mid = l + (r - l) / 2;
                build(arr, segId, l, mid, i * 2);
                build(arr, segId, mid + 1, r, i * 2 + 1);
                up(i);
            }
        }

        private void up(int i) {
            tree[i] = op(tree[i * 2],(tree[i * 2 + 1]));
        }

        public void update(int ql, int qr, Info info, int l, int r, int i) {
            if (l == r) {
                tree[i] = info;
            } else {
                int mid = l + (r - l) / 2;
                if (ql <= mid) {
                    update(ql, qr, info, l, mid, i * 2);
                }
                if (qr > mid) {
                    update(ql, qr, info, mid + 1, r, i * 2 + 1);
                }
                up(i);
            }
        }

        public Info query(int ql, int qr, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                return tree[i];
            }
            int mid = l + (r - l) / 2;
            Info ans = new Info();
            if (ql <= mid) {
                ans = op(ans,query(ql, qr, l, mid, i * 2));
            }
            if (qr > mid) {
                ans = op(ans,query(ql, qr, mid + 1, r, i * 2 + 1));
            }
            return ans;
        }
    }

    static class HLD {
        private int n;
        private int[] head, to, nxt, we;
        private int cnt;

        private int[] dfn, sz, son, pa, a, seg, deep, top;
        private int dfncnt;

        private Segment segTree;

        public HLD(int n) {
            this.n = n;
            int N = n + 5;
            head = new int[N];
            to = new int[N * 2];
            nxt = new int[N * 2];
            we = new int[N * 2];
            cnt = 0;

            dfn = new int[N];
            sz = new int[N];
            son = new int[N];
            pa = new int[N];
            a = new int[N];
            seg = new int[N];
            deep = new int[N];
            top = new int[N];
            dfncnt = 0;

            segTree = new Segment(n);
        }

        public void addEdge(int u, int v, int w) {
            cnt++;
            nxt[cnt] = head[u];
            to[cnt] = v;
            we[cnt] = w;
            head[u] = cnt;
        }

        private void dfs1(int u, int fa, int d) {
            sz[u] = 1;
            deep[u] = d;
            pa[u] = fa;
            for (int e = head[u]; e > 0; e = nxt[e]) {
                int v = to[e];
                if (v != fa) {
                    dfs1(v, u, d + 1);
                    sz[u] += sz[v];
                    if (son[u] == 0 || sz[son[u]] < sz[v]) {
                        son[u] = v;
                    }
                }
            }
        }

        private void dfs2(int u, int t) {
            dfncnt++;
            int id = dfncnt;
            dfn[u] = id;
            top[u] = t;
            seg[id] = u;
            if (son[u] == 0)
                return;
            dfs2(son[u], t);
            for (int e = head[u]; e > 0; e = nxt[e]) {
                int v = to[e];
                if (v != pa[u] && v != son[u]) {
                    dfs2(v, v);
                }
            }
        }

        public void build(int root, int[] values) {
            System.arraycopy(values, 1, a, 1, n);
            dfs1(root, 0, 1);
            dfs2(root, root);
            segTree.build(a, seg, 1, n, 1);
        }

        public Info pathQuery(int u, int v) {
            Info ans = new Info();
            while (top[u] != top[v]) {
                if (deep[top[u]] <= deep[top[v]]) {
                    ans = op(ans,segTree.query(dfn[top[v]], dfn[v], 1, n, 1));
                    v = pa[top[v]];
                } else {
                    ans = op(ans,segTree.query(dfn[top[u]], dfn[u], 1, n, 1));
                    u = pa[top[u]];
                }
            }
            ans = op(ans,segTree.query(Math.min(dfn[u], dfn[v]), Math.max(dfn[u], dfn[v]), 1, n, 1));
            return ans;
        }

        public void pathUpdate(int u, int v, Info info) {
            while (top[u] != top[v]) {
                if (deep[top[u]] <= deep[top[v]]) {
                    segTree.update(dfn[top[v]], dfn[v], info, 1, n, 1);
                    v = pa[top[v]];
                } else {
                    segTree.update(dfn[top[u]], dfn[u], info, 1, n, 1);
                    u = pa[top[u]];
                }
            }
            segTree.update(Math.min(dfn[u], dfn[v]), Math.max(dfn[u], dfn[v]), info, 1, n, 1);
        }


        public int lca(int u, int v) {
            while (top[u] != top[v]) {
                if (deep[top[u]] <= deep[top[v]]) {
                    v = pa[top[v]];
                } else {
                    u = pa[top[u]];
                }
            }
            return deep[u] < deep[v] ? u : v;
        }
    }

    // 树链剖分模板结束
}
