package leetcode.contest.weekly.w_400.w_410.c;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-410/problems/find-the-count-of-monotonic-pairs-i
 * @title: 单调数组对的数目 I
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "countOfPairs", "C.txt");
    }


    static int MOD = (int) 1e9 + 7;

    public int countOfPairs(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        memo = new long[n][1001];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        long x = 0;
        for (int k = 0; k <= nums[n - 1]; k++) {
            x = (x + dfs(n - 1, k)) % MOD;
        }
        return (int) (x);
    }

    long[][] memo;
    int[] nums;

    public long dfs(int i, int j) {
        if (i == 0) {
            return 1;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        long res = 0;
        int maxK = min(nums[i - 1], nums[i - 1] - nums[i] + j, j);
        for (int k = 0; k <= maxK; k++) {
            res = (res + dfs(i - 1, k)) % MOD;
        }
        return memo[i][j] = res;
    }


    static int min(int... args) {
        int val = 0x7fffff;
        for (int x : args) {
            if (val > x) {
                val = x;
            }
        }
        return val;
    }

    // nums[i] = a1[i] + a2[i]
    // a1[i-1] >= a1[i]
    // a2[i-1] <= a2[i]
    // i,k
    // 0 <= a[i-1] <= k
    // a2[i-1] <= nums[i] - k
    // k = min(a1[i-1],a2[i-1])



}