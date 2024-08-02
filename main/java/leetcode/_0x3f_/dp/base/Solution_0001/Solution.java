package leetcode._0x3f_.dp.base.Solution_0001;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 746. 使用最小花费爬楼梯
 * <p>
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。
 * 一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * 请你计算并返回达到楼梯顶部的最低花费。
 * <p>
 * 示例 1：
 * 输入：cost = [10,15,20]
 * 输出：15
 * 解释：你将从下标为 1 的台阶开始。
 * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
 * 总花费为 15 。
 * <p>
 * 示例 2：
 * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
 * 输出：6
 * 解释：你将从下标为 0 的台阶开始。
 * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
 * 总花费为 6 。
 * <p>
 * <p>
 * 提示：
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/min-cost-climbing-stairs
 * @title: 使用最小花费爬楼梯
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minCostClimbingStairs", "in.txt");
        IoUtil.testUtil(Solution.class, "minCostClimbingStairs1", "in.txt");
        IoUtil.testUtil(Solution.class, "minCostClimbingStairs2", "in.txt");
    }

    public int minCostClimbingStairs(int[] cost) {

        if (cost == null || cost.length <= 1) {
            return 0;
        }
        int n = cost.length;
        for (int i = 2; i < n; i++) {
            cost[i] = Math.min(cost[i - 1], cost[i - 2]) + cost[i];
        }
        return Math.min(cost[n - 1], cost[n - 2]);
    }


    public int minCostClimbingStairs0(int[] cost) {

        if (cost == null || cost.length <= 1) {
            return 0;
        }
        int n = cost.length;
        for (int i = 2; i < n; i++) {
            cost[i] = Math.min(cost[i - 1], cost[i - 2]) + cost[i];
        }
        return Math.min(cost[n - 1], cost[n - 2]);
    }


    // 递归版本
    public int minCostClimbingStairs1(int[] cost) {
        return Math.min(f1(cost.length - 1, cost), f1(cost.length - 2, cost));
    }

    public static int f1(int i, int[] cost) {
        if (i < 0) {
            return 0;
        }
        return Math.min(f1(i - 2, cost), f1(i - 1, cost)) + cost[i];
    }


    // 递归版本
    public int minCostClimbingStairs2(int[] cost) {
        memo = new int[cost.length];
        Arrays.fill(memo,-1);
        return Math.min(f2(cost.length - 1, cost), f2(cost.length - 2, cost));
    }

    int[] memo;

    public int f2(int i, int[] cost) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        memo[i] = Math.min(f2(i - 2, cost), f2(i - 1, cost)) + cost[i];
        return memo[i];
    }


}