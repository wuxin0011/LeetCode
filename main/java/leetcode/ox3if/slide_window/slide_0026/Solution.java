package leetcode.ox3if.slide_window.slide_0026;

import code_generation.utils.IoUtil;

/**
 * 2401. 最长优雅子数组
 * <p>
 * 给你一个由 正 整数组成的数组 nums 。如果nums 的子数组中位于 不同 位置的每对元素按位 与（AND）运算的结果等于 0 ，则称该子数组为 优雅 子数组。返回 最长 的优雅子数组的长度。子数组 是数组中的一个 连续 部分。注意：长度为 1 的子数组始终视作优雅子数组。
 * <p>
 * 示例 1：输入：nums = [1,3,8,48,10]输出：3解释：最长的优雅子数组是 [3,8,48] 。子数组满足题目条件：- 3 AND 8 = 0- 3 AND 48 = 0- 8 AND 48 = 0可以证明不存在更长的优雅子数组，所以返回 3 。
 * <p>
 * 示例 2：输入：nums = [3,1,5,11,13]输出：1解释：最长的优雅子数组长度为 1 ，任何长度为 1 的子数组都满足题目条件。
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/longest-nice-subarray
 * @title: longest-nice-subarray
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "longestNiceSubarray", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int longestNiceSubarray(int[] nums) {

        // 判断 32 位数中是否使用了 如果使用了就不满足要求
        int r = 0, l = 0;
        int n = nums.length;
        int[] h = new int[32];
        int ans = 1;
        while (r < n) {
            f(h, nums[r], true);
            while (!check(h)) {
                f(h, nums[l], false);
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }
        return ans;
    }

    public static void f(int[] h, int v1, boolean is) {
        for (int i = 0; i < h.length; i++) {
            if ((v1 >> i & 1) == 1) {
                h[i] += is ? 1 : -1;
            }
        }
    }

    public static boolean check(int[] h) {
        for (int j : h) {
            if (j > 1) {
                return false;
            }
        }
        return true;
    }


}