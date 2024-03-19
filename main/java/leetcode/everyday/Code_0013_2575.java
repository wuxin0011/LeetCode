package leetcode.everyday;

import code_generation.utils.IoUtil;

/*
*
*   使用动态规划，推理当前状态
*
 */


/**
 * @author: wuxin0011
 * @Description:  读懂题目，注意溢出
 * @title 找出字符串的可整除数组
 * @url https://leetcode.cn/problems/find-the-divisibility-array-of-a-string/
 */
@SuppressWarnings("all")
public class Code_0013_2575 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0013_2575.class, "divisibilityArray", "./txt_file/Code_0013_2575.txt");
    }

    public int[] divisibilityArray(String word, int m) {
        int n  = word.length();
        int[] ans = new int[n];
        long pre = 0;
        for(int i = 0;i<n;i++){
            pre = (pre * 10L + (word.charAt(i)-'0')) %m;
            ans[i] =pre== 0 ? 1:0;
        }

        return ans;
    }


}
