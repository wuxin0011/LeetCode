package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 2639. 查询网格图中每一列的宽度
 *
 * 给你一个下标从 0开始的m x n整数矩阵grid。
 * 矩阵中某一列的宽度是这一列数字的最大 字符串长度。
 * 	比方说，如果grid = [[-10], [3], [12]]，那么唯一一列的宽度是3，因为-10的字符串长度为3。
 * 请你返回一个大小为 n的整数数组ans，其中ans[i]是第i列的宽度。
 * 一个有 len个数位的整数 x，如果是非负数，那么字符串长度为len，否则为len + 1。
 *
 * 示例 1：
 * 输入：grid = [[1],[22],[333]]
 * 输出：[3]
 * 解释：第 0 列中，333 字符串长度为 3 。
 *
 * 示例 2：
 * 输入：grid = [[-15,1,3],[15,7,12],[5,6,-2]]
 * 输出：[3,1,2]
 * 解释：
 * 第 0 列中，只有 -15 字符串长度为 3 。
 * 第 1 列中，所有整数的字符串长度都是 1 。
 * 第 2 列中，12 和 -2 的字符串长度都为 2 。
 *
 * 提示：
 * 	m == grid.length
 * 	n == grid[i].length
 * 	1 <= m, n <= 100
 * 	-10^9 <= grid[r][c] <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/find-the-width-of-columns-of-a-grid
 * @title: 查询网格图中每一列的宽度
 */
public class Code_0056_2639 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0056_2639.class,"findColumnWidth","txt_file\\Code_0056_2639.txt");
    }
     

    public int[] findColumnWidth(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int mx = 0;
            for (int j = 0; j < m; j++) {
                mx = Math.max(mx,String.valueOf(grid[j][i]).length());
            }
            ans[i] = mx;
        }
        return ans;
    }

  

}