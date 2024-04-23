package leetcode.contest.weekly.w_300.w_394.b;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-394/problems/count-the-number-of-special-characters-ii
 * @title: 统计特殊字母的数量II
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"numberOfSpecialChars","B.txt");
    }


    public int numberOfSpecialChars(String word) {
        char[] ws = word.toCharArray();
        int[] h = new int[52];
        boolean[] vis = new boolean[26];
        int cnt = 0;
        for (char w : ws) {
            if(w>='a' && w<='z') {
                h[w-'a']++;
                if(h[w-'a']==1 && h[w-'a'+26]>0){
                    cnt++;
                }
                if(h[w-'a'+26] > 0 && !vis[w-'a'] ) {
                    cnt--;
                    vis[w-'a'] = true;
                }
            }else{
                h[w-'A'+26]++;
                if(h[w-'A'+26]==1 && h[w-'A'] > 0 ) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
  

}