package leetcode.contest.weekly.w_400.w_443;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-443/problems/longest-palindrome-after-substring-concatenation-ii">子字符串连接后的最长回文串 II</a>
 * @title: 子字符串连接后的最长回文串 II
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "longestPalindrome", "C.txt");
    }




    public int longestPalindrome(String s, String t) {
        int ans = 0;
        ans = check(s) ? s.length() : 0;
        ans = Math.max(ans, check(t) ? t.length() : 0);
        ans = Math.max(ans,Math.max(calc(s + t), calc(t + s)));
        return ans;
    }

    public int calc(String s) {
        Map<Integer, List<Integer>> start = new HashMap<>();
        Map<Integer, List<Integer>> end = new HashMap<>();
        int[] a = s.chars().map(x -> x - 'a').toArray();
        int n = a.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = a[i];
            List<Integer> ids = start.getOrDefault(x,new ArrayList<>());
            ids.add(i);
            start.put(x,ids);
        }
        for (int i = n - 1; i >= 0; i--) {
            int x = a[i];
            List<Integer> ids = end.getOrDefault(x,new ArrayList<>());
            ids.add(i);
            end.put(x,ids);
        }

        // 以为 i 为结尾
        for(int i = n - 1;i >= 0;i--) {
            // 以为 j + 1 为开头的
            for (int j = i + 1; j < n; j++) {

            }
        }



        return ans;
    }

    public static boolean check(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    public static int lowerbound(List<Integer> ids,int x){
        int l = 0,r = ids.size() - 1;
        for(;l <= r;){
            int mid = l + ((r - l)>>1);
            if(ids.get(mid)>=x)r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }


}