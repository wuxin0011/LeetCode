package leetcode.contest.weekly.w_400.w_409.b;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-409/problems/shortest-distance-after-road-addition-queries-i
 * @title: 新增道路查询后的最短距离 I
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "shortestDistanceAfterQueries", "B.txt");
    }


    int n;
    int[][] g;

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        this.g = new int[n][n];
        this.n = n;
        //System.out.println(Arrays.deepToString(g));
        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], n + 1);
            g[i][i] = 0;
        }

        int[] ans = new int[queries.length];
        for (int b = 0; b < n; b++) {
            for (int i = 0; i < b; i++) {
                for (int j = i + 1; j < n; j++) {
                    g[i][j] = Math.min(g[i][j], calc(i, b, g[i][b]) + calc(b, j, g[b][j]));
                }
            }
        }
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];
            g[u][v] = 1;
            f(u);
            f(v);
            ans[i] = g[0][n - 1];
            if (ans[i] == 1) {
                Arrays.fill(ans, i, queries.length, 1);
                break;
            }
        }
        return ans;
    }


    public void f(int p) {
        for (int i = 0; i < p; i++) {
            for (int j = p + 1; j < n; j++) {
                g[i][j] = Math.min(g[i][j], calc(i, p, g[i][p]) + calc(p, j, g[p][j]));
            }
        }
    }

    public int calc(int i, int j, int x) {
        if (j < i) {
            return x;
        }
        return Math.min(Math.abs(i - j), x);
    }


}