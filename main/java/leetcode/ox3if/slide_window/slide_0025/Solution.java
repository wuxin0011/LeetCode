package leetcode.ox3if.slide_window.slide_0025;

import code_generation.utils.IoUtil;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * 1438. 绝对差不超过限制的最长连续子数组
 * <p>
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。如果不存在满足条件的子数组，则返回 0 。
 * <p>
 * 示例 1：输入：nums = [8,2,4,7], limit = 4输出：2 解释：所有子数组如下：[8] 最大绝对差 |8-8| = 0 <= 4.[8,2] 最大绝对差 |8-2| = 6 > 4. [8,2,4] 最大绝对差 |8-2| = 6 > 4.[8,2,4,7] 最大绝对差 |8-2| = 6 > 4.[2] 最大绝对差 |2-2| = 0 <= 4.[2,4] 最大绝对差 |2-4| = 2 <= 4.[2,4,7] 最大绝对差 |2-7| = 5 > 4.[4] 最大绝对差 |4-4| = 0 <= 4.[4,7] 最大绝对差 |4-7| = 3 <= 4.[7] 最大绝对差 |7-7| = 0 <= 4. 因此，满足题意的最长子数组的长度为 2 。
 * <p>
 * 示例 2：输入：nums = [10,1,2,4,7,2], limit = 5输出：4 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 * <p>
 * 示例 3：输入：nums = [4,2,2,2,4,4,2,2], limit = 0输出：3
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
 * @title: longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "longestSubarray", "in.txt");
    }


    public int longestSubarray(int[] nums, int limit) {
        // 单调队列
        int ans = 0, r = 0, l = 0;
        int n = nums.length;
        int[] q_mx = new int[n];
        int q_mx_l = 0, q_mx_r = 0;
        int[] q_mi = new int[n];
        int q_mi_l = 0, q_mi_r = 0;
        while (r < n) {
            while(q_mx_r>q_mx_l && q_mx[q_mx_r-1]<nums[r]){
                q_mx_r--;
            }
            q_mx[q_mx_r++] = nums[r];

            while(q_mi_r>q_mi_l && q_mi[q_mi_r-1]>nums[r]){
                q_mi_r--;
            }
            q_mi[q_mi_r++] = nums[r];

            while(q_mx_r>q_mx_l && q_mi_r > q_mi_l && q_mx[q_mx_l] - q_mi[q_mi_l]>limit){
                if(q_mx[q_mx_l] == nums[l]){
                    q_mx_l++;
                }
                if(q_mi[q_mi_l] == nums[l]){
                    q_mi_l++;
                }
                l++;
            }


            ans = Math.max(ans, r - l + 1);
            r++;
        }
        return ans;
    }


    public int longestSubarray1(int[] nums, int limit) {
        // 单调队列
        Deque<Integer> q_mx = new ArrayDeque<>();
        Deque<Integer> q_mi = new ArrayDeque<>();
        int ans = 0, r = 0, l = 0;
        int n = nums.length;
        while (r < n) {

            // 单调递增
            while (!q_mx.isEmpty() && q_mx.getLast() < nums[r]) {
                q_mx.removeLast();
            }
            q_mx.addLast(nums[r]);

            // 单调递减
            while (!q_mi.isEmpty() && q_mi.getLast() > nums[r]) {
                q_mi.removeLast();
            }
            q_mi.addLast(nums[r]);

            // 队列出口处就是最大值和最小值
            while (!q_mx.isEmpty() && !q_mi.isEmpty() && (q_mx.peek() - q_mi.peek() > limit)) {
                if (!q_mx.isEmpty() && nums[l] == q_mx.peek()) {
                    q_mx.poll();
                }
                if (!q_mi.isEmpty() && nums[l] == q_mi.peek()) {
                    q_mi.poll();
                }
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }
        return ans;
    }


    public int longestSubarray2(int[] nums, int limit) {
        int ans = 0;
        int r = 0, l = 0, n = nums.length;
        PriorityQueue<Integer> mx_q = new PriorityQueue<>((a, b) -> b.compareTo(a));
        PriorityQueue<Integer> mi_q = new PriorityQueue<>((a, b) -> a.compareTo(b));
        while (r < n) {
            mx_q.add(nums[r]);
            mi_q.add(nums[r]);
            while (!mx_q.isEmpty() && !mi_q.isEmpty() && mx_q.peek() - mi_q.peek() > limit) {
                mx_q.remove((int) nums[l]);
                mi_q.remove((int) nums[l]);
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }
        return ans;
    }

}