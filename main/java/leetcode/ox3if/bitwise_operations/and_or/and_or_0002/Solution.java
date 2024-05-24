package leetcode.ox3if.bitwise_operations.and_or.and_or_0002;

import code_generation.utils.IoUtil;

/**
 * 2419. 按位与最大的最长子数组
 * <p>
 * 给你一个长度为 n 的整数数组 nums 。
 * 考虑 nums 中进行 按位与（bitwise AND）运算得到的值 最大 的 非空 子数组。
 * 换句话说，令 k 是 nums 任意 子数组执行按位与运算所能得到的最大值。那么，只需要考虑那些执行一次按位与运算后等于 k 的子数组。
 * 返回满足要求的 最长 子数组的长度。
 * 数组的按位与就是对数组中的所有数字进行按位与运算。
 * 子数组 是数组中的一个连续元素序列。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,3,2,2]
 * 输出：2
 * 解释：
 * 子数组按位与运算的最大值是 3 。
 * 能得到此结果的最长子数组是 [3,3]，所以返回 2 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：1
 * 解释：
 * 子数组按位与运算的最大值是 4 。
 * 能得到此结果的最长子数组是 [4]，所以返回 1 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/longest-subarray-with-maximum-bitwise-and
 * @title: 按位与最大的最长子数组
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "longestSubarray", "in.txt");
    }


    public int longestSubarray(int[] nums) {
        int mx = nums[0];
        int ans = 1, l = 1;
        for (int i = 1; i < nums.length; i++) {
            if (mx < nums[i]) {
                mx = nums[i];
                ans = 1;
                l = 1;
            } else if (nums[i] == mx) {
                l++;
                ans = Math.max(ans, l);
            } else {
                l = 0;
            }
        }

        return ans;
    }

    public int longestSubarray1(int[] nums) {
        int mx = nums[0];
        for (int num : nums) {
            mx = Math.max(mx, num);
        }
        int ans = 1, l = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == mx && nums[i] == mx) {
                l++;
            } else {
                ans = Math.max(ans, l);
                l = 1;
            }
        }

        return Math.max(ans, l);
    }

}