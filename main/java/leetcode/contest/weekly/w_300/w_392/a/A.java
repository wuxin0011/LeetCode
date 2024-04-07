package leetcode.contest.weekly.w_300.w_392.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-392/problems/longest-strictly-increasing-or-strictly-decreasing-subarray
 * @title: 最长的严格递增或递减子数组
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "longestMonotonicSubarray", "A.txt");
    }


    public int longestMonotonicSubarray(int[] nums) {

        if(nums == null || nums.length == 0){
            return 0;
        }

        if(nums.length == 1){
            return 1;
        }

        int l = 0, r = 0;
        int ans = 0;
        // 严格递增
        while (r < nums.length) {
            while (r<nums.length && r > 0 && nums[r] > nums[r - 1]) {
                r++;
            }
            ans = Math.max(ans, r - l);
            l = r;
            r++;
        }
        r = l = 0;

        // 严格递减
        while (r < nums.length) {
            while (r<nums.length && r > 0 && nums[r] < nums[r - 1]) {
                r++;
            }
            ans = Math.max(ans, r - l);
            l = r;
            r++;
        }

        return ans;
    }


}