package leetcode.ox3if.dichotomy.Dichotomy_0007;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 *
 * 2529. 正整数和负整数的最大计数
 *
 * 给你一个按 非递减顺序 排列的数组 nums ，返回正整数数目和负整数数目中的最大值。换句话讲，如果 nums 中正整数的数目是 pos ，而负整数的数目是 neg ，返回 pos 和 neg二者中的最大值。注意：0 既不是正整数也不是负整数。
 *
 * 示例 1：输入：nums = [-2,-1,-1,1,2,3]输出：3解释：共有 3 个正整数和 3 个负整数。计数得到的最大值是 3 。
 *
 * 示例 2：输入：nums = [-3,-2,-1,0,0,1,2]输出：3解释：共有 2 个正整数和 3 个负整数。计数得到的最大值是 3 。
 *
 * 示例 3：输入：nums = [5,20,66,1314]输出：4解释：共有 4 个正整数和 0 个负整数。计数得到的最大值是 4 。
 *
 * 提示：
 *
 *
 * 	1 <= nums.length <= 2000
 * 	-2000 <= nums[i] <= 2000
 * 	nums 按 非递减顺序 排列。
 *
 *
 *
 *
 * 进阶：你可以设计并实现时间复杂度为 O(log(n)) 的算法解决此问题吗？
 *
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/maximum-count-of-positive-integer-and-negative-integer
 * @title: maximum-count-of-positive-integer-and-negative-integer
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"maximumCount","in.txt");
    }
     

    public int maximumCount(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        // 第一个0出现的位置
        int l = lowerBound(nums,0);
        if( l < 0 || l >= n || nums[l] != 0){
            // 没找到 ？
            return Math.max(l,n - l);
        }else{
            // 最后一个0出现的位置
            int r = lowerBound(nums, 1)-1;

            return Math.max(l,n - r-1); // n - (r+1) => r+ 1才是第一个正数出现的位置
        }
    }

    public static int lowerBound(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = -1, r = arr.length;
        while (l + 1 < r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] >= target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }




}