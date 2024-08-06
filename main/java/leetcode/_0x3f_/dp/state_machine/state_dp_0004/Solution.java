package leetcode._0x3f_.dp.state_machine.state_dp_0004;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 309. 买卖股票的最佳时机含冷冻期
 * <p>
 * 给定一个整数数组prices，其中第prices[i]表示第i天的股票价格 。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * <p>
 * 示例 2:
 * 输入: prices = [1]
 * 输出: 0
 * <p>
 * 提示：
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown
 * @title: 买卖股票的最佳时机含冷冻期
 */
public class Solution {


    static class S0 {
        public static void main(String[] args) {
            IoUtil.testUtil(S0.class, "maxProfit", "in.txt");
        }


        public int maxProfit(int[] prices) {
            a = prices;
            int n = prices.length;
            memo = new int[n][2];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 1) {
                        memo[i][j] = Math.max(dfs(i - 1, j), dfs(i - 2, j ^ 1) - a[i]);
                    }else {
                        memo[i][j] = Math.max(dfs(i - 1, j), dfs(i - 1, j ^ 1) + a[i]);
                    }
                }
            }

            return dfs(n - 1, 0);
        }

        int[][] memo;

        static int inf = 0x3ffffff;
        int[] a;

        public int dfs(int i, int hold) {
            if (i < 0) {
                return hold == 1 ? -inf : 0;
            }
            return memo[i][hold];
        }

    }


    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "maxProfit", "in.txt");
        }


        public int maxProfit(int[] prices) {
            a = prices;
            int n = prices.length;
            memo = new int[n][2];
            for (int i = 0; i < n; i++) {
                Arrays.fill(memo[i], -1);
            }

            return dfs(n - 1, 0);
        }

        int[][] memo;

        static int inf = 0x3ffffff;
        int[] a;

        public int dfs(int i, int hold) {
            if (i < 0) {
                return hold == 1 ? -inf : 0;
            }
            if (memo[i][hold] != -1) {
                return memo[i][hold];
            }
            int res = 0;
            if (hold == 1) {
                res = Math.max(dfs(i - 1, hold), dfs(i - 2, hold ^ 1) - a[i]);
            } else {
                res = Math.max(dfs(i - 1, hold), dfs(i - 1, hold ^ 1) + a[i]);
            }
            return memo[i][hold] = res;
        }

    }


    static class S2 {
        public static void main(String[] args) {
            IoUtil.testUtil(S2.class, "maxProfit", "in.txt");
        }


        public int maxProfit(int[] prices) {
            a = prices;
            int n = prices.length;
            return dfs(n - 1, false);
        }

        static int inf = 0x3ffffff;
        int[] a;

        public int dfs(int i, boolean hold) {
            if (i < 0) {
                return hold ? -inf : 0;
            }
            if (hold) {
                return Math.max(dfs(i - 1, true), dfs(i - 2, false) - a[i]);
            }
            return Math.max(dfs(i - 1, false), dfs(i - 1, true) + a[i]);
        }

    }

}