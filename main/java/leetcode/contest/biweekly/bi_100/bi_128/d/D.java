package leetcode.contest.biweekly.bi_100.bi_128.d;

import code_generation.utils.IoUtil;

import java.util.ArrayDeque;
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
        ArrayDeque<int[]> q = new ArrayDeque<>();
        long ans = 0;
        for (int num : nums) {
            while (!q.isEmpty() && q.getLast()[0] < num) {
                q.pollLast();
            }
            if (q.isEmpty() || q.getLast()[0] != num) {
                q.addLast(new int[]{num, 1});
            } else {
                q.getLast()[1]++;
            }
            ans += q.getLast()[1];
        }
        return ans;
    }



}