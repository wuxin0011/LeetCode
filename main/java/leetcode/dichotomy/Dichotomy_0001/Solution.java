package leetcode.dichotomy.Dichotomy_0001;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/split-array-largest-sum
 * @title: split-array-largest-sum
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "splitArray", "in.txt");
    }


    public int splitArray(int[] nums, int k) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long r = sum, l = 0;
        long ans = 0;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            if (check(nums, mid, k)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) ans;
    }


    public static boolean check(int[] nums, long limit, int k) {
        int cnt = 1;
        long sum = 0;
        for (int num : nums) {
            if (num > limit) { // 不可能
                return false;
            }
            if (sum + num > limit) {
                sum = num;
                cnt++;
            } else {
                sum += num;
            }
        }

        return cnt <= k;
    }

}