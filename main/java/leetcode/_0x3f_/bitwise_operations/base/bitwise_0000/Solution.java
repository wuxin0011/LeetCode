package leetcode._0x3f_.bitwise_operations.base.bitwise_0000;

import code_generation.utils.IoUtil;

/**
 *
 *
 * 136. 只出现一次的数字
 *
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 *
 * 示例 1 ：输入：nums = [2,2,1]输出：1
 *
 * 示例 2 ：输入：nums = [4,1,2,1,2]输出：4
 *
 * 示例 3 ：输入：nums = [1]输出：1
 *
 * 提示：
 *
 *
 * 	1 <= nums.length <= 3 * 104
 * 	-3 * 104 <= nums[i] <= 3 * 104
 * 	除了某个元素只出现一次以外，其余每个元素均出现两次。
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/single-number
 * @title: single-number
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"singleNumber","in.txt");
    }


    // 使用简单异或运算就能得到
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }

}