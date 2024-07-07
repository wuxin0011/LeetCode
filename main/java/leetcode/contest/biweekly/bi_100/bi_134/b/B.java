package leetcode.contest.biweekly.bi_100.bi_134.b;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-134/problems/maximum-points-after-enemy-battles
 * @title: 与敌人战斗后的最大分数
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "maximumPoints", "B.txt");
    }


    public long maximumPoints(int[] e, int c) {
        Arrays.sort(e);
        if (c < e[0]) {
            return 0;
        }
        long s = 0;
        for (int val : e) {
            s += val;
        }
        long ans = 0;
        int mi = e[0];
        s -= mi;
        ans += c / mi;
        c -= mi * (c / mi);
        s += c;
        return ans + s / mi;
    }


}