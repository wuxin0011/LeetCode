package leetcode.contest.biweekly.bi_100.bi_135.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-135/problems/find-the-winning-player-in-coin-game
 * @title: 求出硬币游戏的赢家
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "losingPlayer", "A.txt");
    }


    public String losingPlayer(int x, int y) {
        int tot = 115;
        int i = 0;
        // 75 // 4y
        while (x >= 0 || y >= 0) {
            if ((x - 1) >= 0 && y - 4 >= 0) {
                x--;
                y -= 4;
            } else {
                break;
            }
            i++;
        }
        return i % 2 == 1 ? "Alice" : "Bob";
    }


}