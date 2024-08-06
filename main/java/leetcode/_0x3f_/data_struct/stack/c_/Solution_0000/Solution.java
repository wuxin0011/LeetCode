package leetcode._0x3f_.data_struct.stack.c_.Solution_0000;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-score-from-removing-substrings
 * @title: 删除子字符串的最大得分
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maximumGain", "in.txt");
    }


    public int maximumGain(String ss, int x, int y) {
        char[] a = ss.toCharArray();
        int n = a.length, ans = 0;
        char[] sk = new char[n];
        int size = 0;
        // 先删除得分较大的
        for (char c : a) {
            if (c == 'a' && size > 0 && sk[size - 1] == 'b' && y > x) {
                size--;
                ans += y;
            } else if (c == 'b' && size > 0 && sk[size - 1] == 'a' && x > y) {
                size--;
                ans += x;
            } else {
                sk[size++] = c;
            }
        }
        char[] temp = new char[size];
        // 再从分数小数较小 但是可能构成能够删除字符中和删除
        for (int i = 0, j = 0; i < size; i++) {
            char c = sk[i];
            if (c == 'b' && j > 0 && temp[j - 1] == 'a') {
                ans += x;
                j--;
            } else if (c == 'a' && j > 0 && temp[j - 1] == 'b') {
                ans += y;
                j--;
            } else {
                temp[j++] = c;
            }
        }
        return ans;
    }


}