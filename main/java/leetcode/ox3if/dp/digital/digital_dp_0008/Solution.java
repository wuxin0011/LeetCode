package leetcode.ox3if.dp.digital.digital_dp_0008;

import code_generation.utils.IoUtil;

/**
 * 357. 统计各位数字都不同的数字个数
 * <p>
 * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n。
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：91
 * 解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：1
 * <p>
 * 提示：
 * 0 <= n <= 8
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/count-numbers-with-unique-digits
 * @title: 统计各位数字都不同的数字个数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "countNumbersWithUniqueDigits", "in.txt");
    }

    static int[] dp = new int[11];

    static {
        // f(0) = 1
        // f(1) = 10
        // f(2) = f(1) + (10 - 2 + 1) * (f(0) - f(1));
        // f(3) = f(2) + (10 - 3 + 1) * (f(2) - f(1))
        // ...
        // f(i) = f(i - 1) + (n - i + 1) * (f(i - 1) - f(i - 2))
        int n = 10;
        dp[0] = 1;
        dp[1] = 10;
        for (int i = 2; i <= 9; i++) {
            dp[i] = dp[i - 1] + (n - i + 1) * (dp[i - 1] - dp[i - 2]);
        }
    }

    public int countNumbersWithUniqueDigits(int n) {
        return dp[n];
    }


}