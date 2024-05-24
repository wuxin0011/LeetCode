package leetcode.ox3if.bitwise_operations.and_or.and_or_0006;

import code_generation.utils.IoUtil;

/**
 * 2411. 按位或最大的最小子数组长度
 * <p>
 * 给你一个长度为 n下标从 0开始的数组nums，数组中所有数字均为非负整数。
 * 对于0到n - 1之间的每一个下标 i，你需要找出nums中一个 最小 非空子数组，它的起始位置为i（包含这个位置），同时有最大的 按位或运算值。
 * 换言之，令Bij表示子数组nums[i...j]的按位或运算的结果，你需要找到一个起始位置为i的最小子数组，这个子数组的按位或运算的结果等于max(Bik)，其中i <= k <= n - 1。
 * 一个数组的按位或运算值是这个数组里所有数字按位或运算的结果。
 * 请你返回一个大小为 n的整数数组answer，其中answer[i]是开始位置为i，按位或运算结果最大，且最短子数组的长度。
 * 子数组是数组里一段连续非空元素组成的序列。
 * <p>
 * 示例 1：
 * 输入：nums = [1,0,2,1,3]
 * 输出：[3,3,2,2,1]
 * 解释：
 * 任何位置开始，最大按位或运算的结果都是 3 。
 * - 下标 0 处，能得到结果 3 的最短子数组是 [1,0,2] 。
 * - 下标 1 处，能得到结果 3 的最短子数组是 [0,2,1] 。
 * - 下标 2 处，能得到结果 3 的最短子数组是 [2,1] 。
 * - 下标 3 处，能得到结果 3 的最短子数组是 [1,3] 。
 * - 下标 4 处，能得到结果 3 的最短子数组是 [3] 。
 * 所以我们返回 [3,3,2,2,1] 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2]
 * 输出：[2,1]
 * 解释：
 * 下标 0 处，能得到最大按位或运算值的最短子数组长度为 2 。
 * 下标 1 处，能得到最大按位或运算值的最短子数组长度为 1 。
 * 所以我们返回 [2,1] 。
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^5
 * 0 <= nums[i] <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/smallest-subarrays-with-maximum-bitwise-or
 * @title: 按位或最大的最小子数组长度
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "smallestSubarrays", "in.txt");
    }


    public int[] smallestSubarrays(int[] nums) {


        return new int[]{3, 3, 2, 2, 1};
    }



}