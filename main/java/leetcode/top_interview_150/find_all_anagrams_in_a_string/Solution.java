package leetcode.top_interview_150.find_all_anagrams_in_a_string;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * <p>
 * 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。
 * 不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 * 示例1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * <p>
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
 * 提示:
 * 1 <= s.length, p.length <= 3 * 10^4
 * s和p仅包含小写字母
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/find-all-anagrams-in-a-string
 * @title: 找到字符串中所有字母异位词
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "findAnagrams", "in.txt");
    }


    public List<Integer> findAnagrams(String s, String p) {
        int[] need = new int[26];
        int t = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            need[c - 'a']++;
            if (need[c - 'a'] == 1) t++;
        }
        int k = p.length();
        int[] have = new int[26];
        List<Integer> ans = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < s.length(); i++) {

            if (i >= k) {
                char c = s.charAt(i - k);
                id = c-'a';
                if (have[id] == need[id]) t++;
                have[id]--;

            }
            char c = s.charAt(i);
            id = c-'a';
            have[id]++;
            if (have[id] == need[id]) t--;

            if (i >= k - 1 && t == 0) {
                ans.add(i-k + 1);
            }
        }
        return ans;
    }


}