package leetcode.ox3if.data_struct.heap.base.Solution_0017;

import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/meeting-rooms-iii
 * @title: meeting-rooms-iii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "mostBooked", "in.txt");
    }

    static class Pair implements Comparable<Pair>{
        long ed;
        int id;

        public Pair(long ed, int id) {
            this.ed = ed;
            this.id = id;
        }

        @Override
        public int compareTo(Pair o2) {
            Pair o1 = this;
            if (o1.ed != o2.ed) {
                return (int) (o1.ed - o2.ed);
            }
            return o1.id - o2.id;
        }
    }


    public int mostBooked(int n, int[][] meetings) {
        if (n == 1) return 0;
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]); // 按照最早时间排序
        // System.out.println(Arrays.deepToString(meetings));
        TreeSet<Integer> s = new TreeSet<Integer>((a, b) -> a - b);
        for (int i = 0; i < n; i++) {
            s.add(i);
        }
        int[] cnt = new int[n];
        PriorityQueue<Pair> q = new PriorityQueue<>(); // 按照最早结束时间排序
        int tot = meetings.length;
        for (int[] meeting : meetings) {
            long st = meeting[0], ed = meeting[1];
            while (!q.isEmpty() && q.peek().ed <= st) {
                Pair p = q.poll();
                s.add(p.id);
            }
            if (s.isEmpty() && !q.isEmpty()) {
                Pair p = q.poll();
                ed += p.ed - st;
                s.add(p.id);
            }
            assert !s.isEmpty();
            int no = s.first();
            cnt[no]++;
            if (cnt[no] > tot / 2) {
                return no;
            }
            s.remove(no);
            q.add(new Pair(ed, no));
        }
        int idx = 0;
        for (int i = 1; i < n; i++) {
            if (cnt[idx] < cnt[i]) {
                idx = i;
            }
        }
        return idx;
    }


}