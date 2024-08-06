package template.union;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
public class UnionIdx {


    // 和数组下标相关的并查集
    // @see https://leetcode.cn/problems/smallest-string-with-swaps/
    // @see https://leetcode.cn/problems/lexicographically-smallest-equivalent-string
    public static class Union<T> {
        int[] fa;
        int[] s;
        int size;
        List[] ids;
        boolean[] vis;

        public Union(int n) {
            fa = new int[n];
            s = new int[n];
            ids = new ArrayList[n];
            vis = new boolean[n];
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

        public boolean union(int x, T t0, int y, T t1) {
            int idx0 = x, idx1 = y;
            x = find(x);
            y = find(y);
            if (x == y) {
                return false;
            } else {
                size--;
                int sx = s[x];
                int sy = s[y];
                if (sy > sx) {
                    int temp = x;
                    x = y;
                    y = temp;
                }
                List<Node> nodes = null;
                nodes = ids[x];
                if (nodes == null) {
                    ids[x] = new ArrayList<Node>();
                    nodes = ids[x];
                }
                fa[y] = x;
                s[x] += s[y];
                s[y] = 0;
                if (ids[y] != null) {
                    nodes.addAll(ids[y]);
                    ids[y].clear();
                }
                if (!vis[idx0]) {
                    nodes.add(new Node(idx0, t0));
                    vis[idx0] = true;
                }
                if (!vis[idx1]) {
                    nodes.add(new Node(idx1, t1));
                    vis[idx1] = true;
                }
                return true;
            }
        }
    }


    public static class Node<T> {
        int index;
        T c;

        // 补充更多于ID绑定的内容
        Node(int index, T t) {
            this.c = t;
            this.index = index;
        }
    }
}
