package leetcode.contest.biweekly.bi_100.bi_126.b;

import leetcode.utils.IoUtil;

import java.util.PriorityQueue;

/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class B {
    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "unmarkedSumArray", "in.txt");
    }


    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        int n = queries.length;
        int m = nums.length;
        boolean[] vis = new boolean[m];
        // Arrays.sort(queries, (a,b)->a[0]-b[0]);
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->a[0] == b[0] ?a[1]-b[1]:a[0]-b[0]);
        long tot = 0;
        for (int i = 0; i < nums.length; i++) {
            q.add(new int[]{nums[i], i});
            tot += (long) nums[i];
        }
        long[] ans = new long[n];

        for (int i = 0; i < n; i++) {
            int id = queries[i][0];
            int cnt = queries[i][1];
            //System.out.println("id = " + id + ",vis[id] = " + vis[id] + ",cnt = " + cnt +",tot =" + tot);

            if(!vis[id]){
                tot -= nums[id];
                vis[id] = true;
            }
            while (!q.isEmpty() && cnt > 0) {
                int[] p = q.poll();
                if (vis[p[1]]) continue;
                vis[p[1]] = true;
                cnt--;
                tot -= p[0]*1L;


            }
            ans[i] = tot;
        }


        return ans;
    }
}
