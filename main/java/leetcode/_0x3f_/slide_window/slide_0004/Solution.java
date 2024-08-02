package leetcode._0x3f_.slide_window.slide_0004;

import code_generation.utils.IoUtil;

/**
 * 1343. 大小为 K 且平均值大于等于阈值的子数组数目
 *
 * 给你一个整数数组arr和两个整数 k和 threshold。请你返回长度为 k且平均值大于等于threshold的子数组数目。
 *
 * 示例 1：输入：arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4输出：3解释：子数组 [2,5,5],[5,5,5] 和 [5,5,8] 的平均值分别为 4，5 和 6 。其他长度为 3 的子数组的平均值都小于 4 （threshold 的值)。
 *
 * 示例 2：输入：arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5输出：6解释：前 6 个长度为 3 的子数组平均值都大于 5 。注意平均值不是整数。
 * 提示：
 *
 * 	1 <= arr.length <= 105
 * 	1 <= arr[i] <= 104
 * 	1 <= k <= arr.length
 * 	0 <= threshold <= 104
 *
 *
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