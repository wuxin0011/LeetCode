package leetcode.contest.weekly.w_300.w_390.a;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class, IoUtil.DEFAULT_METHOD_NAME, "A.txt");
    }


    public int maximumLengthSubstring(String s) {
        int[] help = new int[26];
        char[] ss = s.toCharArray();
        int mx = 0;
        int r = 0, l = 0;
        int n = ss.length;
        while (r < n) {
            help[ss[r] - 'a']++;
            while (help[ss[r] - 'a']> 2) {
                help[ss[l] - 'a']--;
                l++;
            }
            mx = Math.max(r - l + 1,mx);
            r++;
        }
        return mx;
    }
}
