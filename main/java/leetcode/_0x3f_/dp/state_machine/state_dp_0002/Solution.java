package leetcode._0x3f_.dp.state_machine.state_dp_0002;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 123. 买卖股票的最佳时机 III
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例1:
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * <p>
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * 示例 3：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 示例 4：
 * 输入：prices = [1]
 * 输出：0
 * <p>
 * 提示：
 * 1 <=prices.length <= 10^5
 * 0 <=prices[i] <=10^5
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii
 * @title: best-time-to-buy-and-sell-stock-iii
 */
public class Solution {


    // 递归超时
    static class S0 {
        public static void main(String[] args) {
            IoUtil.testUtil(S0.class, "maxProfit", "in.txt");
        }


        public int maxProfit(int[] prices) {
            return f(prices, 2);
        }

        public int f(int[] p, int k) {
            int n = p.length;
            memo = new int[n][k + 1][2];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= k; j++) {
                    for (int u = 0; u < 2; u++) {
                        if (u == 1) {
                            memo[i][j][u] = Math.max(dfs(i - 1, j, u), dfs(i - 1, j, 0) - p[i]);
                        } else {
                            memo[i][j][u] = Math.max(dfs(i - 1, j, u), dfs(i - 1, j - 1, 1) + p[i]);
                        }
                    }
                }
            }
            return memo[n - 1][k][0];
        }


        static int inf = 0x3ffffff;
        int[][][] memo;

        public int dfs(int i, int j, int u) {
            if (j < 0) return -inf;
            if (i < 0) return u == 1 ? -inf : 0;
            return memo[i][j][u];
        }

    }

    // 记忆化搜索 递归超时
    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "maxProfit", "in.txt");
        }


        public int maxProfit(int[] prices) {
            return f(prices, 2);
        }

        public int f(int[] p, int k) {
            a = p;
            int n = p.length;
            memo = new int[n][k + 1][2];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= k; j++) {
                    Arrays.fill(memo[i][j], -1);
                }
            }
            return dfs(n - 1, k, 0);
        }


        static int inf = 0x3ffffff;
        int[][][] memo;
        int[] a;

        public int dfs(int i, int j, int u) {
            if (j < 0) return -inf;
            if (i < 0) return u == 1 ? -inf : 0;
            if (memo[i][j][u] != -1) {
                return memo[i][j][u];
            }
            int res = 0;
            if (u == 1) {
                res = Math.max(dfs(i - 1, j, u), dfs(i - 1, j, 0) - a[i]);
            } else {
                res = Math.max(dfs(i - 1, j, u), dfs(i - 1, j - 1, 1) + a[i]);
            }
            return memo[i][j][u] = res;
        }

    }

}