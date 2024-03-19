package leetcode.contest.weekly.w_300.w_389.b;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/count-substrings-starting-and-ending-with-given-character/description/
 * @title
 */
public class B {
    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "main", "in.txt");
    }

    public long countSubstrings(String s, char c) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (c == s.charAt(i)) cnt++;
        }
        return (cnt) * 1L * (cnt + 1) / 2;
    }
}
