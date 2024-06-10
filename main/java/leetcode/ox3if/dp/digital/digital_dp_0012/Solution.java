package leetcode.ox3if.dp.digital.digital_dp_0012;

import code_generation.annotation.TestCaseGroup;
import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 2801. 统计范围内的步进数字数目
 * <p>
 * 给你两个正整数low 和high，都用字符串表示，请你统计闭区间 [low, high]内的 步进数字数目。
 * 如果一个整数相邻数位之间差的绝对值都 恰好是 1，那么这个数字被称为 步进数字。
 * 请你返回一个整数，表示闭区间[low, high]之间步进数字的数目。
 * 由于答案可能很大，请你将它对10^9 + 7取余后返回。
 * 注意：步进数字不能有前导 0 。
 * <p>
 * 示例 1：
 * 输入：low = "1", high = "11"
 * 输出：10
 * 解释：区间 [1,11] 内的步进数字为 1 ，2 ，3 ，4 ，5 ，6 ，7 ，8 ，9 和 10 。总共有 10 个步进数字。所以输出为 10 。
 * <p>
 * 示例 2：
 * 输入：low = "90", high = "10^1"
 * 输出：2
 * 解释：区间 [90,10^1] 内的步进数字为 98 和 10^1 。总共有 2 个步进数字。所以输出为 2 。
 * <p>
 * 提示：
 * 1 <= int(low) <= int(high) < 10^100
 * 1 <= low.length, high.length <= 100
 * low 和high只包含数字。
 * low 和high都不含前导 0 。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/count-stepping-numbers-in-range
 * @title: 统计范围内的步进数字数目
 */

public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "countSteppingNumbers", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    @TestCaseGroup(start = 3, end = 3, use = false)
    public int countSteppingNumbers(String low, String high) {
        System.out.println("low = " + low + ",high = " + high);
        int a = check(low) ? 1 : 0;
        // 取模运算需要特别注意
        // 本题结果特别大以至于 long 都越界了
        // 需要 + MOD
        return (int) ((calc(high) - calc(low) + a + MOD ) % MOD);
    }

    public static boolean check(String s) {
        int n = s.length();
        for (int i = 1; i < n; i++) {
            if (Math.abs(s.charAt(i) - s.charAt(i - 1)) != 1) {
                return false;
            }
        }
        return true;
    }

    long[][] memo;
    char[] path;

    public long calc(String s) {
        path = s.toCharArray();
        memo = new long[path.length][10];
        for (int i = 0; i < path.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(0, -1, true);
    }

    /**
     * 记忆化搜索 用 pre = - 1 代替 不是数字的变量 也就是 isNum
     *
     * @param i       suoyin
     * @param pre     pre
     * @param isLimit 是否越界
     * @return long
     */

    public long dfs(int i, int pre, boolean isLimit) {
        if (i == path.length) {
            return 1;
        }
        if (pre != -1 && !isLimit && memo[i][pre] != -1) {
            return memo[i][pre];
        }
        long res = 0;
        if (pre == -1) {
            res += dfs(i + 1, pre, false);
        }
        int hi = isLimit ? path[i] - '0' : 9;
        int lo = pre == -1 ? 1 : 0;
        for (int d = lo; d <= hi; d++) {
            if (pre == -1 || Math.abs(pre - d) == 1) {
                res = (res + dfs(i + 1, d, isLimit && d == hi)) % MOD;
            }
        }

        if (pre != -1 && !isLimit) {
            memo[i][pre] = res;
        }
        return res;
    }


}