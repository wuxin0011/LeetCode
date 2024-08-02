package leetcode._0x3f_.dp.digital.digital_dp_0007;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 10^12. 至少有 1 位重复的数字给定正整数n，返回在[1, n]范围内具有 至少 1 位 重复数字的正整数的个数。
 * <p>
 * 示例 1：
 * 输入：n = 20
 * 输出：1
 * 解释：具有至少 1 位重复数字的正数（<= 20）只有 11 。
 * <p>
 * 示例 2：
 * 输入：n = 100
 * 输出：10
 * 解释：具有至少 1 位重复数字的正数（<= 100）有 11，22，33，44，55，66，77，88，99 和 100 。
 * <p>
 * 示例 3：
 * 输入：n = 1000
 * 输出：262
 * <p>
 * 提示：
 * 1 <= n <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/numbers-with-repeated-digits
 * @title: numbers-with-repeated-digits
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "numDupDigitsAtMostN", "in.txt");
    }


    public int numDupDigitsAtMostN(int n) {

        return n - countSpecialNumbers(n);
    }

    // 本题同上面一题 修改一下模板就好了 https://leetcode.cn/problems/count-special-integers
    // 至少有一位重复的 = 全部 - 没有重复的
    char[] path;
    int[][] dp;


    /**
     * @see leetcode._0x3f_.dp.digital.digital_dp_0006.Solution
     * @param n
     * @return
     */
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
        if (!isLimit && isNumber) {
            dp[i][mask] = res;
        }
        return res;
    }


}