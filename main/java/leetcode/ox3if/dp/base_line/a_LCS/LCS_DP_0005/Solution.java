package leetcode.ox3if.dp.base_line.a_LCS.LCS_DP_0005;

import code_generation.utils.IoUtil;

/**
 * 1035. 不相交的线在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
 * 现在，可以绘制一些连接两个数字 nums1[i]和 nums2[j]的直线，这些直线需要同时满足：
 * nums1[i] == nums2[j]
 * 且绘制的直线不与任何其他连线（非水平线）相交。
 * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
 * 以这种方法绘制线条，并返回可以绘制的最大连线数。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,4,2], nums2 = [1,2,4]
 * 输出：2
 * 解释：可以画出两条不交叉的线，如上图所示。
 * 但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交。
 * <p>
 * 示例 2：
 * 输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
 * 输出：3
 * <p>
 * 示例 3：
 * 输入：nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
 * 输出：2
 * <p>
 * 提示：
 * 1 <= nums1.length, nums2.length <= 500
 * 1 <= nums1[i], nums2[j] <= 2000
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/uncrossed-lines
 * @title: 不相交的线
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxUncrossedLines", "in.txt");
    }


    // 最长公共子序列
    public int maxUncrossedLines(int[] a, int[] b) {
        int m = a.length, n = b.length;
        int[][] f = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i] == b[j]) {
                    f[i][j] = dfs(i - 1, j - 1, f) + 1;
                } else {
                    f[i][j] = Math.max(dfs(i - 1, j, f), dfs(i, j - 1, f));
                }
            }
        }
        return f[m - 1][n - 1];
    }

    public static int dfs(int i, int j, int[][] f) {
        if (i < 0 || j < 0) {
            return 0;
        }
        return f[i][j];
    }


}