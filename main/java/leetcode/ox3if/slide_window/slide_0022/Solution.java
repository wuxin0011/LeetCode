package leetcode.ox3if.slide_window.slide_0022;

import code_generation.utils.IoUtil;
import java.util.*;
/**
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