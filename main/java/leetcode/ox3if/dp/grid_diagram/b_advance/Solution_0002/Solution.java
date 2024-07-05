package leetcode.ox3if.dp.grid_diagram.b_advance.Solution_0002;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 2435. 矩阵中和能被 K 整除的路径
 * <p>
 * 给你一个下标从 0开始的m x n整数矩阵grid和一个整数k。
 * 你从起点(0, 0)出发，每一步只能往 下或者往 右，你想要到达终点(m - 1, n - 1)。
 * 请你返回路径和能被 k整除的路径数目，由于答案可能很大，返回答案对10^9 + 7取余的结果。
 * <p>
 * 示例 1：
 * 输入：grid = [[5,2,4],[3,0,5],[0,7,2]], k = 3
 * 输出：2
 * 解释：有两条路径满足路径上元素的和能被 k 整除。
 * 第一条路径为上图中用红色标注的路径，和为 5 + 2 + 4 + 5 + 2 = 18 ，能被 3 整除。
 * 第二条路径为上图中用蓝色标注的路径，和为 5 + 3 + 0 + 5 + 2 = 15 ，能被 3 整除。
 * <p>
 * 示例 2：
 * 输入：grid = [[0,0]], k = 5
 * 输出：1
 * 解释：红色标注的路径和为 0 + 0 = 0 ，能被 5 整除。
 * <p>
 * 示例 3：
 * 输入：grid = [[7,3,4,9],[2,3,6,2],[2,3,7,0]], k = 1
 * 输出：10
 * 解释：每个数字都能被 1 整除，所以每一条路径的和都能被 k 整除。
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 5 * 10^4
 * 1 <= m * n <= 5 * 10^4
 * 0 <= grid[i][j] <= 100
 * 1 <= k <= 50
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/paths-in-matrix-whose-sum-is-divisible-by-k
 * @title: paths-in-matrix-whose-sum-is-divisible-by-k
 */
public class Solution {


    // tips 一般这种求和 mod k 问题 可以先 mod 这样范围就小很多
    // 相似题目
    // http://leetcode.cn/contest/weekly-contest-404/problems/find-the-maximum-length-of-valid-subsequence-i/
    // http://leetcode.cn/contest/weekly-contest-404/problems/find-the-maximum-length-of-valid-subsequence-ii/


    // 分析 既然要到达终点 那么只能遍历到终点了
    //

    // 递推
    static class S0 {
        public static void main(String[] args) {
            IoUtil.testUtil(S0.class, "numberOfPaths", "in.txt");
        }

        private static final int MOD = (int) 1e9 + 7;

        public int numberOfPaths(int[][] grid, int k) {
            int m = grid.length, n = grid[0].length;
            int[][][] f = new int[m + 1][n + 1][k];
            f[0][1][0] = 1;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int v = grid[i][j];
                    for (int z = 0; z < k; z++) {
                        f[i + 1][j + 1][(z + v) % k] = (f[i + 1][j][z] + f[i][j + 1][z]) % MOD;
                    }
                }
            }
            return f[m][n][0];
        }

    }


    // 记忆化搜索

    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "numberOfPaths", "in.txt");
        }

        private static final int MOD = (int) 1e9 + 7;

        public int numberOfPaths(int[][] grid, int k) {
            this.k = k;
            this.m = grid.length;
            this.n = grid[0].length;
            this.grid = grid;
            memo = new int[m][n][k];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    Arrays.fill(memo[i][j], -1);
                }
            }
            return dfs(0, 0, 0);
        }

        int k, m, n;
        int[][][] memo;
        int[][] grid;

        public int dfs(int i, int j, int c) {
            c %= k;
            if (i == m - 1 && j == n - 1) {
                c += grid[i][j];
                return c % k == 0 ? 1 : 0;
            }
            if (memo[i][j][c] != -1) return memo[i][j][c];
            int res = 0;
            if (i + 1 < m) {
                res = (dfs(i + 1, j, c + grid[i][j]) + res) % MOD;
            }
            if (j + 1 < n) {
                res = (dfs(i, j + 1, c + grid[i][j]) + res) % MOD;
            }
            memo[i][j][c] = res;
            return res;
        }
    }


}