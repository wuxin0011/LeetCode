package leetcode._0x3f_.data_struct.pre_sum.base.Solution_0006;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/maximum-subarray
 * @title: 最大子数组和
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"maxSubArray","in.txt");
    }
     

    public int maxSubArray(int[] nums) {

        int ans = Integer.MIN_VALUE;
        int cnt = 0;
        for(int num : nums) {
            cnt += num;
            ans = Math.max(ans,cnt);
            if(cnt<0) cnt = 0;
        }
        return ans;
	}

  

}