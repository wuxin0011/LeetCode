package leetcode.contest.biweekly.bi_100.bi_136.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-136/problems/find-the-number-of-winning-players
 * @title: 求出胜利玩家的数目
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "winningPlayerCount", "A.txt");
    }


    public int winningPlayerCount(int n, int[][] pick) {
        int x = 0;
        int[][] a = new int[n][11];
        for (int[] ints : pick) {
            int pos = ints[0];
            int c = ints[1];
            a[pos][c]++;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 11; j++) {
                if (a[i][j] > i) {
                    x++;
                    break;
                }
            }
        }
        return x;
    }


}