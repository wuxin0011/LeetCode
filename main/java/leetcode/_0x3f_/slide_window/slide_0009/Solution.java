package leetcode._0x3f_.slide_window.slide_0009;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * 2461. 长度为 K 子数组中的最大和
 *
 * 给你一个整数数组 nums 和一个整数 k 。请你从 nums 中满足下述条件的全部子数组中找出最大子数组和：子数组的长度是 k，且子数组中的所有元素 各不相同 。返回满足题面要求的最大子数组和。如果不存在子数组满足这些条件，返回 0 。子数组 是数组中一段连续非空的元素序列。
 *
 * 示例 1：输入：nums = [1,5,4,2,9,9,9], k = 3输出：15解释：nums 中长度为 3 的子数组是：- [1,5,4] 满足全部条件，和为 10 。- [5,4,2] 满足全部条件，和为 11 。- [4,2,9] 满足全部条件，和为 15 。- [2,9,9] 不满足全部条件，因为元素 9 出现重复。- [9,9,9] 不满足全部条件，因为元素 9 出现重复。因为 15 是满足全部条件的所有子数组中的最大子数组和，所以返回 15 。
 *
 * 示例 2：输入：nums = [4,4,4], k = 3输出：0解释：nums 中长度为 3 的子数组是：- [4,4,4] 不满足全部条件，因为元素 4 出现重复。因为不存在满足全部条件的子数组，所以返回 0 。
 *
 * 提示：
 *
 *
 * 	1 <= k <= nums.length <= 105
 * 	1 <= nums[i] <= 105
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/maximum-sum-of-distinct-subarrays-with-length-k
 * @title: maximum-sum-of-distinct-subarrays-with-length-k
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"maximumSubarraySum","in.txt");
    }
     

    public long maximumSubarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        long ans = 0,sum = 0;
        int cnt = 0,key = 0,val = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i>=k){
                key = nums[i-k];
                val = map.get(key)-1;
                if(val==0)cnt--;
                sum -= key;
                map.put(key,val);
            }
            key = nums[i];
            sum += key;
            val = map.getOrDefault(key,0)+1;
            if(val==1)cnt++;
            map.put(key,val);
            if(i>=k-1 && cnt == k && sum > ans ){
                ans = sum;
            }
        }
        return ans;
    }

  

}