package leetcode._0x3f_.dp.knapsack.b_full_knaspsack.Solution_0001;

import code_generation.utils.IoUtil;

import java.util.Arrays;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/coin-change-ii
 * @title: coin-change-ii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "change", "in.txt");
        IoUtil.testUtil(Solution.class, "change1", "in.txt");
        IoUtil.testUtil(Solution.class, "change2", "in.txt");
    }


    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] f = new int[amount + 1];
        f[0] = 1;
        for (int coin : coins) {
            for (int c = coin; c <= amount; c++) {
                f[c] += f[c - coin];
            }
        }
        return f[amount];
    }

    public int change1(int amount, int[] coins) {
        int n = coins.length;
        int[][] f = new int[n + 1][amount + 1];
        f[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int c = 0; c <= amount; c++) {
                if (c < coins[i]) {
                    f[i + 1][c] = f[i][c];
                } else {
                    f[i + 1][c] = f[i][c] + f[i + 1][c - coins[i]];
                }
            }
        }
        return f[n][amount];
    }


    public int change2(int amount, int[] coins) {
        int n = coins.length;
        this.coins = coins;
        memo = new int[n][amount + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(n - 1, amount);
    }

    int[] coins;
    int[][] memo;

    public int dfs(int i, int c) {
        if (i < 0) {
            return c == 0 ? 1 : 0;
        }
        if(memo[i][c] !=-1) {
            return memo[i][c];
        }
        int res=  0;

        if( c < coins[i]) {
            res = dfs(i-1,c);
        }else {
            res = dfs(i-1,c) + dfs(i,c - coins[i]);
        }
        memo[i][c] = res;
        return res;
    }


}