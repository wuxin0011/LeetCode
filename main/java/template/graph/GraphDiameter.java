package template.graph;

import java.util.Arrays;

// 参考题目
// https://leetcode.cn/problems/find-minimum-diameter-after-merging-two-trees/
// https://leetcode.cn/problems/longest-path-with-different-adjacent-characters/
// https://leetcode.cn/problems/count-subtrees-with-max-distance-between-cities/
// https://leetcode.cn/problems/difference-between-maximum-and-minimum-price-sum/
/**
 * 树的直径
 * 求法 自底部向上 ，每次更新之前的最大高度 + 当前求的高度
 * @author: wuxin0011
 * @Description: 树的直径 注意和重心的区别 （ 重心是求点的数量)
 */
public class GraphDiameter {




    static class Template1 {

        // https://leetcode.cn/problems/longest-path-with-different-adjacent-characters/
        static int N = (int) 1e5 + 10;
        static int head[] = new int[N], nxt[] = new int[N<<1], to[] = new int[N<<1], cnt,maxDiameter;

        static void addEdge(int u, int v) {
            ++cnt;nxt[cnt]=head[u];to[cnt]=v;head[u]=cnt;
        }

        static int getMaxDiameter(int[][] edges) {
            int n = edges.length + 1;
            cnt = maxDiameter = 0;
            Arrays.fill(head, 0, n + 1, -1);
            for (int[] e : edges) {
                addEdge(e[0], e[1]);
                addEdge(e[1],e[0]);
            }
            dfs(0, -1);
            return maxDiameter;
        }
        public static int dfs(int u, int f) {
            int maxLen = 0;
            for (int e = head[u]; e != -1; e = nxt[e]) {
                if (to[e] != f) {
                    int subLen = dfs(to[e], u) + 1;
                    maxDiameter = Math.max(maxDiameter, maxLen + subLen);
                    maxLen = Math.max(maxLen, subLen);
                }
            }
            return maxLen;
        }


        // 两个图中 任意两个点合并后 最小直径要么不变 （如果其中一个图非常小) 要么为 各自一半 向上取整 + 1 （ 如果两个图直径差不多)
        public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
            int d1 = getMaxDiameter(edges1);
            int d2 = getMaxDiameter(edges2);
            return Math.max(d1,Math.max((d1 + 1) / 2 + (d2 + 1) / 2 + 1,d2));
        }

    }
}

