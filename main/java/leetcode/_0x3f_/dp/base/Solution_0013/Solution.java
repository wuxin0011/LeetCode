package leetcode._0x3f_.dp.base.Solution_0013;

import code_generation.utils.IoUtil;
/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-sum-circular-subarray
 * @title: 环形子数组的最大和
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxSubarraySumCircular", "in.txt");
    }


    public int maxSubarraySumCircular(int[] nums) {
        boolean isNeg = true;
        int tot = 0;
        int mx = Integer.MIN_VALUE;
        for (int num : nums) {
            if (mx < num) mx = num;
            if (num >= 0) {
                isNeg = false;
            }
            tot += num;
        }
        if (isNeg) {
            return mx;
        }


        return Math.max(findMax(nums), tot - findMin(nums));
    }

    public static int findMax(int[] arr) {
        int mx = 0;
        int tot = 0;
        for (int num : arr) {
            tot += num;
            mx = Math.max(tot, mx);
            if (tot < 0) {
                tot = 0;
            }
        }
        return mx;
    }

    public static int findMin(int[] arr) {
        int mi = 0;
        int tot = 0;
        for (int num : arr) {
            tot += num;
            mi = Math.min(tot, mi);
            if (tot > 0) {
                tot = 0;
            }
        }
        return mi;
    }

}