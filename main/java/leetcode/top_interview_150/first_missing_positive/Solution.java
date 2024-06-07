package leetcode.top_interview_150.first_missing_positive;

import code_generation.utils.IoUtil;

/**
 * 41. 缺失的第一个正数
 * <p>
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,0]
 * 输出：3
 * 解释：范围 [1,2] 中的数字都在数组中。
 * <p>
 * 示例 2：
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 解释：1 在数组中，但 2 没有。
 * <p>
 * 示例 3：
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * 解释：最小的正数 1 没有出现。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 231 - 1
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/first-missing-positive
 * @title: 缺失的第一个正数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "firstMissingPositive", "in.txt");
    }


    // 这题如果第一次做还是比较难想的
    // 提示：
    // 1、题目要求找到是正数 根据示例看0不在其中
    // 2、直接排除负数
    // 3、超过 数组长应该变为 1
    // 4、原地 hash 想办法打标记
    public int firstMissingPositive(int[] nums) {

        // 是否出现1
        boolean hasOne = false;

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                hasOne = true;
            }
            // 根据题目意思 0 或者小于 或者 大于 n 都会计入其中
            if ( nums[i] > n || nums[i] <= 0) {
                nums[i] = 1;
            }
        }
        if (!hasOne) {
            return 1;
        }

        // 添加负号是为了打标记
        // 可以认为是原地hash

        for (int i = 0; i < n; i++) {
            int id = Math.abs(nums[i]) - 1;
            nums[id] = -Math.abs(nums[id]);
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                // 上面id - 1
                return i + 1;
            }
        }
        // 为什么需要 n + 1
        // [1,2,3,4] 长度为 4 根据题目意思应该返回5
        return n + 1;
    }


}