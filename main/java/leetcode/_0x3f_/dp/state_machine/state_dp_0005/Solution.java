package leetcode._0x3f_.dp.state_machine.state_dp_0005;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 * @title: 买卖股票的最佳时机含手续费
 */
public class Solution {


    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "maxProfit", "in.txt");
        }


        public int maxProfit(int[] a, int k) {
            this.a = a;
            int n = a.length;
            this.memo = new int[n][2];
            this.k = k;
            for (int i = 0; i < n; i++) {
                Arrays.fill(memo[i], -1);
            }

            return dfs(n - 1, 0);
        }


        int[][] memo;

        static int inf = 0x3ffffff;
        int[] a;
        int k;

        public int dfs(int i, int hold) {
            if (i < 0) {
                return hold == 1 ? -inf : 0;
            }
            if (memo[i][hold] != -1) {
                return memo[i][hold];
            }
            int res = 0;
            if (hold == 1) {
                res = Math.max(dfs(i - 1, hold), dfs(i - 1, 0) - a[i]);
            } else {
                res = Math.max(dfs(i - 1, hold), a[i] - k > 0 ? dfs(i - 1, hold ^ 1) + a[i] - k : 0);
            }
            return memo[i][hold] = res;
        }

    }


}