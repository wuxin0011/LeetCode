package leetcode._0x3f_.data_struct.heap.base.Solution_0009;

import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/the-number-of-the-smallest-unoccupied-chair
 * @title: 最小未被占据椅子的编号
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "smallestChair", "in.txt");
    }


    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        //{st,ed,i,no}
        int[][] timeQ = new int[n][4];
        for (int i = 0; i < n; i++) {
            timeQ[i][0] = times[i][0];
            timeQ[i][1] = times[i][1];
            timeQ[i][2] = i;
        }
        Arrays.sort(timeQ, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        TreeSet<Integer> set = new TreeSet<>((a, b) -> a - b);
        for (int i = 0; i < n; i++) {
            set.add(i); // 添加空出的
        }
        // 根据结束事件排序
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int i = 0; i < n; i++) {
            // [st1,ed1]
            // [st2,ed2]
            while (!pq.isEmpty() && timeQ[i][0] >= pq.peek()[1]) {
                set.add(pq.poll()[3]);
            }
            int curNO = set.first();
            timeQ[i][3] = curNO;
            set.remove(timeQ[i][3]);
            pq.add(timeQ[i]);
            if (timeQ[i][2] == targetFriend) {
                return curNO;
            }
        }
        return n;
    }




}