package leetcode.dichotomy.Dichotomy_0002;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
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