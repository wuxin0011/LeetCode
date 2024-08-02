package leetcode._0x3f_.bitwise_operations.and_or.and_or_0006;

import code_generation.utils.IoUtil;

import java.util.Arrays;

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

    // 本题类似与滑动窗口
    // 统计对应数位1出现次数
    // mask 统计值 d 为位数
    // 对于首次 出现0->1 需要  mask | 1 << d (也可以写成 mask + (1 << d))
    // 对于首次 出现1->0 需要  mask ^ (1 << d) (移出当前位数的1 也可以直接写成 mask - (1 << d)

    public int[] smallestSubarrays(int[] nums) {
        int mx = 0;
        int[] mx_h = new int[32];
        for (int num : nums) {
            mx = calc(mx_h, num, mx, true);
        }
        int n = nums.length;
        // System.out.println("mx = " + mx);
        int[] ans = new int[n];
        int[] h = new int[32];

        if (mx == 0) {
            Arrays.fill(ans, 1);
            return ans;
        }

        for (int l = 0, r = 0, v = 0, k = 0; k < n; k++, l++) {
            // 注意本题 mx 会 随着 K 移动 而变化
            while (v < mx && r < n) {
                v = calc(h, nums[r], v, true);
                r++;
            }
            ans[k] = r - l;
            v = calc(h, nums[l], v, false);

            // 减小最大值
            mx = calc(mx_h, nums[l], mx, false);
        }
        return ans;
    }


    public static int calc(int[] help, int num, int v, boolean isAdd) {
        if (num == 0) return v;
        for (int i = 31; i >= 0; i--) {
            int r = 31 - i;
            int tot = 1 << r;
            if (tot > num) { // 减枝
                break;
            }
            int d = num >> r & 1;
            if (d == 0) {
                continue;
            }
            if (isAdd) {
                help[i] += 1;
                // 首次加入
                if (help[i] == 1) {
                    v |= 1 << r;
                }
            } else {
                help[i] -= 1;
                // 当前为没有数字了
                if (help[i] == 0) {
                    v ^= (1 << r);
                }
            }

        }
        return v;
    }


}