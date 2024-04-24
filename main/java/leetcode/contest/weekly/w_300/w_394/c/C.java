package leetcode.contest.weekly.w_300.w_394.c;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 *
 *
 * 3122. 使矩阵满足条件的最少操作次数
 *
 * 给你一个大小为 m x n的二维矩形grid。
 * 每次 操作中，你可以将 任一 格子的值修改为 任意非负整数。完成所有操作后，你需要确保每个格子grid[i][j]的值满足：
 * 	如果下面相邻格子存在的话，它们的值相等，也就是grid[i][j] == grid[i + 1][j]（如果存在）。
 * 	如果右边相邻格子存在的话，它们的值不相等，也就是grid[i][j] != grid[i][j + 1]（如果存在）。
 * 请你返回需要的 最少操作数目。
 *
 * 示例 1：
 * 输入：grid = [[1,0,2],[1,0,2]]
 * 输出：0
 * 解释：
 * 矩阵中所有格子已经满足要求。
 *
 * 示例 2：
 * 输入：grid = [[1,1,1],[0,0,0]]
 * 输出：3
 * 解释：
 * 将矩阵变成[[1,0,1],[1,0,1]]，它满足所有要求，需要 3 次操作：
 * 	将grid[1][0]变为 1 。
 * 	将grid[0][1] 变为 0 。
 * 	将grid[1][2]变为 1 。
 *
 * 示例 3：
 * 输入：grid = [[1],[2],[3]]
 * 输出：2
 * 解释：
 * 这个矩阵只有一列，我们可以通过 2 次操作将所有格子里的值变为 1 。
 *
 * 提示：
 * 	1 <= n, m <= 1000
 * 	0 <= grid[i][j] <= 9
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-394/problems/minimum-number-of-operations-to-satisfy-conditions
 * @title: 使矩阵满足条件的最少操作次数
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minimumOperations", "C.txt");
    }


    public int minimumOperations(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] h = new int[col][10];
        for (int i = 0; i < col; i++) {
            for (int[] ints : grid) {
                h[i][ints[i]]++;
            }
        }
        int[][] memo = new int[col][11];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return row * col - dfs(0, 10, h, memo);
    }


    public int dfs(int i, int pre, int[][] cnts, int[][] memo) {
        if (i >= cnts.length) {
            return 0;
        }
        if (memo[i][pre] != -1) {
            return memo[i][pre];
        }
        int res = 0;

        for (int k = 0; k < 10; k++) {
            if (k != pre) {
                // 能够保留最多的
                res = Math.max(res, dfs(i + 1, k, cnts, memo) + cnts[i][k]);
            }
        }

        memo[i][pre] = res;
        return res;
    }


}