package leetcode.contest.weekly.w_300.w_386.d;

import leetcode.utils.IoUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author: wuxin0011
 * @Description: very hard !!!
 * @url https://leetcode.cn/problems/earliest-second-to-mark-indices-ii/
 */
public class D {
    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "earliestSecondToMarkIndices","in.txt");
    }


    /**
     * @see https://leetcode.cn/problems/earliest-second-to-mark-indices-ii/solutions/2653053/er-fen-da-an-fan-hui-tan-xin-pythonjavac-997n/
     * @param nums
     * @param changeIndices
     * @return
     */
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int n = nums.length;
        int m = changeIndices.length;
        if (n > m) {
            return -1;
        }

        long slow = n; // 慢速复习+考试所需天数
        for (int v : nums) {
            slow += v;
        }

        int[] firstT = new int[n];
        Arrays.fill(firstT, -1);
        for (int t = m - 1; t >= 0; --t) {
            firstT[changeIndices[t] - 1] = t;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int left = n - 1, right = m + 1;
        while (left + 1 < right) {
            pq.clear();
            int mid = (left + right) / 2;
            if (check(nums, changeIndices, firstT, pq, slow, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right > m ? -1 : right;
    }

    private boolean check(int[] nums, int[] changeIndices, int[] firstT, PriorityQueue<int[]> pq, long slow, int mx) {
        int cnt = 0;
        for (int t = mx - 1; t >= 0; t--) {
            int i = changeIndices[t] - 1;
            int v = nums[i];
            if (v <= 1 || t != firstT[i]) {
                cnt++; // 留给左边，用来快速复习/考试
                continue;
            }
            if (cnt == 0) {
                if (pq.isEmpty() || v <= pq.peek()[0]) {
                    cnt += 1; // 留给左边，用来快速复习/考试
                    continue;
                }
                slow += pq.poll()[0] + 1;
                cnt += 2; // 反悔：一天快速复习，一天考试
            }
            slow -= v + 1;
            cnt--; // 快速复习，然后消耗一天来考试
            pq.offer(new int[]{v, i});
        }
        return cnt >= slow; // 剩余天数不能慢速复习+考试
    }
}
