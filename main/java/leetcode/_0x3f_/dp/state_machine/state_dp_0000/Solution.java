package leetcode._0x3f_.dp.state_machine.state_dp_0000;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/best-time-to-buy-and-sell-stock
 * @title: 买卖股票的最佳时机
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxProfit", "in.txt");
    }


    public int maxProfit(int[] prices) {
        // 想要利润最大 投入最小 卖出最大
        int ans = 0, mi = 0x3ffffff;
        for (int price : prices) {
            ans = Math.max(ans, price - mi);
            mi = Math.min(mi, price);
        }
        return ans;
    }

    public int maxProfit1(int[] prices) {
        // 想要利润最大 投入最小 卖出最大

        return 0;
    }


}