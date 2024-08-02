package leetcode._0x3f_.bitwise_operations.and_or.and_or_0004;

import code_generation.utils.IoUtil;

/**
 * 2401. 最长优雅子数组
 * <p>
 * 给你一个由 正 整数组成的数组 nums 。
 * 如果nums 的子数组中位于 不同 位置的每对元素按位 与（AND）运算的结果等于 0 ，则称该子数组为 优雅 子数组。
 * 返回 最长 的优雅子数组的长度。
 * 子数组 是数组中的一个 连续 部分。
 * 注意：长度为 1 的子数组始终视作优雅子数组。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,8,48,10]
 * 输出：3
 * 解释：最长的优雅子数组是 [3,8,48] 。子数组满足题目条件：
 * - 3 AND 8 = 0
 * - 3 AND 48 = 0
 * - 8 AND 48 = 0
 * 可以证明不存在更长的优雅子数组，所以返回 3 。
 * <p>
 * 示例 2：
 * 输入：nums = [3,1,5,11,13]
 * 输出：1
 * 解释：最长的优雅子数组长度为 1 ，任何长度为 1 的子数组都满足题目条件。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/longest-nice-subarray
 * @title: 最长优雅子数组
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "longestNiceSubarray", "in.txt");
        IoUtil.testUtil(Solution.class, "longestNiceSubarray1", "in.txt");
    }


    public int longestNiceSubarray(int[] nums) {
        int l = 0;
        int r = 0;
        int v = 0;
        int n = nums.length;
        int ans = 0;
        while (r < n) {
            while ((v & nums[r]) > 0) { // 大于 0 说明有交集 题目要求没有交集 因此可以弹出改元素
                v ^= nums[l++]; // 移出左边元素
            }
            v |= nums[r];
            ans = Math.max(ans, r - l + 1);
            r++;
        }
        return ans;
    }

    public int longestNiceSubarray1(int[] nums) {


        // 如果没有0 说明 各自为 子数组 才是结果最好的
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pre &= nums[i];
            if (pre == 0) {
                break;
            }
        }
        if (pre > 0) {
            return 1;
        }

        // 暴力解法
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            int j = i - 1;
            while (j >= 0 && (val & nums[j]) == 0) {
                // 相当于占据了坑位，与运算 会把之前的坑位占据 这样减少了暴力枚举下面
                // 因为 最长为 30次循环
                val |= nums[j];
                j--;
            }
            ans = Math.max(ans, i - j);

        }
        return ans;
    }


}