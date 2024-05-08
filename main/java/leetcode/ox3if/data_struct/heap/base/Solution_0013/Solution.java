package leetcode.ox3if.data_struct.heap.base.Solution_0013;

import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/single-threaded-cpu
 * @title: single-threaded-cpu
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "getOrder", "in.txt");
    }


    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[][] help = new int[n][3];
        for (int i = 0; i < n; i++) {
            help[i][0] = tasks[i][0];
            help[i][1] = tasks[i][1];
            help[i][2] = i;
        }
        Arrays.sort(help, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> q = new PriorityQueue<int[]>((a, b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);
        int[] ans = new int[n];

        int i = 0,j = 0;
        long curTime = help[0][0];
        while (i < n && help[i][0] == curTime) {
            q.add(help[i]);
            i++;
        }
        while (!q.isEmpty()) {
            int[] p = q.poll();
            ans[j++] = p[2];
            curTime += p[1];

            // 注意当前时间
            while (i < n && help[i][0] <= curTime) {
                q.add(help[i]);
                i++;
            }
            if (q.isEmpty() && i < n) {
                curTime = help[i][0]; // update
                while (i < n && help[i][0] == curTime) q.add(help[i++]);

            }
        }

        // System.out.println(Arrays.toString(ans));
        return ans;
    }


}