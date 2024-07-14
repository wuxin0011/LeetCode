package leetcode.contest.biweekly.bi_100.bi_134.c;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-134/problems/alternating-groups-ii
 * @title: 交替组 II
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "numberOfAlternatingGroups", "C.txt");
    }

    // 0(1) 空间做法
    public int numberOfAlternatingGroups(int[] a, int k) {
        int cnt = 0;
        int n = a.length;
        for (int l = 0, r = 0; r < n + k - 1; r++) {
            if (r > 0 && a[r % n] == a[(r - 1) % n]) {
                l = r;
                continue;
            }
            if (r - l + 1 >= k) {
                cnt++;
            }
        }
        return cnt;
    }

    // 0(n+k) 空间做法

    public int numberOfAlternatingGroups1(int[] a, int k) {
        int n = a.length;
        int cnt = 0;
        int[] h = new int[n + k - 1];
        int size = 0;
        for (int i = n - k + 1; i < n; i++) {
            h[size++] = a[i];
        }
        for (int j : a) {
            h[size++] = j;
        }
        int l = 0, r = 0;
        while (r < h.length) {
            if (r > 0 && h[r] == h[r - 1]) {
                l = r;
                r++;
                continue;
            }
            if (r - l + 1 == k) {
                cnt++;
                l++;
            }
            r++;
        }
        return cnt;
    }


}