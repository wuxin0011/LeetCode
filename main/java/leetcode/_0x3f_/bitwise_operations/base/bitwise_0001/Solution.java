package leetcode._0x3f_.bitwise_operations.base.bitwise_0001;

import code_generation.utils.IoUtil;

/**
 *
 *
 * 137. 只出现一次的数字 II
 *
 * 给你一个整数数组nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
 *
 * 示例 1：输入：nums = [2,2,3,2]输出：3
 *
 * 示例 2：输入：nums = [0,1,0,1,0,1,99]输出：99
 *
 * 提示：
 *
 *
 * 	1 <= nums.length <= 3 * 104
 * 	-231 <= nums[i] <= 231 - 1
 * 	nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/single-number-ii
 * @title: single-number-ii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"singleNumber","in.txt");
    }

    // 在所有三个数字择找到唯一出现一次的数字
    // 可以利用数位统计 然后取模
    public int singleNumber(int[] nums) {
        int[] h = new int[32];
        for (int num : nums) {
            for (int i = 0; i < h.length; i++) {
                // 向右移动检查
                h[i] += num>>i & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < h.length; i++) {
            if(h[i] % 3 != 0 ){
                ans |= 1 << i;
            }
        }
        return ans;
    }


    // 更一般的
    // 对于任意数组 其他数都出现 m 次，找出唯一数字
    public int singleNumberAny(int[] nums,int m) {
        int[] h = new int[32];
        for (int num : nums) {
            for (int i = 0; i < h.length; i++) {
                // 向右移动检查
                h[i] += (num>>i & 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < h.length; i++) {
            if(h[i] % m != 0 ){
                // 当前位置不为0
                ans |= 1 << i;
            }
        }
        return ans;
    }

}