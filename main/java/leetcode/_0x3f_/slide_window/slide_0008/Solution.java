package leetcode._0x3f_.slide_window.slide_0008;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.List;

/**
 * 2841. 几乎唯一子数组的最大和
 * <p>
 * 给你一个整数数组nums和两个正整数m和k。请你返回 nums中长度为 k的几乎唯一子数组的 最大和，如果不存在几乎唯一子数组，请你返回 0。
 * 如果 nums的一个子数组有至少 m个互不相同的元素，我们称它是 几乎唯一子数组。子数组指的是一个数组中一段连续 非空的元素序列。
 * <p>
 * 示例 1：输入：nums = [2,6,7,3,1,7], m = 3, k = 4输出：18解释：总共有 3 个长度为 k = 4 的几乎唯一子数组。分别为 [2, 6, 7, 3] ，[6, 7, 3, 1] 和 [7, 3, 1, 7] 。这些子数组中，和最大的是 [2, 6, 7, 3] ，和为 18 。
 * <p>
 * 示例 2：输入：nums = [5,9,9,2,4,5,4], m = 1, k = 3输出：23解释：总共有 5 个长度为 k = 3 的几乎唯一子数组。分别为 [5, 9, 9] ，[9, 9, 2] ，[9, 2, 4] ，[2, 4, 5] 和 [4, 5, 4] 。这些子数组中，和最大的是 [5, 9, 9] ，和为 23 。
 * <p>
 * 示例 3：输入：nums = [1,2,1,2,1,2,1], m = 3, k = 3输出：0解释：输入数组中不存在长度为 k = 3 的子数组含有至少  m = 3 个互不相同元素的子数组。所以不存在几乎唯一子数组，最大和为 0 。
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 2 * 104
 * 1 <= m <= k <= nums.length
 * 1 <= nums[i] <= 109
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-sum-of-almost-unique-subarray
 * @title: maximum-sum-of-almost-unique-subarray
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxSum", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public long maxSum(List<Integer> nums, int m, int k) {

        int cnt = 0;
        long ans = 0;
        long sum = 0;
        int key = 0,t = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            // 出
            if (i >= k) {
                key = nums.get(i - k);
                sum -= key;
                t = map.get(key)-1;
                if (t == 0) {
                    cnt--;
                }
                map.put(key, t);
            }

            // 计算
            key = nums.get(i);
            t = map.getOrDefault(key, 0) + 1;
            // 首次遇到 cnt++
            if (t == 1) {
                cnt++;
            }

            sum += key;

            map.put(key, t);

            // 符合题目条件
            if (i >= k - 1 && cnt >= m && sum > ans) {
                ans = sum;
            }

        }

        return ans;
    }


}