package leetcode._0x3f_.dichotomy.Dichotomy_0002;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 *
 * 719. 找出第 K 小的数对距离
 *
 * 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。返回 所有数对距离中 第 k 小的数对距离。
 *
 * 示例 1：输入：nums = [1,3,1], k = 1输出：0解释：数对和对应的距离如下：(1,3) -> 2(1,1) -> 0(3,1) -> 2距离第 1 小的数对是 (1,1) ，距离为 0 。
 *
 * 示例 2：输入：nums = [1,1,1], k = 2输出：0
 *
 * 示例 3：输入：nums = [1,6,1], k = 3输出：5
 *
 * 提示：
 *
 *
 * 	n == nums.length
 * 	2 <= n <= 104
 * 	0 <= nums[i] <= 106
 * 	1 <= k <= n * (n - 1) / 2
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/find-k-th-smallest-pair-distance
 * @title: find-k-th-smallest-pair-distance
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "smallestDistancePair", "in.txt");
    }


    // 排序不影响题目有点少个数对
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, r = nums[nums.length - 1] - nums[0];
        int ans = 0;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (check(nums, mid, k)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    // 一个简单滑动窗口
    // 当 r - l > limit => l++ 直到 r - l <= limit
    // 对数为 r - l

    public static boolean check(int[] nums, int limit, int k) {
        int cnt = 0;
        int l = 0, r = 0;
        while (r < nums.length) {
            while (nums[r] - nums[l] > limit) {
                l++;
            }
            cnt += r - l;
            r++;
        }

        return cnt >= k;
    }

}