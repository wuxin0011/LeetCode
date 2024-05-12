package leetcode.contest.weekly.w_300.w_397.c;

import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 100281. 矩阵中的最大得分
 * <p>
 * <p>
 * 给你一个由 正整数 组成、大小为 m x n 的矩阵 grid。
 * 你可以从矩阵中的任一单元格移动到另一个位于正下方或正右侧的任意单元格（不必相邻）。从值为 c1 的单元格移动到值为 c2 的单元格的得分为 c2 - c1 。
 * 你可以从 任一 单元格开始，并且必须至少移动一次。
 * 返回你能得到的 最大 总得分。
 * <p>
 * 示例 1：
 * 输入：grid = [[9,5,7,3],[8,9,6,1],[6,7,14,3],[2,5,3,1]]
 * 输出：9
 * 解释：从单元格 (0, 1) 开始，并执行以下移动：
 * - 从单元格 (0, 1) 移动到 (2, 1)，得分为 7 - 5 = 2 。
 * - 从单元格 (2, 1) 移动到 (2, 2)，得分为 14 - 7 = 7 。
 * 总得分为 2 + 7 = 9 。
 * <p>
 * 示例 2：
 * 输入：grid = [[4,3,2],[3,2,1]]
 * 输出：-1
 * 解释：从单元格 (0, 0) 开始，执行一次移动：从 (0, 0) 到 (0, 1) 。得分为 3 - 4 = -1 。
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 10^5
 * 1 <= grid[i][j] <= 10^5
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-397/problems/maximum-difference-score-in-a-grid
 * @title: 矩阵中的最大得分
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "maxScore", "C.txt");
    }


    public int maxScore(List<List<Integer>> ls) {
        int m = ls.size();
        int n = ls.get(0).size();
        int[][] grid = new int[m][n];
        int[][][] score = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = ls.get(i).get(j);
            }
        }
        int mx = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j + 1 < n) {
                    score[i][j][0] = grid[i][j + 1] - grid[i][j]; // 右边得分
                } else {
                    score[i][j][0] = Integer.MIN_VALUE; // 右边得分
                }

                if (i + 1 < m) {
                    score[i][j][1] = grid[i + 1][j] - grid[i][j]; // 下边得分
                } else {
                    score[i][j][1] = Integer.MIN_VALUE; // 下边得分
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(Arrays.toString(score[i][j]) + " ");
            }
            System.out.println();
        }

        System.out.println("======end=======");

        return 0;
    }


}