package leetcode.contest.weekly.w_300.w_388.d;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/maximum-strength-of-k-disjoint-subarrays/
 * @title
 */
public class D {
    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"maximumStrength","in.txt");
    }

    public long maximumStrength(int[] nums, int k) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }
        long[][] f = new long[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            f[i][i - 1] = Long.MIN_VALUE;
            long mx = Long.MIN_VALUE;
            int w = (k - i + 1) * (i % 2 > 0 ? 1 : -1);
            for (int j = i; j <= n - k + i; j++) {
                mx = Math.max(mx, f[i - 1][j - 1] - s[j - 1] * w);
                f[i][j] = Math.max(f[i][j - 1], s[j] * w + mx);
            }
        }
        return f[k][n];

    }
}
