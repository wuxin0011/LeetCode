package leetcode.contest.weekly.w_300.w_380.d;

import leetcode.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: 找出数组中的美丽下标 I
 * @url https://leetcode.cn/problems/find-beautiful-indices-in-the-given-array-i/description/
 */
@SuppressWarnings("all")
public class D {
    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "beautifulIndices", "surrounded_regions.txt");
    }

    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> ans = new ArrayList<>();
        List<Integer> a_pos = kmpSearch(s, a);
        List<Integer> b_pos = kmpSearch(s,b);
        int pre = 0;
        for(int i = 0;i<a_pos.size();i++){
            for(int j = pre;j<b_pos.size();j++){
                if(Math.abs(a_pos.get(i) - b_pos.get(j))<=k){
                    ans.add(a_pos.get(i));
                    pre = b_pos.get(j);
                    break;
                }
            }
        }
        return ans;
    }

    private static List<Integer> kmpSearch(String t, String p1) {

        List<Integer> ans = new ArrayList<>();
        if (t == null || p1 == null || t.length() < p1.length() ) return ans;
        int[] nexts1 = calcNexts(p1);
        int n = t.length(), m1 = p1.length(),cnt1 = 0;
        for (int i = 0; i < n; i++) {
            while (cnt1 > 0 && t.charAt(i) != p1.charAt(cnt1)) {
                cnt1 = nexts1[cnt1 - 1];
            }
            if (p1.charAt(cnt1) == t.charAt(i)) {
                cnt1++;
            }
            if (cnt1 == m1) {
                ans.add(i - cnt1 + 1);
                cnt1 = nexts1[cnt1 - 1];
            }
        }
        return ans;
    }

    private static int[] calcNexts(String s) {
        if (s == null) {
            return null;
        }
        int n = s.length();
        int[] nexts = new int[n];
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            while (cnt > 0 && s.charAt(cnt) != s.charAt(i)) {
                cnt = nexts[cnt - 1];
            }
            if (s.charAt(cnt) == s.charAt(i)) {
                cnt++;
            }
            nexts[i] = cnt;
        }
        return nexts;
    }



//    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
//        List<Integer> ans = new ArrayList<>();
//        List<Integer>[] lists = kmp(s, a,b);
//        List<Integer> a_pos = lists[0];
//        List<Integer> b_pos = lists[1];
//        int pre = 0;
//        for(int i = 0;i<a_pos.size();i++){
//            int idx = a_pos.get(i);
//            for(int j = pre;j<b_pos.size();j++){
//                if(Math.abs(idx-b_pos.get(j))<=k){
//                    ans.add(idx);
//                    pre = j;
//                    break;
//                }
//            }
//        }
//        return ans;
//    }
//
//    public static List<Integer>[] kmp(String t, String p1,String p2) {
//
//        List<Integer>[] ans = new ArrayList[2];
//        ans[0] =  new ArrayList<Integer>();
//        ans[1] =  new ArrayList<Integer>();
//        if (t == null || p1 == null || t.length() < p1.length() || t.length()<p2.length()) return ans;
//        int[] nexts1 = calcNexts(p1);
//        int[] nexts2 = calcNexts(p2);
//        int n = t.length(), m1 = p1.length(),m2=p2.length(),cnt1 = 0 ,cnt2 = 0;
//        for (int i = 0; i < n; i++) {
//            while (cnt1 > 0 && t.charAt(i) != p1.charAt(cnt1)) {
//                cnt1 = nexts1[cnt1 - 1];
//            }
//            if (p1.charAt(cnt1) == t.charAt(i)) {
//                cnt1++;
//            }
//            if (cnt1 == m1) {
//                ans[0].add(i - cnt1 + 1);
//                cnt1 = nexts1[cnt1 - 1];
//            }
//
//            while (cnt2> 0 && t.charAt(i) != p2.charAt(cnt2)) {
//                cnt2 = nexts2[cnt2 - 1];
//            }
//            if (p2.charAt(cnt2) == t.charAt(i)) {
//                cnt2++;
//            }
//            if (cnt2 == m2) {
//                ans[1].add(i - cnt2 + 1);
//                cnt2 = nexts2[cnt2 - 1];
//            }
//        }
//        return ans;
//    }
//
//    public static int[] calcNexts(String s) {
//        if (s == null) {
//            return null;
//        }
//        int n = s.length();
//        int[] nexts = new int[n];
//        int cnt = 0;
//        for (int i = 1; i < n; i++) {
//            while (cnt > 0 && s.charAt(cnt) != s.charAt(i)) {
//                cnt = nexts[cnt - 1];
//            }
//            if (s.charAt(cnt) == s.charAt(i)) {
//                cnt++;
//            }
//            nexts[i] = cnt;
//        }
//        return nexts;
//    }
}
