package leetcode.ox3if.data_struct.pre_sum.hash.hash_0001;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为 K 的子数组
 * <p>
 * 给你一个整数数组 nums 和一个整数k ，请你统计并返回 该数组中和为k的子数组的个数。
 * 子数组是数组中元素的连续非空序列。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * <p>
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/subarray-sum-equals-k
 * @title: subarray-sum-equals-k
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "subarraySum", "in.txt");
    }


    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            sum += num;
            cnt += map.getOrDefault(sum - k, 0);
        }
        return cnt;
    }


}