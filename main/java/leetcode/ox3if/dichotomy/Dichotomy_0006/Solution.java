package leetcode.ox3if.dichotomy.Dichotomy_0006;

import code_generation.utils.IoUtil;

/**
 * 704. 二分查找
 * <p>
 * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
 * <p>
 * 示例 1:输入: nums = [-1,0,3,5,9,12], target = 9输出: 4解释: 9 出现在 nums 中并且下标为 4
 * <p>
 * 示例2:输入: nums = [-1,0,3,5,9,12], target = 2输出: -1解释: 2 不存在 nums 中因此返回 -1
 * <p>
 * 提示：
 * <p>
 * <p>
 * 你可以假设 nums中的所有元素是不重复的。
 * n将在[1, 10000]之间。
 * nums的每个元素都将在[-9999, 9999]之间。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/binary-search
 * @title: binary-search
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "search", "in.txt");
    }


    public int search(int[] nums, int target) {
        int v = lowerBound(nums, target);
        if (v < 0 || v >= nums.length || nums[v] != target) {
            return -1;
        }
        return v;
    }


    public static int lowerBound(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = -1, r = arr.length;
        while (l + 1 < r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }


}