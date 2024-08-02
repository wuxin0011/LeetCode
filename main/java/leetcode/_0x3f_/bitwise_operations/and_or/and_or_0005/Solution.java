package leetcode._0x3f_.bitwise_operations.and_or.and_or_0005;

import code_generation.utils.IoUtil;

/**
 * 2680. 最大或值
 * <p>
 * 给你一个下标从 0开始长度为 n的整数数组nums和一个整数k 。
 * 每一次操作中，你可以选择一个数并将它乘2。
 * 你最多可以进行 k次操作，请你返回nums[0] | nums[1] | ... | nums[n - 1]的最大值。
 * a | b表示两个整数 a和 b的 按位或运算。
 * <p>
 * 示例 1：
 * 输入：nums = [12,9], k = 1
 * 输出：30
 * 解释：如果我们对下标为 1 的元素进行操作，新的数组为 [12,18] 。此时得到最优答案为 12 和 18 的按位或运算的结果，也就是 30 。
 * <p>
 * 示例 2：
 * 输入：nums = [8,1,2], k = 2
 * 输出：35
 * 解释：如果我们对下标 0 处的元素进行操作，得到新数组 [32,1,2] 。此时得到最优答案为 32|1|2 = 35 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 15
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-or
 * @title: 最大或值
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maximumOr", "in.txt");
//        System.out.println(Integer.toBinaryString(9));
//        System.out.println(Integer.toBinaryString(12));
//
//        System.out.println();
//        System.out.println(Integer.toBinaryString(1));
//        System.out.println(Integer.toBinaryString(2));
//        System.out.println(Integer.toBinaryString(8));
//
//        System.out.println();
//        System.out.println((12 << 1) | 9);
//        System.out.println((12) | (9 << 1));
    }


    public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        // 后缀与的和
        long[] suf = new long[n];
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = nums[i + 1] | suf[i + 1];
        }
        long pre = 0;
        long ans = 0;
        // k 最大值为 15
        // num[i] 最大值为 10^9 小于 2 ^ 30
        for (int i = 0; i < n; i++) {
            //整体与 考虑将 k 给其中一个数
            ans = Math.max(ans, pre | suf[i] | ((long) nums[i] << k));
            // 前缀与的和
            pre |= nums[i];
        }
        return ans;
    }


}