package leetcode.top_interview_150.slide_window;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 长度最小的子数组
 * @url https://leetcode.cn/problems/minimum-size-subarray-sum/?envType=study-plan-v2&envId=top-interview-150
 */
public class minimum_size_subarray_sum {

    public static void main(String[] args) {
        IoUtil.testUtil(minimum_size_subarray_sum.class,"minSubArrayLen","./txt_file/minimum_size_subarray_sum.txt");
    }

    public int minSubArrayLen(int target, int[] nums) {
        if( nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length,r = 0,l=0;
        int mx = n+1,tot = 0;
        while( r < n){
            tot += nums[r];
            r++;
            while( tot >= target) {

                if( r - l < mx){
                    mx = r - l;
                }

                tot -= nums[l];
                l++;
            }
        }
        return mx == n+1 ? 0 : mx;
    }
}
