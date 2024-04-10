package leetcode.ox3if.dichotomy.Dichotomy_0013;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-candies-allocated-to-k-children
 * @title: maximum-candies-allocated-to-k-children
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maximumCandies", "in.txt");
    }


    public int maximumCandies(int[] candies, long k) {

        long sum = 0;
        int mx = 0;
        int mi = Integer.MAX_VALUE;
        for (int candy : candies) {
            sum += candy;
            if (candy > mx) {
                mx = candy;
            }
            if (mi > candy) {
                mi = candy;
            }
        }
        if (sum < k) {
            return 0;
        }
        long r = mx, l = 1;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            if (check(candies, mid, k)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return (int) r;
    }


    public static boolean check(int[] arr, long v, long k) {
        long tot = 0;
        for (int a : arr) {
            tot += a / v;
            if (tot >= k) {
                return true;
            }
        }
        return false;
    }


}