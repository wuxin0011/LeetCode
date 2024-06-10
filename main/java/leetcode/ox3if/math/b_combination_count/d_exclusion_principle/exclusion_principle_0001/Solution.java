package leetcode.ox3if.math.b_combination_count.d_exclusion_principle.exclusion_principle_0001;

import code_generation.utils.IoUtil;

/**
 * 878. 第 N 个神奇数字
 * <p>
 * 一个正整数如果能被 a 或 b 整除，那么它是神奇的。
 * 给定三个整数 n ,a , b ，返回第 n 个神奇的数字。因为答案可能很大，所以返回答案对10^9+ 7 取模后的值。
 * <p>
 * 示例 1：
 * 输入：n = 1, a = 2, b = 3
 * 输出：2
 * <p>
 * 示例2：
 * 输入：n = 4, a = 2, b = 3
 * 输出：6
 * <p>
 * 提示：
 * 1 <= n <= 10^9
 * 2 <= a, b <= 4 * 10^4
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/nth-magical-number
 * @title: nth-magical-number
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "nthMagicalNumber", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    //  开区间写法
    public int nthMagicalNumber(int n, int a, int b) {
        int lcmValue = lcm(a, b);
        long l = 0, r = (long) Math.min(a, b) * n;
        while (l + 1 < r) {
            long mid = l + ((r - l) >> 1);
            // a + b - ab
            if (mid / a + mid / b - mid / lcmValue >= n) {
                r = mid;
            } else {
                l = mid;
            }
        }

        return (int) (r % MOD);
    }


    // 闭区间写法
    public int nthMagicalNumber1(int n, int a, int b) {
        int lcmValue = lcm(a, b);
        long l = 0, r = (long) Math.min(a, b) * n;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            // a + b - ab
            if (mid / a + mid / b - mid / lcmValue >= n) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return (int) (l % MOD);
    }


    public static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
        // return a / gcd(a, b) * b;
    }


    /**
     * 超时做法 思路是OK的
     *
     * @param n
     * @param a
     * @param b
     * @return
     */
    public int nthMagicalNumberTimeoutError(int n, int a, int b) {
        if (a == b) {
            return (int) (a * 1L * n % MOD);
        }
        int ai = 1, bi = 1;
        long st = 1;
        for (int i = 1; i <= n; i++) {
            st = Math.min(ai * 1L * a % MOD, bi * 1L * b % MOD);
            if (st == ai * 1L * a % MOD) {
                ai++;
            }
            if (st == bi * 1L * b % MOD) {
                bi++;
            }
        }
        return (int) st;
    }


}