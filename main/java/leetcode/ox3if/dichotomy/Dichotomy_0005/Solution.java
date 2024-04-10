package leetcode.ox3if.dichotomy.Dichotomy_0005;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 35. 搜索插入位置
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。请必须使用时间复杂度为 O(log n) 的算法。
 *
 * 示例 1:输入: nums = [1,3,5,6], target = 5输出: 2
 *
 * 示例2:输入: nums = [1,3,5,6], target = 2输出: 1
 *
 * 示例 3:输入: nums = [1,3,5,6], target = 7输出: 4
 *
 * 提示:
 *
 *
 * 	1 <= nums.length <= 104
 * 	-104 <= nums[i] <= 104
 * 	nums 为无重复元素的升序排列数组
 * 	-104 <= target <= 104
 *
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/search-insert-position
 * @title: search-insert-position
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"searchInsert","in.txt");
    }


    public int searchInsert(int[] nums, int target) {
        int r = nums.length,l = 0;
//        if(target>nums[r]){
//            return nums.length;
//        }else if(target == nums[r]){
//            return nums.length - 1;
//        }else if(target <= nums[l]){
//            return l;
//        }
        while( l< r ) {
            int mid = l + ((r - l)>>1);
            if(nums[mid]==target){
                return mid;
            }
            if(target > nums[mid]){
                l = mid + 1;
            }else{
                r = mid;
            }
        }

        return l;
	}


  

}