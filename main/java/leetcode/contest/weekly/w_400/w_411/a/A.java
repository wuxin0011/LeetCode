package leetcode.contest.weekly.w_400.w_411.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-411/problems/count-substrings-that-satisfy-k-constraint-i
 * @title: 统计满足 K 约束的子字符串数量 I
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "countKConstraintSubstrings", "A.txt");
    }


    public int countKConstraintSubstrings(String s, int k) {
        char[] a = s.toCharArray();
        int[] map = new int[2];
        int n = a.length;
        int l = 0, r = 0;
        int ans = 0;
        while (r < n) {
            map[a[r] - '0']++;
            while (map[0] > k && map[1] > k) {
                map[a[l] - '0']--;
                l++;
            }
            ans += r - l + 1;
            r++;
        }
        return ans;
    }


}