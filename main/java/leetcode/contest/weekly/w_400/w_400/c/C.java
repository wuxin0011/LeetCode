package leetcode.contest.weekly.w_400.w_400.c;

import code_generation.utils.IoUtil;

import java.util.PriorityQueue;

/**
 *
 * 100322. 删除星号以后字典序最小的字符串
 *
 *
 *               给你一个字符串s。
 * 它可能包含任意数量的'*'字符。你的任务是删除所有的'*'字符。
 * 当字符串还存在至少一个'*'字符时，你可以执行以下操作：
 * 	删除最左边的'*'字符，同时删除该星号字符左边一个字典序 最小的字符。如果有多个字典序最小的字符，你可以删除它们中的任意一个。
 * 请你返回删除所有'*'字符以后，剩余字符连接而成的 字典序最小 的字符串。
 *
 * 示例 1：
 * 输入：s = "aaba*"
 * 输出："aab"
 * 解释：
 * 删除 '*'号和它左边的其中一个'a'字符。如果我们选择删除s[3]，s字典序最小。
 *
 * 示例 2：
 * 输入：s = "abc"
 * 输出："abc"
 * 解释：
 * 字符串中没有'*'字符。
 *
 * 提示：
 * 	1 <= s.length <= 10^5
 * 	s只含有小写英文字母和'*'字符。
 * 	输入保证操作可以删除所有的'*'字符。
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-400/problems/lexicographically-minimum-string-after-removing-stars
 * @title: 删除星号以后字典序最小的字符串
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "clearStars", "C.txt");
    }


    public String clearStars(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        boolean[] vis = new boolean[n];
        // aaaaaa***
        // aaaba*
        boolean delete = false;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            char c = chs[i];
            if (c == '*') {
                vis[i] = true;
                if (!q.isEmpty()) {
                    int[] cur = q.poll();
                    vis[cur[1]] = true;
                }
                delete = true;
            } else {
                q.add(new int[]{c - 'a', i});
            }
        }
        if (!delete) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chs.length; i++) {
            if (vis[i]) continue;
            sb.append(chs[i]);
        }
        return sb.toString();
    }


}