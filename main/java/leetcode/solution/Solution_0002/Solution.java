package leetcode.solution.Solution_0002;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/super-palindromes
 * @title: super-palindromes
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "superpalindromesInRange", "in.txt");
    }


    public int superpalindromesInRange(String left, String right) {
        long l = Long.parseLong(left), r = Long.parseLong(right);
        long limit = (long) (Math.sqrt(r * 1.0));
        int i = 1, cnt = 0;
        long v = 0;
        for (; ; ) {
            v = o(i);
            if (check(l, r, v * v)) {
                cnt++;
            }
            v = e(i);
            if (check(l, r, v * v)) {
                cnt++;
            }
            i++;
            if (v > limit) {
                break;
            }
        }
        return cnt;
    }


    public static long e(long v) {
        long a = v / 10;
        while (v != 0) {
            a = a * 10 + v % 10;
            v /= 10;
        }
        return a;
    }

    public static long o(long v) {
        long a = v;
        while (v != 0) {
            a = a * 10 + v % 10;
            v /= 10;
        }
        return a;
    }

    public static boolean check(long l, long r, long v) {
        return v >= l && v <= r && isPalindrome(v);
    }

    public static boolean isPalindrome(long a) {
        if (a < 0) {
            return false;
        }
        // System.out.println("a" + a);
        long f = 1L;
        while (a / f >= 10) {
            f *= 10;
        }
        while (a != 0) {
            if (a / f != a % 10) {
                return false;
            }
            a = a % f / 10;
            f /= 100;
        }
        return true;
    }

}