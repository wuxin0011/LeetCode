package leetcode.contest.weekly.w_400.w_414.b;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-414/problems/maximize-score-of-numbers-in-ranges
 * @title: 范围内整数的最大得分
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "maxPossibleScore", "B.txt");
    }


    public int maxPossibleScore(int[] start, int d) {
        Arrays.sort(start);
        long l = 0, r = (long) 1E10 + 1;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            if (check(start, mid, d)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return (int) r;
    }

    public static boolean check(int[] a, long limit, int d) {
        long v = a[0];
        for (int i = 1; i < a.length; i++) {
            v = Math.max(a[i], v + limit);
            if (v > a[i] + d) {
                return false;
            }
        }
        return true;
    }


}