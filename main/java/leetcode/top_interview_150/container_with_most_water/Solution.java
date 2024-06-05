package leetcode.top_interview_150.container_with_most_water;

import code_generation.utils.IoUtil;

/**
 * 11. 盛最多水的容器
 * <p>
 * 给定一个长度为 n 的整数数组height。
 * 有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * <p>
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
 * <p>
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 * <p>
 * 提示：
 * n == height.length
 * 2 <= n <= 10^5
 * 0 <= height[i] <= 10^4
 *
 * @author: wuxin0011
 * @Description: 双指针
 * @url: https://leetcode.cn/problems/container-with-most-water
 * @title: 盛最多水的容器
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxArea", "in.txt");
        IoUtil.testUtil(Solution.class, "maxArea1", "in.txt");
    }


    public int maxArea(int[] height) {
        int ans = 0;
        int n = height.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int cur = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, cur);

            // 如果当前值和相邻值一样 直接缩减 因为高度不变 宽度变小 答案一定变小
            while (l < r && l > 0 && height[l] == height[l - 1]) l++;

            while (l < r && r + 1 < n && height[r] == height[r + 1]) r--;

            // 此时左指针向右移动不影响右边答案
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return ans;
    }


    public int maxArea1(int[] height) {
        int ans = 0;
        int n = height.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int cur = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, cur);
            // 此时左指针向右移动不影响右边答案
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return ans;
    }


}