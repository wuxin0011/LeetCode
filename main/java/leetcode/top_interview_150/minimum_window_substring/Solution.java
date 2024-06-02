package leetcode.top_interview_150.minimum_window_substring;

import code_generation.utils.IoUtil;

/**
 * 76. 最小覆盖子串
 * <p>
 * 给你一个字符串 s 、一个字符串 t 。
 * 返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * <p>
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * <p>
 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * 提示：
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 10^5
 * s 和 t 由英文字母组成
 * 进阶：你能设计一个在 o(m+n) 时间内解决此问题的算法吗？
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-window-substring
 * @title: 最小覆盖子串
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minWindow", "in.txt");
    }

    // 这是一道十分经典的滑动窗口题
    // 从题意看 本题只统计字符种类是否一直 如果 从 [l,r] 满足 可以尝试收缩 [l + 1,r] 这样收缩过程成就能找到满足题目要求的最小子串
    public String minWindow(String s, String t) {

        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();

        int n = ss.length, m = tt.length;
        int len = n + 1, st = -1, l = 0, r = 0;


        // 注意本题不是只有 a-z
        int[] have = new int[128];
        int type = 0;
        for (char c : tt) {
            have[c]++;
            if (have[c] == 1) {
                type++;
            }
        }
        int[] needs = new int[128];


        while (r < n) {
            char c = ss[r];

            needs[c]++;

            // 当前字符满足
            if (needs[c] == have[c]) {
                type--;
            }

            while (type == 0) {
                // 统计答案
                if (len > r - l + 1) {
                    len = r - l + 1;
                    st = l;
                }
                c = ss[l];
                // 最左侧位置字符
                if (needs[c] == have[c]) {
                    type++;
                }
                needs[c]--;
                l++;
            }


            r++;
        }


        if (len == n + 1) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = st; i < n && i < st + len; i++) {
            sb.append(ss[i]);
        }
        return sb.toString();
    }


}