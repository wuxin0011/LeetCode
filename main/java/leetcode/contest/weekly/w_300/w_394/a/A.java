package leetcode.contest.weekly.w_300.w_394.a;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class,IoUtil.DEFAULT_METHOD_NAME,"A.txt");
    }

    public int numberOfSpecialChars(String word) {
        char[] ws = word.toCharArray();
        int[] h = new int[52];
        int cnt = 0;
        for (char w : ws) {
            if(w>='a' && w<='z') {
                h[w-'a']++;
                if(h[w-'a']==1 && h[w-'a'+26] > 0 ) {
                    cnt++;
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
