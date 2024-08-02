package leetcode._0x3f_.dp.digital.digital_dp_0004;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-2s-in-range-lcci
 * @title: 出现的次数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "numberOf2sInRange", "in.txt");
    }


    public int numberOf2sInRange(int n) {

        return countDigitTemplate(n, 2);
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