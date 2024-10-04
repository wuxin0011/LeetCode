package leetcode._0x3f_.dp.knapsack.d_goup_knaspack.Solution_0000;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-dice-rolls-with-target-sum
 * @title: 掷骰子等于目标和的方法数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "numRollsToTarget", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int numRollsToTarget(int n, int k, int target) {
        this.n = n;
        this.k = k;
        this.target = target;
        memo = new Long[n + 1][target + 1];
        return (int) (dfs(0, 0) % MOD);
    }


    int n, k, target;
    Long[][] memo;


    public long dfs(int i, int t) {
        if (i >= n) {
            return t == target ? 1 : 0;
        }
        if (memo[i][t] != null) return memo[i][t];
        long ans = 0;
        for (int score = 1; score <= k; score++) {
            if (t + score > target) break;
            ans += dfs(i + 1, t + score);
            ans %= MOD;
        }
        return memo[i][t] = ans;
    }


}