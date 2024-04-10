package leetcode.ox3if.dichotomy.Dichotomy_0004;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 *
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。如果数组中不存在目标值 target，返回[-1, -1]。你必须设计并实现时间复杂度为O(log n)的算法解决此问题。
 *
 * 示例 1：输入：nums = [5,7,7,8,8,10], target = 8输出：[3,4]
 *
 * 示例2：输入：nums = [5,7,7,8,8,10], target = 6输出：[-1,-1]
 *
 * 示例 3：输入：nums = [], target = 0输出：[-1,-1]
 *
 * 提示：
 *
 *
 * 	0 <= nums.length <= 105
 * 	-109<= nums[i]<= 109
 * 	nums是一个非递减数组
 * 	-109<= target<= 109
 *
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
 * @title: find-first-and-last-position-of-element-in-sorted-array
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"searchRange","in.txt");
    }
    private static final int MOD = (int)1e9 + 7;

    static final int[] NOT = new int[]{-1,-1};

    public int[] searchRange(int[] nums, int target) {

        int l = lowerBound2(nums,target);
        if(l < 0 || l >= nums.length || nums[l] != target){
            return NOT;
        }
        int r = lowerBound2(nums,target+1)-1;
        return new int[]{l,r};
	}

    public static int lowerBound(int[] arr,int target){
        if(arr == null || arr.length == 0){
            return -1;
        }
        int r = arr.length - 1,l = 0;
        while(l<=r){
            int mid = l + ((r - l) >>1);
            if(arr[mid]>=target){
                r = mid  - 1;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }

    public static int lowerBound1(int[] arr,int target){
        if(arr == null || arr.length == 0){
            return -1;
        }
        int r = arr.length,l = 0;
        while(l<r){
            int mid = l + ((r - l) >>1);
            if(arr[mid]>=target){
                r = mid ;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }

    public static int lowerBound2(int[] arr,int target){
        if(arr == null || arr.length == 0){
            return -1;
        }
        int r = arr.length ,l = -1;
        while(l + 1 < r){
            int mid = l + ((r - l) >>1);
            if(arr[mid]>=target){
                r = mid;
            }else{
                l = mid ;
            }
        }
        return r;
    }

  

}