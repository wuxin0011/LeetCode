package leetcode.solution.Solution_0001;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/jump-game
 * @title: jump-game
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "canJump", "in.txt");
    }

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        // System.out.println(Arrays.toString(nums));
        int a = nums[0], n = nums.length;
        for (int i = 0; i < n; i++) {
            if (a < i) {
                return false;
            }
            a = Math.max(nums[i] + i, a);
        }
        return true;
    }
}