package leetcode.contest.weekly.w_300.w_394.c;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-394/problems/minimum-number-of-operations-to-satisfy-conditions
 * @title: 使矩阵满足条件的最少操作次数
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minimumOperations", "C.txt");
    }

    int ans = Integer.MAX_VALUE;


    public int minimumOperations(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] h = new int[col][10];
        for (int i = 0; i < col; i++) {
            for (int[] ints : grid) {
                h[i][ints[i]]++;
            }
        }
        dfs(-1, 0, row, 0, h);
        return ans;
    }


    public void dfs(int pre, int tot, int row, int col, int[][] h) {
        if (col == h.length) {
            ans = Math.min(tot, ans);
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (i != pre) {
                dfs(i, tot + (row - h[col][i]), row, col + 1, h);
            }
        }
    }


}