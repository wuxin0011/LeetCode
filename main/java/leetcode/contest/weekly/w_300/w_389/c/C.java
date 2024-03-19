package leetcode.contest.weekly.w_300.w_389.c;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/minimum-deletions-to-make-string-k-special/
 * @title
 */
public class C {
    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minimumDeletions", "in.txt");
    }

    public int minimumDeletions(String word, int k) {
        int[] h = new int[26];
        int size = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (h[c - 'a'] == 0) size++;
            h[c - 'a']++;
        }
        int[] arr = new int[size];
        for (int j : h) {
            if (j == 0) {
                continue;
            }
            size--;
            arr[size] = j;
        }
        // ystem.out.println(Arrays.toString(arr));
        int ans = Integer.MAX_VALUE;
        for (int x : arr) {
            // 枚举容量
            for (int v = Math.max(0, x - k); v <= x; v++) {
                ans = Math.min(ans, calc(arr, v, k));
            }
        }
        return ans;
    }

    public static int calc(int[] arr, int v, int k) {
        int res = 0;
        for (int cap : arr) {
            if (cap == v) continue;
            if (cap > v + k) {
                res += cap - (v + k);// 需要减少的
            } else if (cap < v) {
                res += cap; // 全部减少
            }
        }
        return res;
    }
}
