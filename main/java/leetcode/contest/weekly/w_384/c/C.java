package leetcode.contest.weekly.w_384.c;

import leetcode.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class);
    }
    public int maxPalindromesAfterOperations(String[] words) {
        int[] h = new int[26];
        for (String w : words) {
            for (int i = 0; i < w.length(); i++) {
                h[w.charAt(i) - 'a']++;
            }
        }
        int left = 0;
        for (int v : h) left += v / 2;
        // System.out.println("left" + left);

        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int ans = 0;
        for (String w : words) {
            int n = w.length() / 2;
            if (left < n) {
                break;
            }
            left -= n;
            ans++;
        }

        return ans;
    }
}
