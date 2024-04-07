package leetcode.ox3if.slide_window.slide_0000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * 给你字符串 s 和整数 k 。
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 * 英文中的 元音字母 为（a, e, i, o, u）。
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/maximum-number-of-vowels-in-a-substring-of-given-length
 * @title: maximum-number-of-vowels-in-a-substring-of-given-length
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"maxVowels","in.txt");
    }


	     public int maxVowels(String s, int k) {

	       return 0;
   		}


           public static boolean check(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
           }



}