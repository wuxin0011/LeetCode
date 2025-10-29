package leetcode.contest.weekly.w_400.w_473;

import code_generation.utils.IoUtil;

import java.util.*;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-473/problems/stable-subarrays-with-equal-boundary-and-interior-sum">边界与内部和相等的稳定子数组</a>
 * @title: 边界与内部和相等的稳定子数组
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "countStableSubarrays", "C.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public long countStableSubarrays(int[] a) {
        int n = a.length;
        int mx = Integer.MIN_VALUE;
        int mi = Integer.MAX_VALUE;
        for (int x : a) {
            mi = Math.min(mi,x);
            mx = Math.max(mx,x);
        }

        if (mi == mx) {
            n -= 2;
            if (mi != 0) {
                return n - 2;
            }
            return 1L * n * (n + 1) / 2;
        }

        long[] s = new long[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + a[i];
        }

        Map<Integer, List<Integer>> d = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = a[i];
            d.computeIfAbsent(x, k -> new ArrayList<>()).add(i);
        }

        long ans = 0;
        for (Map.Entry<Integer, List<Integer>> entry : d.entrySet()) {
            int k = entry.getKey();
            List<Integer> v = entry.getValue();
            int m = v.size();
            if (m == 1) continue;

            int i = 0;
            Map<Long, Integer> cnt = new HashMap<>();
            for (int r : v) {
                while (i < m && v.get(i) <= r - 2) {
                    long preVal = s[v.get(i)];
                    cnt.put(preVal, cnt.getOrDefault(preVal, 0) + 1);
                    i++;
                }
                ans += cnt.getOrDefault(s[r + 1] - 3L * k, 0);
            }
        }
        return ans;
    }
}