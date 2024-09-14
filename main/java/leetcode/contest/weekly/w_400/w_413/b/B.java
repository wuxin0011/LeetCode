package leetcode.contest.weekly.w_400.w_413.b;

import code_generation.utils.IoUtil;

import java.util.PriorityQueue;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-413/problems/k-th-nearest-obstacle-queries
 * @title: 第 K 近障碍物查询
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "resultsArray", "B.txt");
    }


    public int[] resultsArray(int[][] queries, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b.compareTo(a));
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int v = Math.abs(queries[i][0]) + Math.abs(queries[i][1]);
            q.add(v);
            if (q.size() < k) {
                ans[i] = -1;
            } else {
                while (q.size() > k) q.poll();
                ans[i] = q.isEmpty() ? -1 : q.peek();
            }
        }
        return ans;
    }


}