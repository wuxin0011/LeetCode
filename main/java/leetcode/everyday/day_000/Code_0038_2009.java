package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 2009. 使数组连续的最少操作数
 * 给你一个整数数组nums。每一次操作中，你可以将nums中任意一个元素替换成 任意整数。如果nums满足以下条件，那么它是 连续的：nums中所有元素都是 互不相同的。nums中 最大元素与最小元素的差等于nums.length - 1。比方说，nums = [4, 2, 5, 3]是 连续的，但是nums = [1, 2, 3, 5, 6] 不是连续的。请你返回使 nums连续的 最少操作次数。
 * 示例 1：输入：nums = [4,2,5,3]输出：0解释：nums 已经是连续的了。
 * 示例 2：输入：nums = [1,2,3,5,6]输出：1解释：一个可能的解是将最后一个元素变为 4 。结果数组为 [1,2,3,5,4] ，是连续数组。
 * 示例 3：输入：nums = [1,10,100,1000]输出：3解释：一个可能的解是：- 将第二个元素变为 2 。- 将第三个元素变为 3 。- 将第四个元素变为 4 。结果数组为 [1,2,3,4] ，是连续数组。
 * 提示：
 * 	1 <= nums.length <= 105
 * 	1 <= nums[i] <= 109
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/minimum-number-of-operations-to-make-array-continuous
 * @title: 使数组连续的最少操作数
 */
public class Code_0038_2009 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0038_2009.class,"minOperations","txt_file\\Code_0038_2009.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public int minOperations(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int m = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[m++] = nums[i]; // 原地去重
            }
        }
        int ans = 0;
        int r = 0,l = 0;
        // 只考虑没有重复的元素 重复的会自动填充有序数组
        while(r<m){

            // 按照题目要求
            // 在连续相等区间内
            // [l,r]
            while (nums[l] < nums[r] - n + 1) {
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }
        return n - ans;
	}

  

}