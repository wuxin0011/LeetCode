package leetcode.contest.biweekly.bi_100.bi_129.b;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-129/problems/right-triangles
 * @title: 直角三角形
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "numberOfRightTriangles", "B.txt");
    }


    public long numberOfRightTriangles(int[][] grid) {
        long cnt = 0;
        int m = grid.length, n = grid[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        // { row 1 cnt, col 1 cnt }
        for (int i = 0; i < m; i++) {
            int tot = 0;
            for (int j = 0; j < n; j++) {
                tot += grid[i][j];
            }
            row[i] = tot;
        }

        for (int i = 0; i < n; i++) {
            int tot = 0;
            for (int[] ints : grid) {
                tot += ints[i];
            }
            col[i] = tot;
        }
        // System.out.println("row = " + Arrays.toString(row) + " , col = " + Arrays.toString(col));

        for (int i = 0; i < n; i++) {
            int col_cnt = col[i];
            if(col_cnt==0) continue;
            for (int j = 0; j < m; j++) {
                int row_cnt = row[j];
                if(row_cnt == 0 ) {
                    continue;
                }
                if(grid[j][i] == 0 ) {
                    continue;
                }
                if(row_cnt == 1 || col_cnt == 1 ) {
                    continue;
                }
                //System.out.println("pre col cnt = " + col_cnt + ",row_cnt = " + row_cnt + "   (" + j + "," + i + ")");
                // System.out.println("col cnt = " + col_cnt + ",row_cnt = " + row_cnt + "(" + j + "," + i + ")");
                cnt += (long) Math.max(col_cnt - 1, 1) * Math.max(row_cnt - 1, 1);
            }
        }
        //System.out.println(cnt);
        return cnt;
    }


}