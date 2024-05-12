package leetcode.contest.biweekly.bi_100.bi_130.a;

import code_generation.utils.IoUtil;

/**
 *
 *
 * 100299. 判断矩阵是否满足条件
 *
 * 给你一个大小为 m x n的二维矩阵grid。
 * 你需要判断每一个格子grid[i][j]是否满足：
 * 	如果它下面的格子存在，那么它需要等于它下面的格子，也就是grid[i][j] == grid[i + 1][j]。
 * 	如果它右边的格子存在，那么它需要不等于它右边的格子，也就是grid[i][j] != grid[i][j + 1]。
 * 如果 所有格子都满足以上条件，那么返回 true，否则返回 false。
 *
 * 示例 1：
 * 输入：grid = [[1,0,2],[1,0,2]]
 * 输出：true
 * 解释：
 * 网格图中所有格子都符合条件。
 *
 * 示例 2：
 * 输入：grid = [[1,1,1],[0,0,0]]
 * 输出：false
 * 解释：
 * 同一行中的格子值都相等。
 *
 * 示例 3：
 * 输入：grid = [[1],[2],[3]]
 * 输出：false
 * 解释：
 * 同一列中的格子值不相等。
 *
 * 提示：
 * 	1 <= n, m <= 10
 * 	0 <= grid[i][j] <= 9
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-130/problems/check-if-grid-satisfies-conditions
 * @title: 判断矩阵是否满足条件
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "satisfiesConditions", "A.txt");
    }


    public boolean satisfiesConditions(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int col = 0; col < n; col++) {
            for (int row = 0; row < m; row++) {
                if (row > 0 && grid[row][col] != grid[row - 1][col]) {
                    return false;
                }
                if (col > 0 && grid[row][col] == grid[row][col - 1]) {
                    return false;
                }

            }
        }
        return true;
    }


}