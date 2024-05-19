package leetcode.ox3if.dp.base.Solution_0006;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 740. 删除并获得点数
 * <p>
 * 给你一个整数数组nums，你可以对它进行一些操作。
 * 每次操作中，选择任意一个nums[i]，删除它并获得nums[i]的点数。之后，你必须删除 所有 等于nums[i] - 1 和 nums[i] + 1的元素。
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * <p>
 * 示例 1：
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * <p>
 * 示例2：
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 * <p>
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] <= 10^4
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/delete-and-earn
 * @title: 删除并获得点数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "deleteAndEarn", "in.txt");
        IoUtil.testUtil(Solution.class, "deleteAndEarn1", "in.txt");
        IoUtil.testUtil(Solution.class, "deleteAndEarn2", "in.txt");
        IoUtil.testUtil(Solution.class, "deleteAndEarn3", "in.txt");
    }


    public int deleteAndEarn1(int[] nums) {

        // 删除或者不删除
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int mx = nums[0];
        for (int num : nums) {
            if (num > mx) mx = num;
        }
        mx++;
        int[] tot = new int[mx];
        for (int num : nums) {
            tot[num]++;
        }
        int[] dp = new int[tot.length];
        dp[0] = 0;
        dp[1] = tot[1];
        for (int i = 2; i < tot.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + tot[i] * i);
        }
        return dp[dp.length - 1];
    }


    // 最优空间版本
    public int deleteAndEarn(int[] nums) {

        // 删除或者不删除
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int mx = nums[0];
        for (int num : nums) {
            if (num > mx) mx = num;
        }
        mx++;
        int[] tot = new int[mx];
        for (int num : nums) {
            tot[num]++;
        }
        int prepre = 0;
        int pre = tot[1]; // 点数为1的得分
        int cur = 0;
        for (int i = 2; i < tot.length; i++) {
            cur = Math.max(pre, prepre + tot[i] * i);
            prepre = pre;
            pre = cur;
        }
        return cur;
    }


    public int deleteAndEarn2(int[] nums) {

        // 删除或者不删除
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int mx = nums[0];
        for (int num : nums) {
            if (num > mx) mx = num;
        }
        mx++;
        int[] tot = new int[mx];
        for (int num : nums) {
            tot[num]++;
        }
        return f1(tot.length - 1, tot);
    }

    public static int f1(int i, int[] tots) {
        if (i < 0) {
            return 0;
        }
        return Math.max(f1(i - 1, tots), f1(i - 2, tots) + tots[i] * i);
    }


    public int deleteAndEarn3(int[] nums) {

        // 删除或者不删除
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int mx = nums[0];
        for (int num : nums) {
            if (num > mx) mx = num;
        }
        mx++;
        int[] tot = new int[mx];
        for (int num : nums) {
            tot[num]++;
        }
        memo = new int[tot.length];
        Arrays.fill(memo, -1);
        return f1(tot.length - 1, tot);
    }

    int[] memo;

    public int f2(int i, int[] tots) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int cur = Math.max(f1(i - 1, tots), f1(i - 2, tots) + tots[i] * i);
        memo[i] = cur;
        return cur;
    }


}