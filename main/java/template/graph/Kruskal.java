package template.graph;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description: kruskal 又称为 K算法 是一种求最小生成树的算法 和 并查集一起应用
 * @see template.union
 */
public class Kruskal {

    static class Union {
        int MAXN = 1000001;
        int[] fa;

        int size = 0;

        public Union(int MAXN) {
            this.MAXN = MAXN;
            this.fa = new int[MAXN];
        }

        public void init(int N) {
            for (int i = 0; i < N; i++) {
                fa[i] = i;
            }
            size = N;
        }

        public boolean isSame(int x, int y) {
            return find(x) == find(y);
        }

        public boolean union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) {
                return false;
            } else {
                size--;
                fa[fy] = fx;
                return true;
            }
        }

        public int find(int i) {
            while (i != fa[i]) {
                fa[i] = fa[fa[i]];
                i = fa[i];
            }
            return fa[i];
        }


    }

    static Union union = new Union(10001);

    public static int kruskal(int[][] edges, int n) {

        union.init(n + 1);
        // from to wt
        // 根据权重排序
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        int l = 0;
        int cost = 0;
        for (int[] edge : edges) {
            // 如果两个点不是一个集合
            // 合并成功了 点数量++
            if (union.union(edge[0], edge[1])) {
                cost += edge[2]; // 加上权重
                l++;
            }
        }
        return l == n - 1 ? -1 : cost;

    }


}
