package leetcode.contest.biweekly.bi_100.bi_128.d;

import code_generation.utils.IoUtil;

import java.util.HashMap;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-128/problems/find-the-number-of-subarrays-where-boundary-elements-are-maximum
 * @title: 边界元素是最大值的子数组数目
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "numberOfSubarrays", "D.txt");
    }


    public long numberOfSubarrays(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int ans = 0;

        // 找到每个元素向左第一个小于它的元素的位置
        for (int i = 0; i < n; i++) {
            int j = i - 1;
            while (j >= 0 && nums[j] < nums[i]) {
                j = left[j];
            }
            left[i] = j;
        }

        // 找到每个元素向右第一个小于它的元素的位置
        for (int i = n - 1; i >= 0; i--) {
            int j = i + 1;
            while (j < n && nums[j] < nums[i]) {
                j = right[j];
            }
            right[i] = j;
        }

        // 计算以每个元素为最大值的子数组数量，并累加到结果中
        for (int i = 0; i < n; i++) {
            int leftCount = i - left[i];
            int rightCount = right[i] - i;
            ans += leftCount * rightCount;
            ans += leftCount; // 加上左侧长度为最大值的子数组数量
            ans += rightCount; // 加上右侧长度为最大值的子数组数量
        }
        return ans;
    }

    public long numberOfSubarrays1(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        if (map.size() == 1) {
            return (long) (n + 1) * n >> 1;
        }
        int r = 0, l = 0;
        int mx = 0;
        long ans = 0;
        while (r < n) {
            while (r < n && nums[r] < mx) {
                r++;
            }
            mx = nums[r];
            while (r < n && nums[r] <= mx) {
                r++;
            }

            r++;
        }
        return ans;
    }


}