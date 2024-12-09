package leetcode.contest.weekly.w_400.w_427.c;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-427/problems/maximum-subarray-sum-with-length-divisible-by-k">长度可被 K 整除的子数组的最大元素和</a>
 * @title: 长度可被 K 整除的子数组的最大元素和
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "maxSubarraySum", "C.txt");
    }


    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] max = new long[k];
        Arrays.fill(max,Long.MAX_VALUE >> 1);
        max[0] = 0;
        long sums = 0,ans = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            sums += nums[i];
            if(i >= k - 1 ) {
                ans = Math.max(ans,sums - max[(i + 1) % k]);
            }
            max[(i + 1) % k] = Math.min(sums,max[(i + 1) % k]);
        }
        return ans;
    }

}