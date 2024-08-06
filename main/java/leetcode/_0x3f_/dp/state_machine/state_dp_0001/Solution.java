package leetcode._0x3f_.dp.state_machine.state_dp_0001;

import code_generation.utils.IoUtil;

/**
 *
 * 122. 买卖股票的最佳时机 II
 *
 * 给你一个整数数组 prices ，其中prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润。
 *
 * 示例 1：
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3。
 * 最大总利润为 4 + 3 = 7 。
 *
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
 * 最大总利润为 4 。
 *
 * 示例3：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0。
 *
 * 提示：
 * 	1 <= prices.length <= 3 * 10^4
 * 	0 <= prices[i] <= 10^4
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii
 * @title: best-time-to-buy-and-sell-stock-ii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"maxProfit","in.txt");
    }
     

    public int maxProfit(int[] prices) {
        int ans = 0;
        // 由于可以卖出再买入 因此只需要判断后面一天>前一天就行
        for (int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i-1]) {
                ans += prices[i] - prices[i-1];
            }
        }
        return ans;
    }

  

}