package leetcode.top_interview_150.move_zeroes;

import code_generation.utils.IoUtil;

/**
 * 283. 移动零
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意，必须在不复制数组的情况下原地对数组进行操作。
 * <p>
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * <p>
 * 示例 2:
 * 输入: nums = [0]
 * 输出: [0]
 * <p>
 * 提示:
 * 1 <= nums.length <= 10^4
 * -2^31<= nums[i] <= 231- 1
 * 进阶：你能尽量减少完成的操作次数吗？
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/move-zeroes
 * @title: 移动零
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "moveZeroes", "in.txt");
        IoUtil.testUtil(Solution.class, "moveZeroes1", "in.txt");
    }


    // 不用栈
    public void moveZeroes(int[] nums) {
        int size = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[size++] = nums[i];
            }
        }
        for (int i = size; i < n && size > 0; i++) {
            nums[i] = 0;
        }

    }

    // 利用栈来模拟
    public void moveZeroes1(int[] nums) {
        int n = nums.length;
        int[] sk = new int[n];
        int size = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                sk[size++] = nums[i];
            }
        }
        for (int i = 0; i < n && size > 0; i++) {
            nums[i] = sk[i];
        }

    }



}