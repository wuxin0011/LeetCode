package leetcode.contest.weekly.w_300.w_391.b;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class B {
    public static void main(String[] args) {
        IoUtil.testUtil(B.class, IoUtil.DEFAULT_METHOD_NAME, "B.txt");
    }

    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int rest = numBottles;
        int ans = numBottles;
        int cnt = 0;
        while (rest >= numExchange) {
            rest -= numExchange;
            cnt++;
            rest++;
            numExchange++;
        }
        return ans + cnt;
    }
}
