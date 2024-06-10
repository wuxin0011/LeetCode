package leetcode.ox3if.dp.digital.digital_dp_0009;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description: 二分 + 数位DP
 * @url: https://leetcode.cn/problems/maximum-number-that-sum-of-the-prices-is-less-than-or-equal-to-k
 * @title: maximum-number-that-sum-of-the-prices-is-less-than-or-equal-to-k
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "findMaximumNumber", "in.txt");
    }


    public long findMaximumNumber(long k, int x) {
        this.x = x;
        this.k = k;
        long l = 0, r = (k + 1) * (1L << x);
        while (l + 1 < r) {
            long mid = l + ((r - l) >> 1);
            if (check(mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public boolean check(long num) {
        this.num = num;
        // Long.numberOfLeadingZeros 返回零位以下最低阶（“最右边的”）的数量在指定的二进制补码表示的一个位long值。 如果指定的值在其二进制补码表示中没有一位，换句话说如果它等于零则返回64。
        int m = 64 - Long.numberOfLeadingZeros(num);
        dp = new long[m][m + 1];
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }
        // 从右往左填
        return dfs(m - 1, 0, true) <= k;

    }

    long[][] dp;

    int x;
    long k;
    long num;

    public long dfs(int i, int cnt, boolean isLimit) {
        if (i < 0) {
            return cnt;
        }
        if (!isLimit && dp[i][cnt] != -1) {
            return dp[i][cnt];
        }
        long res = 0;

        int hi = isLimit ? (int) (num >> i & 1) : 1;
        for (int d = 0; d <= hi; d++) {
            res += dfs(i - 1, cnt + ((d == 1 && (i + 1) % x == 0) ? 1 : 0), isLimit && hi == d);
        }
        if (!isLimit) {
            dp[i][cnt] = res;
        }
        return res;
    }


}