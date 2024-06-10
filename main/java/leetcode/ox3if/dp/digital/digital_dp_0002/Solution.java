package leetcode.ox3if.dp.digital.digital_dp_0002;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 902. 最大为 N 的数字组合
 * <p>
 * 给定一个按非递减顺序排列的数字数组digits。
 * 你可以用任意次数digits[i]来写的数字。例如，如果digits = ['1','3','5']，我们可以写数字，如'13','551', 和'1351315'。
 * 返回 可以生成的小于或等于给定整数 n 的正整数的个数。
 * <p>
 * 示例 1：
 * 输入：digits = ["1","3","5","7"], n = 100
 * 输出：20
 * 解释：
 * 可写出的 20 个数字是：
 * 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
 * <p>
 * 示例 2：
 * 输入：digits = ["1","4","9"], n = 1000000000
 * 输出：29523
 * 解释：
 * 我们可以写 3 个一位数字，9 个两位数字，27 个三位数字，
 * 81 个四位数字，243 个五位数字，729 个六位数字，
 * 2187 个七位数字，6561 个八位数字和 19683 个九位数字。
 * 总共，可以使用D中的数字写出 29523 个整数。
 * <p>
 * 示例 3:
 * 输入：digits = ["7"], n = 8
 * 输出：1
 * <p>
 * 提示：
 * 1 <= digits.length <= 9
 * digits[i].length == 1
 * digits[i]是从'1'到'9' 的数
 * digits中的所有值都 不同
 * digits按非递减顺序排列
 * 1 <= n <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/numbers-at-most-n-given-digit-set
 * @title: numbers-at-most-n-given-digit-set
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "atMostNGivenDigitSet", "in.txt");
    }


    char[] path;
    int[] dp;

    int[] digits;

    public int atMostNGivenDigitSet(String[] digits, int n) {
        path = String.valueOf(n).toCharArray();
        dp = new int[path.length];
        Arrays.fill(dp, -1);
        this.digits = new int[digits.length];
        for (int i = 0; i < digits.length; i++) {
            this.digits[i] = digits[i].charAt(0) - '0';
        }
        return dfs(0, true, false);
    }

    public int dfs(int i, boolean isLimit, boolean isNumber) {
        if (i == path.length) {
            return isNumber ? 1 : 0;
        }
        if (!isLimit && isNumber && dp[i] != -1) {
            return dp[i];
        }

        int res = 0;
        if (!isNumber) {
            res += dfs(i + 1, false, false);
        }

        int hi = isLimit ? path[i] - '0' : 9;
        for (int d : digits) {
            if (d > hi) {
                break;
            }
            res += dfs(i + 1, isLimit && d == hi, true);
        }

        if (!isLimit && isNumber) {
            dp[i] = res;
        }
        return res;
    }


}