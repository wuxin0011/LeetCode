package leetcode.ox3if.slide_window.slide_0027;

import code_generation.utils.IoUtil;

/**
 * 1658. 将 x 减到 0 的最小操作数
 * <p>
 * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。如果可以将 x恰好 减到0 ，返回 最小操作数 ；否则，返回 -1 。
 * <p>
 * 示例 1：输入：nums = [1,1,4,2,3], x = 5输出：2解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
 * <p>
 * 示例 2：输入：nums = [5,6,7,8,9], x = 4输出：-1
 * <p>
 * 示例 3：输入：nums = [3,2,20,1,1,3], x = 10输出：5解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 * 1 <= x <= 109
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero
 * @title: minimum-operations-to-reduce-x-to-zero
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minOperations", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int minOperations(int[] nums, int x) {

        int tot = 0;
        for (int num : nums) {
            tot += num;
        }
        if (tot < x) {
            return -1;
        }
        if (tot == x) return nums.length;
        int r = 0, l = 0, ans = 0, n = nums.length;
        int target = tot - x;
        while (r < n) {

            target -= nums[r];

            while (target<0) {
                target += nums[l];
                l++;
            }
            if (target == 0) {
                // System.out.println("v = " + v + ",x = " + x + ",tot = " + tot + ",target = " + target);
                ans = Math.max(ans, r - l + 1);
            }
            r++;
        }


        return ans == 0 ? -1 : n - ans;
    }


}