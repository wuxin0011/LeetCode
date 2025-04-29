package leetcode.contest.biweekly.bi_100.bi_155;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/biweekly-contest-155/problems/count-cells-in-overlapping-horizontal-and-vertical-substrings">统计水平子串和垂直子串重叠格子的数目</a>
 * @title: 统计水平子串和垂直子串重叠格子的数目
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "countCells", "C.txt");
    }


    public int countCells(char[][] grid, String p) {
        int m = grid.length, n = grid[0].length;
        char[] pattern = p.toCharArray();
        char[] text = new char[m * n];
        int[][] vis = new int[m][n];
        int k = 0, nxt = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                text[k++] = grid[i][j];
            }
        }
        List<Integer> row = kmpList(text, pattern);

        k = 0;
        nxt = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int dis = i * n + j;
                if (k < row.size() && row.get(k) == dis) {
                    nxt = dis + pattern.length;
                    k++;
                }
                if (dis < nxt) {
                    vis[i][j]++;
                }
            }
        }

        k = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                text[k++] = grid[i][j];
            }
        }
        List<Integer> col = kmpList(text, pattern);

        k = 0;
        nxt = -1;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                int dis = j * m + i;
                if (k < col.size() && col.get(k) == dis) {
                    nxt = dis + pattern.length;
                    k++;
                }
                if (dis < nxt) {
                    vis[i][j]++;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (vis[i][j] > 1) ans++;
            }
        }
        return ans;
    }

    public static List<Integer> kmpList(char[] text, char[] pattern) {
        List<Integer> ans = new ArrayList<>();
        if (text == null || pattern == null || text.length < pattern.length) {
            return ans;
        }
        int n = text.length, m = pattern.length;
        int[] nexts = new int[m];
        for (int i = 1, cnt = 0; i < m; i++) {
            while (cnt > 0 && pattern[i] != pattern[cnt]) {
                cnt = nexts[cnt - 1];
            }
            if (pattern[i] == pattern[cnt]) {
                cnt++;
            }
            nexts[i] = cnt;
        }
        for (int i = 0, cnt = 0; i < n; i++) {
            while (cnt > 0 && text[i] != pattern[cnt]) {
                cnt = nexts[cnt - 1];
            }
            if (text[i] == pattern[cnt]) {
                cnt++;
            }
            if (cnt == m) {
                ans.add(i - m + 1);
                cnt = nexts[cnt - 1];
            }
        }
        return ans;
    }


}