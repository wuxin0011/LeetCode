package leetcode.ox3if.dp.digital.digital_dp_0005;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 600. 不含连续1的非负整数
 * <p>
 * 给定一个正整数 n ，请你统计在[0, n] 范围的非负整数中，有多少个整数的二进制表示中不存在 连续的 1 。
 * <p>
 * 示例 1:
 * 输入: n = 5
 * 输出: 5
 * 解释:
 * 下面列出范围在 [0, 5] 的非负整数与其对应的二进制表示：
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 10^1
 * 其中，只有整数 3 违反规则（有两个连续的 1 ），其他 5 个满足规则。
 * <p>
 * 示例 2:
 * 输入: n = 1
 * 输出: 2
 * <p>
 * 示例 3:
 * 输入: n = 2
 * 输出: 3
 * <p>
 * 提示:
 * 1 <= n <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/non-negative-integers-without-consecutive-ones
 * @title: 不含连的非负整数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "findIntegers", "in.txt");
        // System.out.println(Integer.toBinaryString(5));
    }

    char[] path;

    int[][] dp;

    public int findIntegers(int n) {
        this.path = Integer.toBinaryString(n).toCharArray();
        this.dp = new int[path.length][2];
        for (int i = 0; i < path.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(0, false, true);
    }

    public int dfs(int i, boolean pre, boolean isLimit) {
        if (i == path.length) {
            return 1;
        }
        int pid = pre ? 0 : 1;
        if (!isLimit && dp[i][pid] != -1) {
            return dp[i][pid];
        }
        int ans = 0;
        int hi = isLimit ? path[i] - '0' : 1;
        for (int d = 0; d <= hi; d++) {
            if (d == 1 && pre) {
                break;
            }
            ans += dfs(i + 1, d == 1, isLimit && d == hi);
        }
        if (!isLimit) dp[i][pid] = ans;
        return ans;
    }


}