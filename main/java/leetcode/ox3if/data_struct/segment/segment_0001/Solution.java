package leetcode.ox3if.data_struct.segment.segment_0001;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 *
 * 2407. 最长递增子序列 II
 *
 * 给你一个整数数组nums和一个整数k。
 * 找到nums中满足以下要求的最长子序列：
 * 	子序列 严格递增
 * 	子序列中相邻元素的差值 不超过k。
 * 请你返回满足上述要求的 最长子序列的长度。
 * 子序列是从一个数组中删除部分元素后，剩余元素不改变顺序得到的数组。
 *
 * 示例 1：
 * 输入：nums = [4,2,1,4,3,4,5,8,15], k = 3
 * 输出：5
 * 解释：
 * 满足要求的最长子序列是 [1,3,4,5,8] 。
 * 子序列长度为 5 ，所以我们返回 5 。
 * 注意子序列 [1,3,4,5,8,15] 不满足要求，因为 15 - 8 = 7 大于 3 。
 *
 * 示例 2：
 * 输入：nums = [7,4,5,1,8,12,4,7], k = 5
 * 输出：4
 * 解释：
 * 满足要求的最长子序列是 [4,5,8,12] 。
 * 子序列长度为 4 ，所以我们返回 4 。
 *
 * 示例 3：
 * 输入：nums = [1,5], k = 1
 * 输出：1
 * 解释：
 * 满足要求的最长子序列是 [1] 。
 * 子序列长度为 1 ，所以我们返回 1 。
 *
 * 提示：
 * 	1 <= nums.length <= 10^5
 * 	1 <= nums[i], k <= 10^5
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/longest-increasing-subsequence-ii
 * @title: longest-increasing-subsequence-ii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"lengthOfLIS","in.txt");
    }
     

    public int lengthOfLIS(int[] nums, int k) {    

        return 0; 
	}

  

}