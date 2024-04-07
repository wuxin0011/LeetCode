package leetcode.ox3if.slide_window.slide_0002;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 *
 * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
 * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
 * 返回可能的 最小差值 。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-difference-between-highest-and-lowest-of-k-scores
 * @title: minimum-difference-between-highest-and-lowest-of-k-scores
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minimumDifference", "in.txt");
    }


    public int minimumDifference(int[] nums, int k) {
        if (k <= 1 || nums == null || nums.length <= 1) {
            return 0;
        }
        Arrays.sort(nums);
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (i >= k - 1) {
                ans = Math.min(ans, nums[i] - nums[i-k+1]);
            }
        }
        return ans;
    }


}