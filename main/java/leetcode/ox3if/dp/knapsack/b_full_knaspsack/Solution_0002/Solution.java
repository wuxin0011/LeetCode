package leetcode.ox3if.dp.knapsack.b_full_knaspsack.Solution_0002;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/perfect-squares
 * @title: 完全平方数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "numSquares", "in.txt");
        IoUtil.testUtil(Solution.class, "numSquares1", "in.txt");
        IoUtil.testUtil(Solution.class, "numSquares2", "in.txt");
    }

    public int numSquares(int n) {
        int N = (int) Math.sqrt(n * 1.0);
        if (N * N == 1) {
            return 1;
        }
        int inf = 0x3ffffff;
        int[] f = new int[n + 1];
        Arrays.fill(f, inf);
        f[0] = 0;

        for (int i = 0; i <= N; i++) {
            for (int c = i * i; c <= n; c++) {
                f[c] = Math.min(f[c], f[c - i * i] + 1);
            }
        }
        return f[n];
    }

    public int numSquares1(int n) {
        int N = (int) Math.sqrt(n * 1.0);
        if (N * N == 1) {
            return 1;
        }
        int inf = 0x3ffffff;
        N += 2;
        int[][] f = new int[N][n + 1];
        for (int[] ints : f) {
            Arrays.fill(ints, inf);
        }
        f[0][0] = 0;

        for (int i = 0; i <= N - 2; i++) {
            for (int c = 0; c <= n; c++) {
                if (c < i * i) {
                    f[i + 1][c] = f[i][c];
                } else {
                    f[i + 1][c] = Math.min(f[i][c], f[i + 1][c - i * i] + 1);
                }
            }
        }
        return f[N - 1][n];
    }


    public int numSquares2(int n) {
        L = (int) Math.sqrt(n * 1.0);
        if (L * L == 1) {
            return 1;
        }
        memo = new int[L + 1][n + 1];
        for (int i = 0; i <= L; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(L, n);
    }

    int L = 0;
    int[][] memo;

    public int dfs(int i, int c) {
        if (i == 0) {
            return c == 0 ? 0 : 0x3ffff;
        }
        if (memo[i][c] != -1) {
            return memo[i][c];
        }
        int res = 0;
        if (c < i * i) {
            res = dfs(i - 1, c);
        } else {
            res = Math.min(dfs(i - 1, c), dfs(i, c - i * i) + 1);
        }
        memo[i][c] = res;
        return res;
    }

}