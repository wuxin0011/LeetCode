package leetcode._0x3f_.data_struct.tree_array.Solution_0002;

import code_generation.utils.IoUtil;

/**
 *
 * 1649. 通过指令创建有序数组
 *
 * 给你一个整数数组instructions，你需要根据instructions中的元素创建一个有序数组。
 * 一开始你有一个空的数组nums，你需要从左到右遍历instructions中的元素，将它们依次插入nums数组中。每一次插入操作的代价是以下两者的 较小值：
 * 	nums中 严格小于instructions[i]的数字数目。
 * 	nums中 严格大于instructions[i]的数字数目。
 * 比方说，如果要将3 插入到nums = [1,2,3,5]，那么插入操作的代价为min(2, 1) (元素1和2小于3，元素5大于3），插入后nums 变成[1,2,3,3,5]。
 * 请你返回将instructions中所有元素依次插入nums后的 总最小代价。由于答案会很大，请将它对10^9 + 7取余后返回。
 *
 * 示例 1：
 * 输入：instructions = [1,5,6,2]
 * 输出：1
 * 解释：一开始 nums = [] 。
 * 插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1] 。
 * 插入 5 ，代价为 min(1, 0) = 0 ，现在 nums = [1,5] 。
 * 插入 6 ，代价为 min(2, 0) = 0 ，现在 nums = [1,5,6] 。
 * 插入 2 ，代价为 min(1, 2) = 1 ，现在 nums = [1,2,5,6] 。
 * 总代价为 0 + 0 + 0 + 1 = 1 。
 *
 * 示例 2:
 * 输入：instructions = [1,2,3,6,5,4]
 * 输出：3
 * 解释：一开始 nums = [] 。
 * 插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1] 。
 * 插入 2 ，代价为 min(1, 0) = 0 ，现在 nums = [1,2] 。
 * 插入 3 ，代价为 min(2, 0) = 0 ，现在 nums = [1,2,3] 。
 * 插入 6 ，代价为 min(3, 0) = 0 ，现在 nums = [1,2,3,6] 。
 * 插入 5 ，代价为 min(3, 1) = 1 ，现在 nums = [1,2,3,5,6] 。
 * 插入 4 ，代价为 min(3, 2) = 2 ，现在 nums = [1,2,3,4,5,6] 。
 * 总代价为 0 + 0 + 0 + 0 + 1 + 2 = 3 。
 *
 * 示例 3：
 * 输入：instructions = [1,3,3,3,2,4,2,1,2]
 * 输出：4
 * 解释：一开始 nums = [] 。
 * 插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1] 。
 * 插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3] 。
 * 插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3,3] 。
 * 插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3,3,3] 。
 * 插入 2 ，代价为 min(1, 3) = 1 ，现在 nums = [1,2,3,3,3] 。
 * 插入 4 ，代价为 min(5, 0) = 0 ，现在 nums = [1,2,3,3,3,4] 。
 *  2 ，代价为 min(1, 4) = 1 ，现在 nums = [1,2,2,3,3,3,4] 。
 * 插入 1 ，代价为 min(0, 6) = 0 ，现在 nums = [1,1,2,2,3,3,3,4] 。
 * 插入 2 ，代价为 min(2, 4) = 2 ，现在 nums = [1,1,2,2,2,3,3,3,4] 。
 * 总代价为 0 + 0 + 0 + 0 + 1 + 0 + 1 + 0 + 2 = 4 。
 *
 * 提示：
 * 	1 <= instructions.length <= 10^5
 * 	1 <= instructions[i] <= 10^5
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/create-sorted-array-through-instructions
 * @title: 通过指令创建有序数组
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"createSortedArray","in.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public int createSortedArray(int[] instructions) {    

        return 0; 
	}

  

}