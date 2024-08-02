package leetcode._0x3f_.data_struct.monotonic_stacks.contribution_method.Solution_0002;

import code_generation.utils.IoUtil;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1856. 子数组最小乘积的最大值
 * <p>
 * 一个数组的 最小乘积定义为这个数组中 最小值乘以数组的 和。
 * 比方说，数组[3,2,5]（最小值是2）的最小乘积为2 * (3+2+5) = 2 * 10 = 20。
 * 给你一个正整数数组nums，请你返回nums任意非空子数组的最小乘积的最大值。由于答案可能很大，请你返回答案对10^9 + 7取余的结果。
 * 请注意，最小乘积的最大值考虑的是取余操作 之前的结果。题目保证最小乘积的最大值在 不取余 的情况下可以用 64 位有符号整数保存。
 * 子数组定义为一个数组的 连续部分。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,2]
 * 输出：14
 * 解释：最小乘积的最大值由子数组 [2,3,2] （最小值是 2）得到。
 * 2 * (2+3+2) = 2 * 7 = 14 。
 * <p>
 * 示例 2：
 * 输入：nums = [2,3,3,1,2]
 * 输出：18
 * 解释：最小乘积的最大值由子数组 [3,3] （最小值是 3）得到。
 * 3 * (3+3) = 3 * 6 = 18 。
 * <p>
 * 示例 3：
 * 输入：nums = [3,1,5,6,4,2]
 * 输出：60
 * 解释：最小乘积的最大值由子数组 [5,6,4] （最小值是 4）得到。
 * 4 * (5+6+4) = 4 * 15 = 60 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^7
 *
 * @author: wuxin0011
 * @Description: 前缀和 + 单调栈
 * @url: https://leetcode.cn/problems/maximum-subarray-min-product
 * @title: 子数组最小乘积的最大值
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxSumMinProduct", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int maxSumMinProduct(int[] a) {
        Deque<Integer> sk = new ArrayDeque<>();
        int n = a.length;
        long[] sums = new long[n];
        sums[0] = a[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + a[i];
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            // 大 -> 小 允许相等
            // 1 2 3 2
            // 当 a[i] = 2,a[-1] = 3
            // 以 cuo 为 min 的 范围有 [sk.peek() + 1,i)
            while (!sk.isEmpty() && a[i] < a[sk.peek()]) {
                int cur = sk.pop();
                int left = sk.isEmpty() ? 0 : (sk.peek() + 1);
                ans = Math.max(ans, sum(sums, left, i - 1, a[cur]));
            }
            sk.push(i);
        }
        while (!sk.isEmpty()) {
            int cur = sk.pop();
            int left = sk.isEmpty() ? 0 : (sk.peek() + 1);
            ans = Math.max(ans, sum(sums, left, n - 1, a[cur]));
        }
        return (int) (ans % MOD);
    }


    // 统计最小值
    public static long sum(long[] sums, int l, int r, int min) {
        long val = l == 0 ? sums[r] : (sums[r] - sums[l - 1]);
        return min * val;
    }


}