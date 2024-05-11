package leetcode.ox3if.data_struct.heap.advanced.Solution_0005;

import code_generation.utils.IoUtil;

import java.util.*;

/**
 * 1631. 最小体力消耗路径
 * <p>
 * 你准备参加一场远足活动。
 * 给你一个二维rows x columns的地图heights，其中heights[row][col]表示格子(row, col)的高度。一开始你在最左上角的格子(0, 0)，且你希望去最右下角的格子(rows-1, columns-1)（注意下标从 0 开始编号）。你每次可以往 上，下，左，右四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * 一条路径耗费的 体力值是路径上相邻格子之间 高度差绝对值的 最大值决定的。
 * 请你返回从左上角走到右下角的最小体力消耗值。
 * <p>
 * 示例 1：
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 * <p>
 * 示例 2：
 * 输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
 * 输出：1
 * 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
 * <p>
 * 示例 3：
 * 输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * 输出：0
 * 解释：上图所示路径不需要消耗任何体力。
 * <p>
 * 提示：
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 10^6
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/path-with-minimum-effort
 * @title: 最小体力消耗路径
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minimumEffortPath", "in.txt");
    }

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int id = i * n + j;
                if (i > 0) {
                    edges.add(new Edge(id - n, id, Math.abs(heights[i][j] - heights[i - 1][j])));
                }
                if (j > 0) {
                    edges.add(new Edge(id - 1, id, Math.abs(heights[i][j] - heights[i][j - 1])));
                }
            }
        }

        Collections.sort(edges);
        UF uf = new UF(m * n);

        for (Edge edge : edges) {
            uf.unite(edge.x, edge.y);
            if (uf.connected(0, m * n - 1)) {
                return edge.z;
            }
        }

        return 0;
    }


    static class UF {
        private final int[] fa;
        private final int[] sz;
        private int comp_cnt;

        public UF(int _n) {
            comp_cnt = _n;
            fa = new int[_n];
            sz = new int[_n];
            Arrays.fill(sz, 1);
            for (int i = 0; i < _n; i++) {
                fa[i] = i;
            }
        }

        private int findset(int x) {
            return fa[x] == x ? x : (fa[x] = findset(fa[x]));
        }

        public void unite(int x, int y) {
            x = findset(x);
            y = findset(y);
            if (x == y) {
                return;
            }
            if (sz[x] < sz[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            fa[y] = x;
            sz[x] += sz[y];
            comp_cnt--;
        }

        public boolean connected(int x, int y) {
            x = findset(x);
            y = findset(y);
            return x == y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int x, y, z;

        Edge(int _x, int _y, int _z) {
            x = _x;
            y = _y;
            z = _z;
        }

        @Override
        public int compareTo(Edge that) {
            return Integer.compare(z, that.z);
        }
    }


}