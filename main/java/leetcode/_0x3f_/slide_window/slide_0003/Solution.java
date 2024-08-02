package leetcode._0x3f_.slide_window.slide_0003;

import code_generation.utils.IoUtil;

/**
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * 任何误差小于 10-5 的答案都将被视为正确答案。
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/maximum-average-subarray-i
 * @title: maximum-average-subarray-i
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"findMaxAverage","in.txt");
    }
     

    public double findMaxAverage(int[] nums, int k) {
        double sum = 0;
        double ans = -1000000000;
        for(int i = 0;i<nums.length;i++){
            if(i>=k){
                sum -= nums[i-k];
            }
            sum += nums[i];
            if(i>=k-1){
                ans = Math.max(ans,sum/k);
            }
        }
        return ans;
	}

  

}