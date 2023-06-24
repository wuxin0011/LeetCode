package com.wuxin.maximum;

/**
 * @author: wuxin0011
 * @Description: 买股票 本题其实是最小值与至最大值为题 其中最小值不能在最大值之后
 * @see https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 * @test TODO 本题测试结果超时！！！ 但是测试结果是正确的哈哈哈哈哈！！！
 */
public class MaxProfit {

    public static void main(String[] args) {
        int[] ints = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(new MaxProfit().maxProfit(ints));
    }

    public int maxProfit(int[] prices) {

        int max = 0; // 卖出最低价格为 0
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int result = prices[j] - prices[i];
                if (result > max) {
                    max = result;
                }
            }
        }
        return max;
    }
}
