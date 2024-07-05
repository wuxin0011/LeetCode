package leetcode.ox3if.dp.grid_diagram.b_advance.Solution_0001;

import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 1301. 最大得分的路径数目
 * <p>
 * 给你一个正方形字符数组board，你从数组最右下方的字符'S'出发。
 * 你的目标是到达数组最左上角的字符'E' ，数组剩余的部分为数字字符1, 2, ..., 9或者障碍 'X'。在每一步移动中，你可以向上、向左或者左上方移动，可以移动的前提是到达的格子没有障碍。
 * 一条路径的 「得分」 定义为：路径上所有数字的和。
 * 请你返回一个列表，包含两个整数：第一个整数是 「得分」 的最大值，第二个整数是得到最大得分的方案数，请把结果对10^9 + 7 取余。
 * 如果没有任何路径可以到达终点，请返回[0, 0] 。
 * <p>
 * 示例 1：
 * 输入：board = ["E23","2X2","12S"]
 * 输出：[7,1]
 * <p>
 * 示例 2：
 * 输入：board = ["E12","1X1","21S"]
 * 输出：[4,2]
 * <p>
 * 示例 3：
 * 输入：board = ["E11","XXX","11S"]
 * 输出：[0,0]
 * <p>
 * 提示：
 * 2 <= board.length == board[i].length <= 100
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-paths-with-max-score
 * @title: 最大得分的路径数目
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "pathsWithMaxScore", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    // 先求最大值
    // 找出最大值后求方案数
    int[][] f;
    char[][] grid;
    int m, n;
    int[][][] memo;

    public int[] pathsWithMaxScore(List<String> board) {
        m = board.size();
        n = board.get(0).length();
        grid = new char[m][];
        for (int i = 0; i < m; i++) {
            grid[i] = board.get(i).toCharArray();
        }

        grid[0][0] = grid[m - 1][n - 1] = '0';

        f = new int[m][n];
        int inf = 0x3fffffff;
        for (int i = 0; i < m; i++) {
            Arrays.fill(f[i], -inf);
        }
        f[m - 1][n - 1] = 0;


        // f[i][j] = Math.max(f[i+1][j],f[i][j+1],f[i+1][j+1])
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 'X') continue;
                // 获取当前格子价值
                int c = grid[i][j] - '0';
                // 下
                if (i + 1 < m && f[i][j] < c + f[i + 1][j]) {
                    f[i][j] = f[i + 1][j] + c;
                }
                // 右
                if (j + 1 < n && f[i][j] < c + f[i][j + 1]) {
                    f[i][j] = f[i][j + 1] + c;
                }
                // 斜对角
                if (i + 1 < m && j + 1 < n && f[i][j] < c + f[i + 1][j + 1]) {
                    f[i][j] = f[i + 1][j + 1] + c;
                }
            }
        }
        int score = f[0][0];
        if (score < 0) return new int[]{0, 0};

//        System.out.println("score = " + score);
        memo = new int[m][n][score + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        int cnt = dfs(0, 0, score);
        return new int[]{score, cnt};
    }

    public int dfs(int i, int j, int c) {
        if (i == m - 1 && j == n - 1) {
            return c == 0 ? 1 : 0;
        }
        if (c > f[i][j]) return 0;
        if (memo[i][j][c] != -1) {
            return memo[i][j][c];
        }
        int res = 0;
        int v = grid[i][j] - '0';
        if (i + 1 < m && grid[i + 1][j] != 'X') {
            res = (res + dfs(i + 1, j, c - v)) % MOD;
        }
        if (j + 1 < n && grid[i][j + 1] != 'X') {
            res = (res + dfs(i, j + 1, c - v)) % MOD;
        }
        if (i + 1 < m && j + 1 < n && grid[i + 1][j + 1] != 'X') {
            res = (res + dfs(i + 1, j + 1, c - v)) % MOD;
        }
        memo[i][j][c] = res;
        return res;
    }


}