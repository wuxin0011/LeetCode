package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-number-of-coins-to-be-added
 * @title: minimum-number-of-coins-to-be-added
 */
public class Code_0029_2952 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0029_2952.class, "minimumAddedCoins", "txt_file\\Code_0029_2952.txt");
    }


    public int minimumAddedCoins(int[] coins, int target) {

        Arrays.sort(coins);
        int ans = 0, s = 1, i = 0, n = coins.length;
        while (s <= target) {
            if (i < n && s >= coins[i]) {
                s += coins[i];
                i++;
            } else {
                ans++;
                s *= 2;
            }
        }
        return ans;
    }


}