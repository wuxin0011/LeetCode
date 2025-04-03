package template.graph;

import java.util.List;

/**
 * 因为Java会超时 这里不写完整的代码 只写基本逻辑
 *
 * 树的重心相关性质 （非森林图）
 * 1、最多有两个重心 如果存在两个重心 那么这两个重心相邻 (<a href="https://codeforces.com/contest/1406/submission/313673924">参考题</a>)
 * 2、如果是两个森林图合并 新的重心一定在原来两个森林重心链接的路径上
 *
 * @author: wuxin0011
 * @Description: 树的重心
 */
public class GraphCenter {


    // 基本树的重心求法
    // 参考题 https://www.luogu.com.cn/problem/U104609
    static class GraphCenter1 {
        static int n; // 点的数量n, 编号[1,n]
        static int[] size, sub; // size 为大小，sub 除去该点为 其他点的最大点集
        static List<Integer>[] g; // 图

        public static void dfs(int u, int f) {
            size[u] = 1;
            for (int v : g[u]) {
                if (v != f) {
                    dfs(v, u);
                    size[u] += size[v];
                    sub[u] = Math.max(sub[u], size[v]);
                }
            }
            sub[u] = Math.max(sub[u], n - size[u]);
        }

        public static void printGraphCenter() {
            for (int i = 1; i <= n; i++) {
                if (sub[i] <= n / 2) {
                    System.out.println(i);
                }
            }
        }

    }


    // 带权的重心
    // https://www.luogu.com.cn/problem/P2986
    static class GraphCenter2 {
        static int n; // 点的数量n, 编号[1,n]
        static int[] size, sub, path, weight; // size 为大小，sub 除去该点为 其他点的最大点集,weight 为每个点的权重,path 为每个点到重心的权重距离
        static List<int[]>[] g; // 图

        static int inf = (int)1e9;
        static int center = -1,maxWeight = inf,totWeight = 0; // totWeight 为总点权重

        public static void dfs(int u, int f) {
            size[u] = weight[u];
            for (int[] e : g[u]) {
                int v = e[0], w = e[1];
                if (v != f) {
                    dfs(v, u);
                    size[u] += size[v];
                    sub[u] = Math.max(sub[u], size[v]);
                }
            }
            sub[u] = Math.max(sub[u], totWeight - size[u]);
            if(sub[u]<maxWeight){
                center = u;
                maxWeight = sub[u];
            }
        }

        // 如果存在边权 求每个点到树重心的距离
        // 先序遍历求
        public static void dis(int u, int f) {
            path[u] = 0;
            for (int[] e : g[u]) {
                int v = e[0], w = e[1];
                if (v != f) {
                    path[v] = path[u] + w;
                    dis(v, u);
                }
            }
        }


        public static void printGraphCenter() {
            System.out.println(center);
        }

    }
}

