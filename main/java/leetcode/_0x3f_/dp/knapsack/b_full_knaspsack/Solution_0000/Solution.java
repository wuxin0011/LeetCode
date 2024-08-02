package leetcode._0x3f_.dp.knapsack.b_full_knaspsack.Solution_0000;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * <p>
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 示例1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * <p>
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 * <p>
 * 提示：
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/coin-change
 * @title: 零钱兑换
 */
public class Solution {
    static class S0 {
        public static void main(String[] args) {
            IoUtil.testUtil(S0.class, "coinChange", "in.txt");
        }


        public int coinChange(int[] coins, int amount) {
            int n = coins.length;
            int[] f = new int[amount + 1];
            int inf = 0x3ffff;
            Arrays.fill(f, inf);
            f[0] = 0;
            for (int coin : coins) {
                for (int c = coin; c <= amount; c++) {
                    f[c] = Math.min(f[c], f[c - coin] + 1);
                }
            }
            return f[amount] >= inf ? -1 : f[amount];
        }


    }


    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "coinChange", "in.txt");
        }


        public int coinChange(int[] coins, int amount) {
            int n = coins.length;
            int[][] f = new int[n + 1][amount + 1];
            int inf = 0x3ffff;
            for (int i = 0; i <= n; i++) {
                Arrays.fill(f[i], inf);
            }
            f[0][0] = 0;
            for (int i = 0; i < n; i++) {
                for (int c = 0; c <= amount; c++) {
                    if (c < coins[i]) {
                        f[i + 1][c] = f[i][c];
                    } else {
                        f[i + 1][c] = Math.min(f[i][c], f[i + 1][c - coins[i]] + 1);
                    }
                }
            }
            return f[n][amount] >= inf ? -1 : f[n][amount];
        }


    }

    static class S2 {
        public static void main(String[] args) {
            IoUtil.testUtil(S2.class, "coinChange", "in.txt");
        }

        static int inf = 0x3ffff;

        public int coinChange(int[] coins, int amount) {
            int n = coins.length;
            memo = new int[n][amount + 1];
            this.coins = coins;

            for (int i = 0; i < n; i++) {
                Arrays.fill(memo[i], -1);
            }
            int v = dfs(n - 1, amount);
            return v >= inf ? -1 : v;
        }

        int[] coins;

        int[][] memo;

        public int dfs(int i, int c) {
            if (i < 0) {
                return c == 0 ? 0 : inf;
            }
            if (memo[i][c] != -1) {
                return memo[i][c];
            }
            int res = 0;
            if (c < coins[i]) {
                res = dfs(i - 1, c);
            } else {
                res = Math.min(dfs(i - 1, c), dfs(i, c - coins[i]) + 1);
            }
            memo[i][c] = res;
            return res;
        }


    }

}