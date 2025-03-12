package leetcode.contest.weekly.w_400.w_439;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-439/problems/longest-palindromic-subsequence-after-at-most-k-operations">至多 K 次操作后的最长回文子序列</a>
 * @title: 至多 K 次操作后的最长回文子序列
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"longestPalindromicSubsequence","B.txt");
    }
     

    public int longestPalindromicSubsequence(String s, int k) {
        int[] a = s.chars().map(x->x - 'a').toArray();
        int n = a.length;
        return 0; 
	}

    private int dfs(int i,int j,int k) {
        return 0;
    }

  

}