package leetcode._0x3f_.slide_window.slide_0024;

import code_generation.utils.IoUtil;

/**
 *
 * 1004. 最大连续1的个数 III
 *
 * 给定一个二进制数组nums和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
 *
 * 示例 1：输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2输出：6解释：[1,1,1,0,0,1,1,1,1,1,1]粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 *
 * 示例 2：输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3输出：10解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 *
 * 提示：
 *
 *
 * 	1 <= nums.length <= 105
 * 	nums[i]不是0就是1
 * 	0 <= k <= nums.length
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/max-consecutive-ones-iii
 * @title: max-consecutive-ones-iii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"longestOnes","in.txt");
    }
     

    public int longestOnes(int[] nums, int k) {

        int ans = 0;
        int n = nums.length,r = 0,l  = 0;
        while(r<n){
            if(nums[r]==0)k--;
            while(k<0){
                if(nums[l]==0)k++;
                l++;
            }
            ans = Math.max(ans,r - l + 1);
            r++;
        }

        return ans;
	}

  

}