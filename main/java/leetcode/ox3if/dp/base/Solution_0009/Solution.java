package leetcode.ox3if.dp.base.Solution_0009;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 *
 * 53. 最大子数组和
 *
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 *
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组[4,-1,2,1] 的和最大，为6 。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *
 * 提示：
 * 	1 <= nums.length <= 10^5
 * 	-10^4 <= nums[i] <= 10^4
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 *
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
        int mx = Integer.MIN_VALUE;
        int tot = 0;
        for (int num : nums) {
            tot += num;
            mx = Math.max(tot,mx);
            if(tot <=0){
                tot = 0;
            }
        }
        return mx;
    }

  

}