package leetcode.contest.weekly.w_400.w_417.c;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-417/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii
 * @title: 元音辅音字符串计数 II
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"countOfSubstrings","C.txt");
    }


    // 恰好k = 至少k - 至少k+1

    public long countOfSubstrings(String word, int k) {
        char[] a = word.toCharArray();
        return (calc(a, k) - calc(a, k + 1));
    }

    public static boolean isVol(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }


    public static long calc(char[] a, int k) {
        long ans = 0;
        int[] cnt = new int[128];
        int n = a.length;
        int l = 0, r = 0, type = 0;
        int cntK = 0;
        while (r < n) {
            cnt[a[r]]++;
            if (isVol(a[r]) && cnt[a[r]] == 1) {
                type++;
            }
            if (!isVol(a[r])) {
                cntK++;
            }
            while (type == 5 && cntK >= k) {
                ans += (n - r);
                cnt[a[l]]--;
                if (isVol(a[l]) && cnt[a[l]] == 0) {
                    type--;
                }
                if (!isVol(a[l])) {
                    cntK--;
                }
                l++;
            }
            r++;
        }
        return ans;
    }

  

}