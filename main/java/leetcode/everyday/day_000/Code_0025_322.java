package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class Code_0025_322 {
    public static void main(String[] args) {
        IoUtil.testUtil(Code_0025_322.class,IoUtil.DEFAULT_METHOD_NAME,"txt_file\\Code_0025_322.txt");
    }


    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for(int coin : coins ){
            for(int i = coin;i<=amount;i++){
                dp[i] = Math.min(dp[i],dp[i-coin]+1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
