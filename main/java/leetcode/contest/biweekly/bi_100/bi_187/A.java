package leetcode.contest.biweekly.bi_100.bi_187;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;
import java.util.*;
/**
 * @author: qitongwei
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-187/problems/rearrange-string-to-avoid-character-pair">重新排列字符串以避免字符对</a>
 * @title: 重新排列字符串以避免字符对
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"rearrangeString","A.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    
    public String rearrangeString(String s, char x, char y) {
        String ans = "";
        for(int i = 0;i < s.length();i++){
            if(s.charAt(i) ==  y){
                ans += y;
            }
        }
        for(int i = 0;i < s.length();i++){
            if(s.charAt(i) !=  y){
                ans += s.charAt(i);
            }
        }
        return ans;
	}

  

}