package leetcode._0x3f_.dichotomy.Dichotomy_0009;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 *
 * 2563. 统计公平数对的数目
 *
 * 给你一个下标从 0 开始、长度为 n 的整数数组nums，和两个整数lower 和upper ，返回 公平数对的数目 。如果(i, j)数对满足以下情况，则认为它是一个 公平数对：0 <= i < j < n，且lower <= nums[i] + nums[j] <= upper
 *
 * 示例 1：输入：nums = [0,1,7,4,4,5], lower = 3, upper = 6输出：6解释：共计 6 个公平数对：(0,3)、(0,4)、(0,5)、(1,3)、(1,4) 和 (1,5) 。
 *
 * 示例 2：输入：nums = [1,7,9,2,5], lower = 11, upper = 11输出：1解释：只有单个公平数对：(2,9) 。
 *
 * 提示：
 *
 *
 * 	1 <= nums.length <= 105
 * 	nums.length == n
 * 	-109<= nums[i] <= 109
 * 	-109<= lower <= upper <= 109
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/count-the-number-of-fair-pairs
 * @title: count-the-number-of-fair-pairs
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"countFairPairs","in.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public long countFairPairs(int[] nums, int lower, int upper) {
        if(nums == null || nums.length < 2){
            return 0;
        }
        long ans = 0;
        Arrays.sort(nums);
        int n = nums.length;
        if(nums[0]+nums[1] > upper || nums[n-1]+nums[n-2]<lower){
            return 0;
        }
        for (int j = 0; j < n; ++j) {
            int r = lowerBound(nums, j, upper - nums[j] + 1);
            int l = lowerBound(nums, j, lower - nums[j]);
            ans += r - l;
        }
        return ans;
    }

    private static int lowerBound(int[] nums, int right, int target) {
        int left = -1; // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = (left + right) >>> 1;
            if (nums[mid] < target)
                left = mid; // 范围缩小到 (mid, right)
            else
                right = mid; // 范围缩小到 (left, mid)
        }
        return right;
    }

}