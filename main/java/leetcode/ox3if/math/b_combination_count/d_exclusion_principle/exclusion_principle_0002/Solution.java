package leetcode.ox3if.math.b_combination_count.d_exclusion_principle.exclusion_principle_0002;

import code_generation.utils.IoUtil;

/**
 * 1201. 丑数 III
 * <p>
 * 丑数是可以被a或b或 c整除的 正整数 。
 * 给你四个整数：n 、a 、b 、c ，请你设计一个算法来找出第n个丑数。
 * <p>
 * 示例 1：
 * 输入：n = 3, a = 2, b = 3, c = 5
 * 输出：4
 * 解释：丑数序列为 2, 3, 4, 5, 6, 8, 9, 10... 其中第 3 个是 4。
 * <p>
 * 示例 2：
 * 输入：n = 4, a = 2, b = 3, c = 4
 * 输出：6
 * 解释：丑数序列为 2, 3, 4, 6, 8, 9, 10, 12... 其中第 4 个是 6。
 * <p>
 * 示例 3：
 * 输入：n = 5, a = 2, b = 11, c = 13
 * 输出：10
 * 解释：丑数序列为 2, 4, 6, 8, 10, 11, 12, 13... 其中第 5 个是 10。
 * <p>
 * 提示：
 * 1 <= n, a, b, c <= 10^9
 * 1 <= a * b * c <= 10^18
 * 本题结果在[1,2 * 10^9]的范围内
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/ugly-number-iii
 * @title: ugly-number-iii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "nthUglyNumber", "in.txt");
    }


    public int nthUglyNumber(int n, int a, int b, int c) {
        // a + b + c - ab - ac - bc + abc
        long l = 0, r = (long) 1e18;
        long lcmab = lcm(a, b);
        long lcmac = lcm(a, c);
        long lcmbc = lcm(b, c);
        long lcmabc = lcm(a, lcmbc);
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            long cnt = mid / a + mid / b + mid / c - mid / lcmab - mid / lcmac - mid / lcmbc + mid / lcmabc;
            if (cnt >= n) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;
    }


    public static long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }



    // 升级版本 https://leetcode.cn/contest/weekly-contest-393/problems/kth-smallest-amount-with-single-denomination-combination/
}