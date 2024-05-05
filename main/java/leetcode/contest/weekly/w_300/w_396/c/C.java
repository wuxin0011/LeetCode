package leetcode.contest.weekly.w_300.w_396.c;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-396/problems/minimum-length-of-anagram-concatenation
 * @title: 同位字符串连接的最小长度
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minAnagramLength", "C.txt");
    }


    public int minAnagramLength(String s) {
        int[] h = new int[26];
        char[] cs = s.toCharArray();
        int n = cs.length;
        int r = 1;
        int mid = n >> 1;
        int[] help = new int[26];
        for (char c : cs) {
            help[c - 'a']++;
        }
        while (r <= mid) {
            h[cs[r-1] - 'a']++;
            if (check(cs, r, h, help)) return r;
            r++;
        }

        return n;
    }

    public static boolean check(char[] cs, int k, int[] h, int[] help) {
        int n = cs.length;
        if ((n - k) % k != 0) {
            return false;
        }

        // 剪枝
        for (int i = 0; i < h.length; i++) {
            if (h[i] == 0 && help[i] > 0) {
                return false;
            }
            if (help[i] > 0 && h[i] > 0 && help[i] % h[i] != 0) {
                return false;
            }
        }
        int[] temp = new int[26];
        int l = 0;
        for (int i = k; i < cs.length; i++) {
            char c = cs[i];
            temp[c - 'a']++;
            l++;
            if (temp[c - 'a'] > h[c - 'a']) return false;
            if (l == k) {
                if (!valid(h, temp)) {
                    return false;
                }
                l = 0;
            }
        }

        return true;
    }


    public static boolean valid(int[] h, int[] temp) {
        int n = h.length;
        for (int i = 0; i < n; i++) {
            if (h[i] != temp[i]) return false;
            temp[i] = 0;
        }
        return true;
    }


}