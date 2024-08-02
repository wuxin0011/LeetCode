package leetcode._0x3f_.dp.interval.b_other.interval_dp_0000;

import code_generation.annotation.TestCaseGroup;
import code_generation.utils.IoUtil;

/**
 * 5. 最长回文子串
 * <p>
 * 给你一个字符串 s，找到 s 中最长的 回文 子串。
 * <p>
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/longest-palindromic-substring
 * @title: 最长回文子串
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "longestPalindrome", "in.txt");
    }

    @TestCaseGroup(start = 2, end = 2, use = false)
    public String longestPalindrome(String s) {
//        System.out.println("origin s = " + s);
        int n = s.length();
        char[] ss = new char[2 * n + 1];
        for (int i = 0; i < ss.length; i++) {
            if ((i & 1) == 0) {
                ss[i] = '#';
            } else {
                ss[i] = s.charAt(i >> 1);
            }
        }
        System.out.println("ss : " + new String(ss));
        int mxL = 0, mxR = 0, mx = 0;
        n = ss.length;
        for (int i = 0; i < n; i++) {
            int l = i, r = i;
            while (l >= 0 && r < n && ss[l] == ss[r]) {
                l--;
                r++;
            }
            // (l,r)
            //
            r--;
            l++;
            if (r - l + 1 > mx) {
                mx = r - l + 1;
                mxL = l;
                mxR = r;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = mxL; i <= mxR; i++) {
            if (ss[i] == '#') continue;
            sb.append(ss[i]);
        }
        return sb.toString();
    }


}