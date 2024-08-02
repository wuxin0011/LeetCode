package leetcode._0x3f_.dp.interval.b_other.interval_dp_0007;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 10^39. 多边形三角剖分的最低得分你有一个凸的n边形，其每个顶点都有一个整数值。
 * 给定一个整数数组values，其中values[i]是第 i 个顶点的值（即 顺时针顺序 ）。
 * 假设将多边形 剖分为 n - 2个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 n - 2个三角形的值之和。
 * 返回 多边形进行三角剖分后可以得到的最低分 。
 * <p>
 * 示例 1：
 * 输入：values = [1,2,3]
 * 输出：6
 * 解释：多边形已经三角化，唯一三角形的分数为 6。
 * <p>
 * 示例 2：
 * 输入：values = [3,7,4,5]
 * 输出：144
 * 解释：有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
 * <p>
 * 示例 3：
 * 输入：values = [1,3,1,4,1,5]
 * 输出：13
 * 解释：最低分数三角剖分的得分情况为 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13。
 * <p>
 * 提示：
 * n == values.length
 * 3 <= n <= 50
 * 1 <= values[i] <= 100
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-score-triangulation-of-polygon
 * @title: 多边形三角剖分的最低得分
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minScoreTriangulation", "in.txt");
    }


    public int minScoreTriangulation(int[] values) {
        this.values = values;
        int n = values.length;
        this.memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(0, n - 1);
    }

    int[][] memo;
    int[] values;

    public int dfs(int l, int r) {
        // 不能构成三角形
        if (l + 1 >= r ) {
            return 0;
        }
        if (memo[l][r] != -1) {
            return memo[l][r];
        }
        int res = Integer.MAX_VALUE;
        // 枚举顶点 l,r 为一条边
        for (int k = l + 1; k < r; k++) {
            // 递归
            res = Math.min(res, dfs(l, k ) + dfs(k, r) + values[l] * values[r] * values[k]);
        }
        memo[l][r] = res;
        return res;
    }


}