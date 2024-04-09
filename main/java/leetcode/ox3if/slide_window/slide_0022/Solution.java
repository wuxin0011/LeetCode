package leetcode.ox3if.slide_window.slide_0022;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 2958. 最多 K 个重复元素的最长子数组
 *
 * 给你一个整数数组nums和一个整数k。一个元素 x在数组中的 频率指的是它在数组中的出现次数。如果一个数组中所有元素的频率都 小于等于k，那么我们称这个数组是 好数组。请你返回 nums中 最长好子数组的长度。子数组 指的是一个数组中一段连续非空的元素序列。
 *
 * 示例 1：输入：nums = [1,2,3,1,2,3,1,2], k = 2输出：6解释：最长好子数组是 [1,2,3,1,2,3] ，值 1 ，2 和 3 在子数组中的频率都没有超过 k = 2 。[2,3,1,2,3,1] 和 [3,1,2,3,1,2] 也是好子数组。最长好子数组的长度为 6 。
 *
 * 示例 2：输入：nums = [1,2,1,2,1,2,1,2], k = 1输出：2解释：最长好子数组是 [1,2] ，值 1 和 2 在子数组中的频率都没有超过 k = 1 。[2,1] 也是好子数组。最长好子数组的长度为 2 。
 *
 * 示例 3：输入：nums = [5,5,5,5,5,5,5], k = 4输出：4解释：最长好子数组是 [5,5,5,5] ，值 5 在子数组中的频率没有超过 k = 4 。最长好子数组的长度为 4 。
 *
 * 提示：
 *
 *
 * 	1 <= nums.length <= 105
 * 	1 <= nums[i] <= 109
 * 	1 <= k <= nums.length
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/length-of-longest-subarray-with-at-most-k-frequency
 * @title: length-of-longest-subarray-with-at-most-k-frequency
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"maxSubarrayLength","in.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public int maxSubarrayLength(int[] nums, int k) {

        int ans = 0;
        int n = nums.length;
        int r = 0, l = 0;
        Map<Integer,Integer> map = new HashMap<>();
        while(r<n){
            int cnt = map.getOrDefault(nums[r],0)+1;
            map.put(nums[r],cnt);
            while(map.get(nums[r])>k){
                int v = map.get(nums[l])-1;
                map.put(nums[l],v);
                l++;
            }
            ans = Math.max(ans,r - l+1);
            r++;
        }
        return ans;
	}

  

}