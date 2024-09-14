package leetcode.contest.weekly.w_400.w_413.c;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description: 值阈状压dp
 * @url: https://leetcode.cn/contest/weekly-contest-413/problems/select-cells-in-grid-with-maximum-score
 * @title: 选择矩阵中单元格的最大得分
 */
public class C {


    static class s0 {
        public static void main(String[] args) {
            IoUtil.testUtil(s0.class, "maxScore", "C.txt");
        }

        ArrayList<Integer>[] temp;
        boolean[] vis = new boolean[101];

        public int maxScore(List<List<Integer>> grid) {
            int m = grid.size();
            int n = grid.get(0).size();
            temp = new ArrayList[101];
            int mx = 0;
            for (int i = 0; i < m; i++) {
                Arrays.fill(vis, false);
                for (int v : grid.get(i)) {
                    if (vis[v]) {
                        continue;
                    }
                    if (temp[v] == null) {
                        temp[v] = new ArrayList<>();
                    }
                    temp[v].add(i);
                    vis[v] = true;
                    mx = Math.max(mx, v);
                }
            }
            // 枚举行
            int s = 1 << m;
            int ans = 0;
            int[][] f = new int[s][mx + 10];
            for (int k = 0; k < s; k++) {
                for (int v = 1; v <= mx; v++) {
                    if (v >= temp.length || temp[v] == null || temp[v].size() == 0) {
                        f[k][v] = f[k][v - 1];
                    } else {
                        f[k][v] = Math.max(f[k][v - 1], f[k][v]);
                        for (int row : temp[v]) {
                            if ((k >> row & 1) == 1) {
                                f[k][v] = Math.max(f[k & (~(1 << row))][v - 1] + v, f[k][v]);
                            }
                        }
                    }

                }
                ans = Math.max(ans, f[k][mx]);
            }
            return ans;
        }
    }


    static class s1 {
        public static void main(String[] args) {
            IoUtil.testUtil(s1.class, "maxScore", "C.txt");
        }

        ArrayList<Integer>[] temp;
        boolean[] vis = new boolean[101];

        public int maxScore(List<List<Integer>> grid) {
            int m = grid.size();
            int n = grid.get(0).size();
            temp = new ArrayList[101];
            int mx = 0;
            for (int i = 0; i < m; i++) {
                Arrays.fill(vis, false);
                for (int v : grid.get(i)) {
                    if (vis[v]) {
                        continue;
                    }
                    if (temp[v] == null) {
                        temp[v] = new ArrayList<>();
                    }
                    temp[v].add(i);
                    vis[v] = true;
                    mx = Math.max(mx, v);
                }
            }
            // 枚举行
            int ans = 0;
            int s = 1 << m;
            memo = new int[s][mx + 10];
            for (int i = 0; i < s; i++) {
                Arrays.fill(memo[i], -1);
            }
            for (int k = s - 1; k >= 0; k--) {
                ans = Math.max(ans, dfs(k, mx));
            }
            return ans;

        }

        int[][] memo;

        public int dfs(int s, int v) {
            if (v <= 0) {
                return 0;
            }
            if (memo[s][v] != -1) {
                return memo[s][v];
            }
            if (v >= temp.length || temp[v] == null || temp[v].size() == 0) {
                return dfs(s, v - 1);
            }
            int ans = 0;
            ans = Math.max(dfs(s, v - 1), ans);
            for (int row : temp[v]) {
                if ((s >> row & 1) == 1) {
                    ans = Math.max(dfs(s & (~(1 << row)), v - 1) + v, ans);
                }
            }
            return memo[s][v] = ans;
        }

    }

}