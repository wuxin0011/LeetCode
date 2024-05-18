package leetcode.ox3if.data_struct.pre_sum.hash.hash_0000;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * 930. 和相同的二元子数组
 *
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
 * 子数组 是数组的一段连续部分。
 *
 * 示例 1：
 * 输入：nums = [1,0,1,0,1], goal = 2
 * 输出：4
 * 解释：
 * 有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
 *
 * 示例 2：
 * 输入：nums = [0,0,0,0,0], goal = 0
 * 输出：15
 *
 * 提示：
 * 	1 <= nums.length <= 3 * 10^4
 * 	nums[i] 不是 0 就是 1
 * 	0 <= goal <= nums.length
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/binary-subarrays-with-sum
 * @title: 和相同的二元子数组
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "numSubarraysWithSum", "in.txt");
    }


    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        int sum = 0;
        for (int num : nums) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            sum += num;
            cnt += map.getOrDefault(sum - goal, 0);
        }
        return cnt;
    }


}