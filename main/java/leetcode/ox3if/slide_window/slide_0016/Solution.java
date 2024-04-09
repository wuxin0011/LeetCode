package leetcode.ox3if.slide_window.slide_0016;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 2953. 统计完全子字符串
 * <p>
 * 给你一个字符串word和一个整数 k。如果word的一个子字符串 s满足以下条件，我们称它是 完全字符串：s中每个字符 恰好出现 k次。相邻字符在字母表中的顺序 至多相差2。也就是说，s中两个相邻字符c1 和c2，它们在字母表中的位置相差至多为 2 。请你返回 word中 完全子字符串的数目。子字符串指的是一个字符串中一段连续 非空的字符序列。
 * <p>
 * 示例 1：输入：word = "igigee", k = 2输出：3解释：完全子字符串需要满足每个字符恰好出现 2 次，且相邻字符相差至多为 2 ：igigee, igigee, igigee。
 * <p>
 * 示例 2：输入：word = "aaabbbccc", k = 3输出：6解释：完全子字符串需要满足每个字符恰好出现 3 次，且相邻字符相差至多为 2 ：aaabbbccc, aaabbbccc, aaabbbccc, aaabbbccc, aaabbbccc, aaabbbccc 。
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= word.length <= 105
 * word只包含小写英文字母。
 * 1 <= k <= word.length
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/count-complete-substrings
 * @title: count-complete-substrings
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "countCompleteSubstrings", "in.txt");
    }


    public int countCompleteSubstrings(String word, int k) {
        int ans = 0;
        char[] ch = word.toCharArray();
        int[] h = new int[26];
        int type = 0;
        for (char c : ch) {
            h[c-'a']++;
            if(h[c-'a']==1){
                type++;
            }
        }
        if(type==1 && k == 1) {
            return ch.length;
        }
        for (int w = k; w <= ch.length; w += k) {
            if (w == 1) {
                ans += ch.length;
            } else {
                ans += f(ch, w, k);
            }

        }
        return ans;
    }


    public static int f(char[] cs, int w, int k) {
        // String s = new String(cs);
        int[] h = new int[26];
        int cnt = 0;
        for (int r = 0, l = 0; r < cs.length; r++) {
            if (r - l >= w) {
                h[cs[l] - 'a']--;
                l++;
            }

            if (Math.abs(cs[r] - cs[Math.max(r - 1, 0)]) > 2) {
                l = r;
                Arrays.fill(h, 0);
                h[cs[l] - 'a']++;
                r++;
            }
            while (h[cs[l] - 'a'] > k) {
                h[cs[l] - 'a']--;
                l++;
            }

            if (r == cs.length) break;

            h[cs[r] - 'a']++;

            while (h[cs[r] - 'a'] > k) {
                h[cs[l] - 'a']--;
                l++;
            }

            // in
            if (r - l == w - 1 && check(h, k, l, r, cs)) {
                cnt++;
                // System.out.println(s.substring(l, r + 1));
                h[cs[l] - 'a']--;
                l++;
            }


        }
        return cnt;
    }



    public static boolean check(int[] h, int k, int l, int r, char[] cs) {
        for (int i : h) {
            if (i == 0) continue;
            if (i != k) return false;
        }
        for (int i = l + 1; i <= r; i++) {
            if (Math.abs(cs[i] - cs[i - 1]) > 2) return false;
        }
        return true;
    }


}