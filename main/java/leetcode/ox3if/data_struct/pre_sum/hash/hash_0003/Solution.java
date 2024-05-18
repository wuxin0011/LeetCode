package leetcode.ox3if.data_struct.pre_sum.hash.hash_0003;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 974. 和可被 K 整除的子数组
 *
 * 给定一个整数数组 nums和一个整数 k ，返回其中元素之和可被 k整除的（连续、非空） 子数组 的数目。
 * 子数组 是数组的 连续 部分。
 *
 * 示例 1：
 * 输入：nums = [4,5,0,-2,-3,1], k = 5
 * 输出：7
 * 解释：
 * 有 7 个子数组满足其元素之和可被 k = 5 整除：
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 * 示例 2:
 * 输入: nums = [5], k = 9
 * 输出: 0
 *
 * 提示:
 * 	1 <= nums.length <= 3 * 10^4
 * 	-10^4<= nums[i] <= 10^4
 * 	2 <= k <= 10^4
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/subarray-sums-divisible-by-k
 * @title: subarray-sums-divisible-by-k
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"subarraysDivByK","in.txt");
    }
     

    public int subarraysDivByK(int[] nums, int k) {
        int cnt = 0;
        int tot = 0;
        for (int num : nums) {
            tot += num;
            int a = tot % k == 0 ? 1 : 0;

        }
        return cnt;
    }

  

}