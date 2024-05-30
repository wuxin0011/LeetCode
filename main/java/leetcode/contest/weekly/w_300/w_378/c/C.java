package leetcode.contest.weekly.w_300.w_378.c;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-378/problems/find-longest-special-substring-that-occurs-thrice-ii
 * @title: 找出出现至少三次的最长特殊子字符串 II
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "maximumLength", "C.txt");
    }


    public int maximumLength(String s) {
        char[] chs = s.toCharArray();
        int l = 0, r = chs.length - 1;
        // 分析
        // 如果长度为0 肯定满足 空
        // 假设最长长度为 K 如果k存在 k - 1 一定存在
        // 从 0 -> k-1 - > k 具有单调性
        // 显然具有单调性
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (check(mid, chs)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        // System.out.println("r =  " + r + ",s = " + s);
        return r == 0 ? -1 : r;
    }


    public static boolean check(int t, char[] cs) {
        int n = cs.length;
        int ans = 0;
        int[] h = new int[26];
        for (int i = 0; i < n; ) {
            int j = i + 1;
            while (j < n && cs[i] == cs[j]) {
                j++;
            }
            int id = cs[i] - 'a';
            // j - i 为相同字符串长度
            // L - t 是否符合题目意思
            h[id] += Math.max(j - i - t + 1, 0);
            ans = Math.max(ans, h[id]);
            i = j;
        }
        return ans >= 3;
    }


}