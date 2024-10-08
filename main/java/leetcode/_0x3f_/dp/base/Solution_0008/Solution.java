package leetcode._0x3f_.dp.base.Solution_0008;

import code_generation.utils.IoUtil;

/**
 * 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * 示例1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 3：
 * 输入：nums = [1,2,3]
 * 输出：3
 * 提示：
 * 1 <= nums.length <= 10^0
 * 0 <= nums[i] <= 1000
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/house-robber-ii
 * @title: house-robber-ii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "rob", "in.txt");
        IoUtil.testUtil(Solution.class, "rob1", "in.txt");
    }


    public int rob1(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        return Math.max(helper1(nums, 0, n - 2), helper1(nums, 1, n - 1));
    }


    // 递归版本
    public static int helper1(int[] nums, int st, int i) {
        if (i < st) {
            return 0;
        }
        return Math.max(helper1(nums, st, i - 1), helper1(nums, st, i - 2) + nums[i]);
    }


    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        return Math.max(helper(nums, 0, n - 2), helper(nums, 1, n - 1));
    }

    public static int helper(int[] nums, int st, int ed) {
        int prePre = nums[st];
        int pre = Math.max(nums[st], st + 1 <= ed ? nums[st + 1] : nums[st]);
        int cur = pre;
        for (int i = st + 2; i <= ed; i++) {
            cur = Math.max(pre, prePre + nums[i]);
            prePre = pre;
            pre = cur;
        }
        return cur;
    }


    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        return Math.max(helper2(nums, 0, n - 2), helper2(nums, 1, n - 1));
    }

    public static int helper2(int[] nums, int st, int ed) {
        int size = ed - st + 1;
        int[] dp = new int[size];
        dp[0] = nums[st];
        dp[1] = Math.max(nums[st], st + 1 <= ed ? nums[st + 1] : 0);
        for (int i = 2; i < size; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i + st]);
        }
        return dp[size - 1];
    }


}