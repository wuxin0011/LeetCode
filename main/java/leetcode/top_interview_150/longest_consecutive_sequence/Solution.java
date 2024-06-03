package leetcode.top_interview_150.longest_consecutive_sequence;

import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/longest-consecutive-sequence
 * @title: 最长连续序列
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "longestConsecutive1", "in.txt");
    }


    public int longestConsecutive1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int size = set.size();
        int i = 0;
        for (Integer val : set) {
            nums[i++] = val;
        }
        Arrays.sort(nums, 0, size);
        i = 0;
        int ans = 0;
        while (i < size) {
            int l = 1;
            while (i > 0 && i < size && nums[i] == nums[i - 1] + 1) {
                l++;
                i++;
            }
            ans = Math.max(ans, l);
            i++;
        }
        return ans;
    }

}