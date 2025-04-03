package leetcode.contest.weekly.w_400.w_443;

import code_generation.annotation.TestCaseGroup;
import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-443/problems/longest-palindrome-after-substring-concatenation-i">子字符串连接后的最长回文串 I</a>
 * @title: 子字符串连接后的最长回文串 I
 */
@TestCaseGroup(start = 2, end = 0x3fff, use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "longestPalindrome", "B.txt");
    }


    char[] s, t;

    public int longestPalindrome(String s, String t) {
        this.s = s.toCharArray();
        this.t = t.toCharArray();
        int ans = 1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                for (int i1 = 0; i1 < t.length(); i1++) {
                    for (int j1 = i1; j1 < t.length(); j1++) {
                        if (check(i, j, i1, j1)) {
                            ans = Math.max(ans, j - i + 1 + j1 - i1 + 1);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (check(i, j, -1, 0)) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        for (int i = 0; i < t.length(); i++) {
            for (int j = i; j < t.length(); j++) {
                if (check(-1, 0, i, j)) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    public boolean check(int si, int sj, int ti, int tj) {
        char[] temp = new char[100];
        int size = 0;
        if (si >= 0) {
            for (int i = si; i <= sj; i++) {
                temp[size++] = s[i];
            }
        }
        if (ti >= 0) {
            for (int i = ti; i <= tj; i++) {
                temp[size++] = t[i];
            }
        }
        if (size == 0) return false;
        int mid = size / 2;
        int l = size % 2 == 0 ? mid - 1 : mid;
        int r = mid;
        while (l >= 0 && r < size) {
            if (temp[l] != temp[r]) return false;
            l--;
            r++;
        }
        return true;
    }


}