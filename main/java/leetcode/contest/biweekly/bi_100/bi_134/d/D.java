package leetcode.contest.biweekly.bi_100.bi_134.d;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-134/problems/number-of-subarrays-with-and-value-of-k
 * @title: 子数组按位与值为 K 的数目
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "countSubarrays", "D.txt");
    }


    public long countSubarrays(int[] a, int k) {
        int n = a.length;
        int l = 0, r = 0;
        int v = -1;
        int[] h = new int[32];
        Arrays.fill(h, 1);
        long ans = 0;
        while (r < n) {
            v &= a[r];
            add(h, a[r], true);
            // 不符合题目要求 重新赋值
            if (!isLess(v, k)) {
                v = -1;
                l = r;
                Arrays.fill(h, 1);
            }
            while (l < r && v == k) {
                ans += 1;
                add(h, a[l], false);
                v = calc(h);
                // 统计
                l++;
            }
            r++;
        }
        return ans;
    }


    public static void add(int[] h, int v, boolean isAdd) {
        int x = 0;
        for (int i = 0; i < h.length-1 && x <= v; i++) {
            int d = (v >> i & 1);
            if (d == 0) {
                continue;
            }
            if (isAdd) {
                h[i]++;
            } else {
                h[i]--;
            }
//            x |= 1 << i;
        }
    }

    public static int calc(int[] h) {
        int x = 0;
        for (int i = 0; i < h.length-1; i++) {
            if (h[i] == 0) {
                continue;
            }
            x |= 1 << i;
        }
        return x;
    }


    // 判断是否对应
    public static boolean isLess(int v, int k) {
        for (int i = 0; i < 32; i++) {
            int a = v >> i & 1;
            int b = k >> i & 1;
            if (a == 0 && b == 1) {
                return false;
            }
        }
        return true;
    }


}