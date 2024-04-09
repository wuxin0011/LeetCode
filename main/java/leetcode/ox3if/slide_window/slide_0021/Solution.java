package leetcode.ox3if.slide_window.slide_0021;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 *1695. 删除子数组的最大得分
 *
 * 给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分 就是子数组各元素之 和 。返回 只删除一个 子数组可获得的 最大得分 。如果数组 b 是数组 a 的一个连续子序列，即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是a 的一个子数组。
 *
 * 示例 1：输入：nums = [4,2,4,5,6]输出：17解释：最优子数组是 [2,4,5,6]
 *
 * 示例 2：输入：nums = [5,2,1,2,5,2,1,2,5]输出：8解释：最优子数组是 [5,2,1] 或 [1,2,5]
 *
 * 提示：
 *
 *
 * 	1 <= nums.length <= 105
 * 	1 <= nums[i] <= 104
 *
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/maximum-erasure-value
 * @title: maximum-erasure-value
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"maximumUniqueSubarray","in.txt");
    }
     

    public int maximumUniqueSubarray(int[] nums) {

        long sum = 0,ans = 0;
        int n = nums.length;
        int r = 0, l = 0;
        Map<Integer,Integer> map = new HashMap<>();
        while(r<n){
            int cnt = map.getOrDefault(nums[r],0)+1;
            map.put(nums[r],cnt);
            sum += nums[r];
            while(map.get(nums[r])>1){
                sum -= nums[l];
                int v = map.get(nums[l])-1;
                map.put(nums[l],v);
                l++;
            }
            ans = Math.max(ans,sum);
            r++;
        }

        return (int)ans;
	}

  

}