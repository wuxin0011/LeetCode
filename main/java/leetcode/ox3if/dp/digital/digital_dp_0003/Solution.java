package leetcode.ox3if.dp.digital.digital_dp_0003;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 233. 数字 1 的个数
 * <p>
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * <p>
 * 示例 1：
 * 输入：n = 13
 * 输出：6
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 * <p>
 * 提示：
 * 0 <= n <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-digit-one
 * @title: number-of-digit-one
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "countDigitOne", "in.txt");
    }


    public int countDigitOne(int n) {
        return countDigitTemplate(n, 1);
    }

    public int countDigitTemplate(int n, int target) {
        path = String.valueOf(n).toCharArray();
        dp = new int[path.length][1 << path.length];
        for (int i = 0; i < path.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        int dfs = dfs(0, 0, true, target);
        return dfs;
    }

    int[][] dp;
    char[] path;

    public int dfs(int i, int s, boolean isLimit, int target) {
        if (i == path.length) {
            return s;
        }
        if (!isLimit && dp[i][s] != -1) {
            return dp[i][s];
        }

        int ans = 0;
        int hi = isLimit ? path[i] - '0' : 9;
        for (int d = 0; d <= hi; d++) {
            ans += dfs(i + 1, s + (d == target ? 1 : 0), isLimit && d == hi, target);
        }
        if (!isLimit) {
            dp[i][s] = ans;
        }
        return ans;
    }


}