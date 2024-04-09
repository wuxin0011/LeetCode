package leetcode.ox3if.slide_window.slide_0023;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/maximize-the-confusion-of-an-exam
 * @title: maximize-the-confusion-of-an-exam
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"maxConsecutiveAnswers","in.txt");
    }
     

    public int maxConsecutiveAnswers(String answerKey, int k) {
        char[] ch = answerKey.toCharArray();
        return Math.max(f(ch,k,'F'),f(ch,k,'T'));
	}

    public static  int f(char[] ch,int k,char O){
        int r = 0,l = 0;
        int n = ch.length;
        int ans = 0;
        while(r<n){
            if(ch[r]==O)k--;
            while(k<0){
                if(ch[l]==O)k++;
                l++;
            }
            ans = Math.max(ans,r - l+1);
            r++;
        }
        return ans;
    }



  

}