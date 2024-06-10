package leetcode.ox3if.dp.digital.digital_dp_0006;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 2376. 统计特殊整数
 * <p>
 * 如果一个正整数每一个数位都是 互不相同的，我们称它是 特殊整数 。
 * 给你一个 正整数n，请你返回区间[1, n]之间特殊整数的数目。
 * <p>
 * 示例 1：
 * 输入：n = 20
 * 输出：19
 * 解释：1 到 20 之间所有整数除了 11 以外都是特殊整数。所以总共有 19 个特殊整数。
 * <p>
 * 示例 2：
 * 输入：n = 5
 * 输出：5
 * 解释：1 到 5 所有整数都是特殊整数。
 * <p>
 * 示例 3：
 * 输入：n = 135
 * 输出：110
 * 解释：从 1 到 135 总共有 110 个整数是特殊整数。
 * 不特殊的部分数字为：22 ，114 和 131 。
 * <p>
 * 提示：
 * 1 <= n <= 2 * 10^9
 *
 * @author: wuxin0011
 * @Description: 数位dp
 * @url: https://leetcode.cn/problems/count-special-integers
 * @title: 统计特殊整数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "countSpecialNumbers", "in.txt");
    }

    char[] path;
    int[][] dp;


    public int countSpecialNumbers(int n) {
        path = String.valueOf(n).toCharArray();
        dp = new int[path.length][1 << 10];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(0, 0, true, false);
    }

    public int dfs(int i, int mask, boolean isLimit, boolean isNumber) {
        if (i == path.length) {
            return isNumber ? 1 : 0;
        }
        if (!isLimit && isNumber && dp[i][mask] != -1) {
            return dp[i][mask];
        }
        int res = 0;
        if (!isNumber) {
            res += dfs(i + 1, mask, false, false);
        }
        int hi = isLimit ? path[i] - '0' : 9;
        for (int d = isNumber ? 0 : 1; d <= hi; d++) {
            if ((mask >> d & 1) == 0) {
                res += dfs(i + 1, mask | 1 << d, isLimit && d == hi, true);
            }
        }
        if(!isLimit && isNumber) {
            dp[i][mask] = res;
        }
        return res;
    }


}