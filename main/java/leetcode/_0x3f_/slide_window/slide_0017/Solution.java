package leetcode._0x3f_.slide_window.slide_0017;

import code_generation.utils.IoUtil;

/**
 *
 * 3. 无重复字符的最长子串
 *
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 *
 * 示例1:输入: s = "abcabcbb"输出: 3 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:输入: s = "bbbbb"输出: 1解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:输入: s = "pwwkew"输出: 3解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。    请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 *
 * 提示：
 *
 *
 * 	0 <= s.length <= 5 * 104
 * 	s由英文字母、数字、符号和空格组成
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/longest-substring-without-repeating-characters
 * @title: longest-substring-without-repeating-characters
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"lengthOfLongestSubstring","in.txt");
    }
     

    public int lengthOfLongestSubstring(String s) {
        if(s==null) return 0;
        char[] ch = s.toCharArray();
        int[] have = new int[128];
        int ans = 0;
        for (int r = 0, l = 0; r < ch.length; r++) {
            have[ch[r]]++;
            while(have[ch[r]]>1){
                have[ch[l]]--;
                l++;
            }
            ans = Math.max(ans,r - l+1);
        }
        return ans;
    }

  

}