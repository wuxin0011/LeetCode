package leetcode.contest.biweekly.bi_100.bi_155;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/biweekly-contest-155/problems/maximum-profit-from-valid-topological-order-in-dag">有向无环图中合法拓扑排序的最大利润</a>
 * @title: 有向无环图中合法拓扑排序的最大利润
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "maxProfit", "D.txt");
    }


    public int maxProfit(int n, int[][] edges, int[] score) {

        if (edges.length == 0) {
            Arrays.sort(score);
            int tot = 0;
            for (int i = 0; i < score.length; i++) {
                tot += (i + 1) * score[i];
            }
            return tot;
        }
        this.f = new int[n];
        for (int[] e : edges) {
            f[e[1]] |= 1 << e[0];
        }
        memo = new Integer[1 << n];
        this.score = score;
        return dfs(0);
    }

    Integer[] memo;
    int[] f, score;


    public int dfs(int mask) {
        if (memo[mask] != null) return memo[mask];
        int k = Integer.bitCount(mask) + 1;
        int ans = 0;
        for (int i = 0; i < f.length; i++) {
            // 1 i 没有选择
            // 2 mask | nxt == mask 表示 nxt 是 mask 的子集 不存存 u,v 在 u 没访问就到达v的情况
            if ((mask >> i & 1) == 0 && (mask | f[i]) == mask) {
                ans = Math.max(ans, dfs(mask | 1 << i) + score[i] * k);
            }
        }
        return memo[mask]=ans;
    }


}