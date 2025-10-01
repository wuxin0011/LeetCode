package leetcode.top_interview_150.sliding_window_maximum;

import code_generation.utils.IoUtil;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * 拓展：
 * 二维滑动窗口 ： https://leetcode.cn/problems/largest-local-values-in-a-matrix
 *
 *
 *
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/problems/sliding-window-maximum">滑动窗口最大值</a>
 * @title: 滑动窗口最大值
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxSlidingWindow", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // 出
            if (i >= k && !q.isEmpty() && i - q.peekFirst() + 1 > k) {
                q.pollFirst();
            }
            // 降本增效
            while (!q.isEmpty() && nums[i] >= nums[q.peekLast()]) {
                q.pollLast();
            }
            // 入
            q.addLast(i);
            // 存放答案
            if (i - k + 1 >= 0 && !q.isEmpty()) {
                ans[i - k + 1] = nums[q.peekFirst()];
            }
        }
        return ans;
    }


}