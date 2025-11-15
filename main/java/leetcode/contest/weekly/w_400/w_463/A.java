package leetcode.contest.weekly.w_400.w_463;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-463/problems/best-time-to-buy-and-sell-stock-using-strategy">按策略买卖股票的最佳时机</a>
 * @title: 按策略买卖股票的最佳时机
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"maxProfit","A.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long ans = 0;
        long[] pre = new long[n + 1];
        long[] suf = new long[n + 1];
        long[] sums = new long[n + 1];

        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + 1L * prices[i] * strategy[i];
        }
        for (int i = n - 1;i >= 0; i--) {
            suf[i] = suf[i + 1] + 1L * prices[i] * strategy[i];
        }
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + prices[i];
        }

        ans = pre[n];
        for (int i = 0; i < n; i++) {
            if(i+k>n)break;
            ans=Math.max(ans,pre[i] + sums[i + k] - sums[i + k / 2] + suf[i + k]);
        }
        return ans;
    }

  

}