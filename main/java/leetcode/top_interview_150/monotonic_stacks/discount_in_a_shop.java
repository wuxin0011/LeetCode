package leetcode.top_interview_150.monotonic_stacks;

/**
 * @author: wuxin0011
 * @Description:
 */
public class discount_in_a_shop {

    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        int[] sk = new int[n];
        int size = 0;
        for(int i = n-1;i>=0;i--){
            while(size>0&&prices[i] < prices[sk[size-1]]){
                size--;
            }
            ans[i] = size == 0 ? prices[i] : prices[i] - prices[sk[size-1]];
            sk[size++] = i;
        }
        return ans;
    }


    /**
     * 直接暴力枚举
     * @param prices
     * @return
     */
    public int[] finalPricesII(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        for(int i = 0;i<n;i++){
        }
        return ans;
    }
}
