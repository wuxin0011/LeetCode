package leetcode.ox3if.slide_window.slide_0019;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 2730. 找到最长的半重复子字符串
 *
 * 给你一个下标从 0开始的字符串s，这个字符串只包含0到9的数字字符。如果一个字符串t中至多有一对相邻字符是相等的，那么称这个字符串 t 是 半重复的。例如，0010 、002020 、0123 、2002 和 54944 是半重复字符串，而 00101022 和 1101234883 不是。请你返回 s中最长 半重复子字符串的长度。一个 子字符串是一个字符串中一段连续 非空的字符。
 *
 * 示例 1：输入：s = "52233"输出：4解释：最长半重复子字符串是 "5223" ，子字符串从 i = 0 开始，在 j = 3 处结束。
 *
 * 示例 2：输入：s = "5494"输出：4解释：s 就是一个半重复字符串，所以答案为 4 。
 *
 * 示例 3：输入：s = "1111111"输出：2解释：最长半重复子字符串是 "11" ，子字符串从 i = 0 开始，在 j = 1 处结束。
 *
 * 提示：
 *
 *
 * 	1 <= s.length <= 50
 * 	'0' <= s[i] <= '9'
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/find-the-longest-semi-repetitive-substring
 * @title: find-the-longest-semi-repetitive-substring
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"longestSemiRepetitiveSubstring","in.txt");
    }
     

    public int longestSemiRepetitiveSubstring(String s) {
        int ans = 0;
        char[] ch = s.toCharArray();
        int r = 0,l = 0,use = 0;
        int n = ch.length;
        while(r<n){
            if(r>0 && ch[r] == ch[r-1]){
                use++;
            }
            while(use>1){
                if(l+1< n && ch[l] == ch[l+1]){
                    use--;
                }
                l++;
            }
            ans = Math.max(ans,r -l+1);
            r++;
        }

        return ans;
	}

  

}