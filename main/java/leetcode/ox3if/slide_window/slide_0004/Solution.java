package leetcode.ox3if.slide_window.slide_0004;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold
 * @title: number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "numOfSubarrays", "in.txt");
    }


    public int numOfSubarrays(int[] arr, int k, int threshold) {
        long tot = 0;
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i >= k) {
                tot -= arr[i - k];
            }
            tot += arr[i];
            if (i >= k - 1 && tot >= threshold * 1L * k) {
                cnt++;
            }
        }

        return cnt;
    }


}