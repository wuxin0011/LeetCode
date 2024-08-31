package leetcode.contest.weekly.w_400.w_411.b;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-411/problems/maximum-energy-boost-from-two-drinks
 * @title: 超级饮料的最大强化能量
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "maxEnergyBoost", "B.txt");
    }


    public long maxEnergyBoost(int[] a, int[] b) {

        this.n = a.length;
        this.a = a;
        this.b = b;
        memo = new long[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return Math.max(f(0, 0), f(0, 1));
    }

    long[][] memo;
    int[] a, b;
    int n;

    public long f(int i, int j) {
        if (i >= n) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int[] cur = j == 0 ? a : b;
        long res = Math.max(f(i + 1, j) + cur[i], f(i + 2, j ^ 1) + cur[i]);
        return memo[i][j] = res;

    }


}