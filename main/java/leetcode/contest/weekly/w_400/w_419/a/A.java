package leetcode.contest.weekly.w_400.w_419.a;

import code_generation.utils.IoUtil;

import java.util.PriorityQueue;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-419/problems/find-x-sum-of-all-k-long-subarrays-i
 * @title: 计算子数组的 x-sum I
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "findXSum", "A.txt");
    }


    public int[] findXSum(int[] nums, int k, int x) {
        int[] cnts = new int[51];
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            if (i >= k) {
                cnts[nums[i - k]]--;
            }
            cnts[nums[i]]++;
            if (i >= k - 1) {
                ans[i - k + 1] = calc(cnts, k, x);
            }
        }
        return ans;
    }

    int calc(int[] cnts, int k, int x) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]);
        for (int i = 0; i <= 50; i++) {
            if (cnts[i] > 0) {
                pq.add(new int[]{cnts[i], i});
                k -= cnts[i];
            }
            if (k == 0) break;
        }
        int ans = 0;
        while (x > 0 && !pq.isEmpty()) {
            int[] p = pq.poll();
            ans += p[0] * p[1];
            x--;
        }
        return ans;
    }


}