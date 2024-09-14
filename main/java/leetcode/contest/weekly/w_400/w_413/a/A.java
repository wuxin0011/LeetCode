package leetcode.contest.weekly.w_400.w_413.a;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-413/problems/check-if-two-chessboard-squares-have-the-same-color
 * @title: 检查棋盘方格颜色是否相同
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "checkTwoChessboards", "A.txt");
    }

    static char[][] g = new char[9][9];

    static {
        int m = g.length, n = g[0].length;
        for (int i = 1; i <= 8; i += 2) {
            for (int j = 0; j < 8; j++) {
                g[i][j] = j % 2 == 0 ? 'B' : 'W';
            }
        }
        for (int i = 2; i <= 8; i += 2) {
            for (int j = 0; j < 8; j++) {
                g[i][j] = j % 2 == 1 ? 'B' : 'W';
            }
        }
        //System.out.print(Arrays.deepToString(g));
    }


    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        int c1 = coordinate1.charAt(0) - 'a';
        int r1 = coordinate1.charAt(1) - '0';
        int c2 = coordinate2.charAt(0) - 'a';
        int r2 = coordinate2.charAt(1) - '0';
        return g[r1][c1] == g[r2][c2];
    }


}