package leetcode.top_interview_150.kth_largest_element_in_an_array;

import code_generation.utils.IoUtil;

import java.util.PriorityQueue;


/**
 * 215. 数组中的第K个最大元素
 * <p>
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 示例 1:
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * <p>
 * 示例2:
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 * <p>
 * 提示：
 * 1 <= k <= nums.length <= 10^5
 * -10^4<= nums[i] <= 10^4
 *
 * @author: wuxin0011
 * @Description: 双堆的雏形 这题可以用小根堆维护
 * @url: https://leetcode.cn/problems/kth-largest-element-in-an-array
 * @title: kth-largest-element-in-an-array
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "findKthLargest", "in.txt");
        IoUtil.testUtil(Solution.class, "findKthLargest1", "in.txt");
    }


    public int findKthLargest(int[] nums, int k) {


        // PriorityQueue<Integer> bigQueue = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> smallQueue = new PriorityQueue<>((a, b) -> a - b);

        for (int num : nums) {
            if (smallQueue.size() < k) {
                smallQueue.add(num);
            } else if (smallQueue.size() == k && smallQueue.peek() < num) {
                smallQueue.poll();
                smallQueue.add(num);
            }
        }
        return smallQueue.peek();
    }


    // 根据桶排序思路
    public int findKthLargest1(int[] nums, int k) {

        int mi = Integer.MAX_VALUE;
        int mx = Integer.MIN_VALUE;
        for (int num : nums) {
            if (mi > num) {
                mi = num;
            }
            if (mx < num) {
                mx = num;
            }
        }


        int[] counts = new int[mx - mi + 1];
        for (int num : nums) {
            counts[num - mi]++;
        }
        int ans = 0;
        for (int i = counts.length - 1; i >= 0 && k > 0; i--) {
            while( k > 0 && counts[i] > 0) {
                counts[i]--;
                ans = i + mi;
                k--;
            }
        }
        return ans;
    }


}