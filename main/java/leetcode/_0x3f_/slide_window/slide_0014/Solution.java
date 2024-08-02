package leetcode._0x3f_.slide_window.slide_0014;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 438. 找到字符串中所有字母异位词
 *
 * 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 示例1:输入: s = "cbaebabacd", p = "abc"输出: [0,6]解释:起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 * 示例 2:输入: s = "abab", p = "ab"输出: [0,1,2]解释:起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 * 提示:
 *
 *
 * 	1 <= s.length, p.length <= 3 * 104
 * 	s和p仅包含小写字母
 *
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/find-all-anagrams-in-a-string
 * @title: find-all-anagrams-in-a-string
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"findAnagrams","in.txt");
    }
     

    // 这题和这题思路一模一样一样 https://leetcode.cn/problems/permutation-in-string
    public List<Integer> findAnagrams(String s, String p) {
        int[] need = new int[26];
        List<Integer> ans = new ArrayList<>();

        char[] cs1 = p.toCharArray();
        char[] cs2 = s.toCharArray();

        for (char c : cs1) {
            need[c-'a']++;
        }
        int k = cs1.length;
        for(int i = 0;i<cs2.length;i++){
            if(i>=k){
                need[cs2[i-k]-'a']++;
            }
            need[cs2[i]-'a']--;
            if(i>=k-1 && check(need)){
                ans.add(i-k+1);
            }
        }

        return ans;
	}


    public  static boolean check(int[] arr){
        for (int i : arr) {
            if(i!=0) return false;
        }
        return true;
    }





}