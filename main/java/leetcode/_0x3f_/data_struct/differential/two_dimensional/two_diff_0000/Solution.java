package leetcode._0x3f_.data_struct.differential.two_dimensional.two_diff_0000;

import code_generation.utils.IoUtil;

/**
 * 2536. 子矩阵元素加 1
 * <p>
 * 给你一个正整数 n ，表示最初有一个 n x n 、下标从 0 开始的整数矩阵 mat ，矩阵中填满了 0 。
 * 另给你一个二维整数数组 query 。针对每个查询 query[i] = [row1i, col1i, row2i, col2i] ，请你执行下述操作：
 * 找出 左上角 为 (row1i, col1i) 且 右下角 为 (row2i, col2i) 的子矩阵，将子矩阵中的 每个元素 加 1 。也就是给所有满足 row1i <= x <= row2i 和 col1i <= y <= col2i 的 mat[x][y] 加 1 。
 * 返回执行完所有操作后得到的矩阵 mat 。
 * <p>
 * 示例 1：
 * 输入：n = 3, queries = [[1,1,2,2],[0,0,1,1]]
 * 输出：[[1,1,0],[1,2,1],[0,1,1]]
 * 解释：上图所展示的分别是：初始矩阵、执行完第一个操作后的矩阵、执行完第二个操作后的矩阵。
 * - 第一个操作：将左上角为 (1, 1) 且右下角为 (2, 2) 的子矩阵中的每个元素加 1 。
 * - 第二个操作：将左上角为 (0, 0) 且右下角为 (1, 1) 的子矩阵中的每个元素加 1 。
 * <p>
 * 示例 2：
 * 输入：n = 2, queries = [[0,0,1,1]]
 * 输出：[[1,1],[1,1]]
 * 解释：上图所展示的分别是：初始矩阵、执行完第一个操作后的矩阵。
 * - 第一个操作：将矩阵中的每个元素加 1 。
 * <p>
 * 提示：
 * 1 <= n <= 500
 * 1 <= queries.length <= 10^4
 * 0 <= row1i <= row2i < n
 * 0 <= col1i <= col2i < n
 *
 * @author: wuxin0011
 * @Description: 二维差分模板题
 * @url: https://leetcode.cn/problems/increment-submatrices-by-one
 * @title: increment-submatrices-by-one
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "rangeAddQueries", "in.txt");
    }


    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] diffs = new int[n + 2][n + 2];
        for (int[] q : queries) {
            int x0 = q[0], y0 = q[1], x1 = q[2], y1 = q[3];
            add(diffs, x0, y0, x1, y1, 1);
        }
        int[][] mat = new int[n][n];
        origin(diffs, mat);
        return mat;
    }

    static void add(int[][] diff, int x0, int y0, int x1, int y1, int v) {
        x0++;
        y0++;
        x1++;
        y1++;
        diff[x0][y0] += v;
        diff[x1 + 1][y0] -= v;
        diff[x0][y1 + 1] -= v;
        diff[x1 + 1][y1 + 1] += v;
    }

    static void origin(int[][] diff, int[][] mat) {
        for (int i = 1; i <= mat.length; i++) {
            for (int j = 1; j <= mat[0].length; j++) {
                diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                mat[i - 1][j - 1] += diff[i][j];
            }
        }
    }


}