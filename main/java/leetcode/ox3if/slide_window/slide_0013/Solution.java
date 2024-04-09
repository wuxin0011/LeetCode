package leetcode.ox3if.slide_window.slide_0013;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 567. 字符串的排列
 *
 * 给你两个字符串s1和s2 ，写一个函数来判断 s2 是否包含 s1的排列。如果是，返回 true ；否则，返回 false 。换句话说，s1 的排列之一是 s2 的 子串 。
 *
 * 示例 1：输入：s1 = "ab" s2 = "eidbaooo"输出：true解释：s2 包含 s1 的排列之一 ("ba").
 *
 * 示例 2：输入：s1= "ab" s2 = "eidboaoo"输出：false
 *
 * 提示：
 *
 *
 * 	1 <= s1.length, s2.length <= 104
 * 	s1 和 s2 仅包含小写字母
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/permutation-in-string
 * @title: permutation-in-string
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"checkInclusion","in.txt");
    }
     

    public boolean checkInclusion(String s1, String s2) {

        // 先计算 S1 有多少中排列 然后在S2中查找
        // 因此本题本质之一就是全排列 但是全部枚举 然后在比对必定会浪费消耗空间
        // 由于不注重顺序 但是全部都是小写字母 因此可以用滑动窗口代替 窗口大小固定为 s1 长度

        int[] need = new int[26];

        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();

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
                return true;
            }
        }

        return false;
    }


    public  static boolean check(int[] arr){
        for (int i : arr) {
            if(i!=0) return false;
        }
        return true;
    }

  

}