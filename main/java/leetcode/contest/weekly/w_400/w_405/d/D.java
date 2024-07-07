package leetcode.contest.weekly.w_400.w_405.d;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-405/problems/construct-string-with-minimum-cost
 * @title: 最小代价构造字符串
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "minimumCost", "D.txt");
    }


    char[] path;
    int l, n;
    int[] cnts;

    public int minimumCost(String target, String[] words, int[] costs) {
        cnts = new int[26];
        l = target.length();
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            cnts[c - 'a']++;
        }
        return 0;
    }

    public static class Node {
        String w;
        int c;
        int[] map;

        public Node(String w, int c) {
            this.w = w;
            this.c = c;
            map = new int[26];
            for (int i = 0; i < w.length(); i++) {
                map[w.charAt(i) - 'a']++;
            }
        }
    }


}