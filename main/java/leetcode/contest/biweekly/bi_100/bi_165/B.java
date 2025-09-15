package leetcode.contest.biweekly.bi_100.bi_165;

import code_generation.utils.IoUtil;

import java.util.HashMap;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/biweekly-contest-165/problems/minimum-discards-to-balance-inventory">使库存平衡的最少丢弃次数</a>
 * @title: 使库存平衡的最少丢弃次数
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "minArrivalsToDiscard", "B.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int minArrivalsToDiscard(int[] a, int k, int m) {
        int n = a.length;
        int l = 0, r = n + 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (check(a, k, m, mid)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static boolean check(int[] a, int k, int m, int x) {
        int n = a.length;
        int c = 0;
        boolean[] vis = new boolean[n];
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (i >= k && !vis[i - k]) {
                cnt.merge(a[i - k], -1, Integer::sum);
            }
            cnt.merge(a[i], 1, Integer::sum);
            if (cnt.get(a[i]) > m) {
                cnt.merge(a[i], -1, Integer::sum);
                vis[i] = true;
                c++;
            }
            if (c > x) return false;
        }
        return true;
    }


}