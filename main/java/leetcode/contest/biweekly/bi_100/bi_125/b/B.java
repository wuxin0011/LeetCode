package leetcode.contest.biweekly.bi_100.bi_125.b;

import code_generation.utils.IoUtil;

import java.util.PriorityQueue;

/**
 * @author: wuxin0011
 * @Description: 使用最小堆，简单模拟 注意溢出
 * @url https://leetcode.cn/problems/minimum-operations-to-exceed-threshold-value-ii/description/
 * @title 超过阈值的最少操作数 II
 */
public class B {
    public static void main(String[] args) {
        IoUtil.testUtil(B.class);
    }

    public int minOperations(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return 0;
        int cnt = 0;
        int n = nums.length;
        PriorityQueue<Long> q = new PriorityQueue<>();

        for (int num : nums) {
            q.add((long) num);
        }
        while (q.size() >= 2 && q.peek() < k) {
            long v1 = q.poll();
            assert !q.isEmpty();
            long v2 = q.poll();
            long v = Math.min(v1, v2) * 2 + Math.max(v1, v2);
            q.add(v);
            cnt++;
        }

        return cnt;
    }
}
