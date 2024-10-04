package leetcode._0x3f_.dp.knapsack.d_goup_knaspack.Solution_0001;

import code_generation.utils.IoUtil;

/**
 * 1981. 最小化目标值与所选元素的差
 * <p>
 * 给你一个大小为 m x n 的整数矩阵 mat 和一个整数 target 。
 * 从矩阵的 每一行 中选择一个整数，你的目标是最小化所有选中元素之和与目标值 target 的 绝对差 。
 * 返回 最小的绝对差 。
 * a 和 b 两数字的 绝对差 是 a - b 的绝对值。
 * <p>
 * 示例 1：
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], target = 13
 * 输出：0
 * 解释：一种可能的最优选择方案是：
 * - 第一行选出 1
 * - 第二行选出 5
 * - 第三行选出 7
 * 所选元素的和是 13 ，等于目标值，所以绝对差是 0 。
 * <p>
 * 示例 2：
 * 输入：mat = [[1],[2],[3]], target = 100
 * 输出：94
 * 解释：唯一一种选择方案是：
 * - 第一行选出 1
 * - 第二行选出 2
 * - 第三行选出 3
 * 所选元素的和是 6 ，绝对差是 94 。
 * <p>
 * 示例 3：
 * 输入：mat = [[1,2,9,8,7]], target = 6
 * 输出：1
 * 解释：最优的选择方案是选出第一行的 7 。
 * 绝对差是 1 。
 * <p>
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 70
 * 1 <= mat[i][j] <= 70
 * 1 <= target <= 800
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimize-the-difference-between-target-and-chosen-elements
 * @title: 最小化目标值与所选元素的差
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minimizeTheDifference", "in.txt");
    }

    int inf = 1 << 30;

    public int minimizeTheDifference(int[][] mat, int target) {
        m = mat.length;
        n = mat[0].length;
        this.target = target;
        memo = new Integer[m + 1][100000];
        this.mat = mat;
        return dfs(0, 0);
    }

    int m, n, target, mat[][];
    Integer memo[][];

    int res = inf;

    public int dfs(int i, int s) {
        if (i >= m) {
            res = Math.min(res, Math.abs(s - target));
            return res;
        }
        if (memo[i][s] != null) return memo[i][s];
        int ans = inf;
        for (int j = 0; j < n; j++) {
            // 减枝
            if (s + mat[i][j] > target && Math.abs(s + mat[i][j] - target) > res) {
                continue;
            }
            ans = Math.min(ans, dfs(i + 1, s + mat[i][j]));
        }
        return memo[i][s] = ans;
    }


}