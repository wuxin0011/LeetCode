package leetcode.contest.weekly.w_400.w_419.c;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-419/problems/count-the-number-of-winning-sequences
 * @title: 统计能获胜的出招序列数
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "countWinningSequences", "C.txt");
    }


    static int MOD = (int) 1e9 + 7;

    public int countWinningSequences(String s) {
        a = s.toCharArray();
        n = a.length;
        max = n;
        memo = new Long[n][n * 2 + 1][4];
        long ans = dfs(0, 0, 0);
        return (int) (ans % MOD);
    }

    int n, max;
    char[] a;

    Long[][][] memo;
    static char[] dir = {'F', 'W', 'E'};

    int getId(char c) {
        if (c == 'F') {
            return 1;
        } else if (c == 'W') {
            return 2;
        } else if (c == 'E') {
            return 3;
        }
        return 0;
    }

    int fc(int idx, int idy) {
        if (idx == idy) {
            return 0;
        }
        if (idx == 1) {
            return idy == 2 ? 1 : -1;
        }
        if (idx == 2) {
            return idy == 3 ? 1 : -1;
        }
        if (idx == 3) {
            return idy == 1 ? 1 : -1;
        }
        return 0;
    }


    long dfs(int i, int score, int j) {
        // 减枝1 必输
        if (n - i + score <= 0) {
            return 0;
        }
        // 减枝2 必赢
        if (score - (n - i) > 0) {

            // 快速幂优化方案数 注意 x需要转换成 long 类型 以及 快速幂过程需要取模
            return pow(2, n - i);
        }
        if (i >= n) {
            return 1;
        }
        if (memo[i][score + max][j] != null) {
            return memo[i][score + max][j];
        }
        long ans = 0;
        for (char c : dir) {
            int idy = getId(c);
            if (idy == j) continue;
            int idx = getId(a[i]);
            ans += dfs(i + 1, score + fc(idx, idy), idy);
            ans %= MOD;
        }
        return memo[i][score + max][j] = ans;

    }

    static long pow(long x, int n) {
        long b = 1L;
        while (n > 0) {
            if ((n & 1) == 1) {
                b *= x;
                b %= MOD;
            }
            x *= x;
            x %= MOD;
            n >>= 1;
        }
        return b;
    }


}