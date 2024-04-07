package leetcode.ox3if.slide_window.slide_0005;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/k-radius-subarray-averages
 * @title: k-radius-subarray-averages
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "getAverages", "in.txt");
    }


    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n];
        long sum = 0L;
        for (int i = 0; i < n; i++) {
            res[i] = -1;
            sum += nums[i];
            if (i >= 2 * k) {
                res[i - k] = (int) (sum / (2 * k + 1));
                sum -= nums[i - 2 * k];
            }
        }
        return res;
    }


}