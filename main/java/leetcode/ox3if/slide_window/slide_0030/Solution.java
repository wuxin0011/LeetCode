package leetcode.ox3if.slide_window.slide_0030;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 2781. 最长合法子字符串的长度
 *
 * 给你一个字符串word和一个字符串数组forbidden。如果一个字符串不包含forbidden中的任何字符串，我们称这个字符串是合法的。请你返回字符串 word的一个 最长合法子字符串的长度。子字符串 指的是一个字符串中一段连续的字符，它可以为空。
 *
 * 示例 1：输入：word = "cbaaaabc", forbidden = ["aaa","cb"]输出：4解释：总共有 11 个合法子字符串："c", "b", "a", "ba", "aa", "bc", "baa", "aab", "ab", "abc" 和 "aabc"。最长合法子字符串的长度为 4 。其他子字符串都要么包含 "aaa" ，要么包含 "cb" 。
 *
 * 示例 2：输入：word = "leetcode", forbidden = ["de","le","e"]输出：4解释：总共有 11 个合法子字符串："l" ，"t" ，"c" ，"o" ，"d" ，"tc" ，"co" ，"od" ，"tco" ，"cod" 和 "tcod" 。最长合法子字符串的长度为 4 。所有其他子字符串都至少包含 "de" ，"le" 和 "e" 之一。
 *
 * 提示：
 *
 *
 * 	1 <= word.length <= 105
 * 	word只包含小写英文字母。
 * 	1 <= forbidden.length <= 105
 * 	1 <= forbidden[i].length <= 10
 * 	forbidden[i]只包含小写英文字母。
 *
 *
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/length-of-the-longest-valid-substring
 * @title: length-of-the-longest-valid-substring
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"longestValidSubstring","in.txt");
    }
     

    public int longestValidSubstring(String word, List<String> forbidden) {    

        return 0; 
	}

  

}