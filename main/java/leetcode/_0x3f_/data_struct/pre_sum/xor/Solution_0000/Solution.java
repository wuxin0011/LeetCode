package leetcode._0x3f_.data_struct.pre_sum.xor.Solution_0000;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/can-make-palindrome-from-substring
 * @title: 构建回文串检测
 */
public class Solution {
    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "canMakePaliQueries", "in.txt");
        }


        public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
            int n = s.length();
            int[][] sums = new int[n + 1][26];
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                sums[i + 1] = sums[i].clone();
                sums[i + 1][c - 'a'] = sums[i][c - 'a'] ^ (c);
            }
            List<Boolean> ans = new ArrayList<>();
            n = queries.length;
            for (int i = 0; i < n; i++) {
                int l = queries[i][0], r = queries[i][1], k = queries[i][2];
                int v = query(sums, l, r);
                // System.out.println(v);
                ans.add(v / 2 <= k);
            }
            return ans;
        }


        static int query(int[][] sums, int l, int r) {
            if (l == r) {
                return 0;
            }
            r++;
            int x = 0;
            for (int i = 0; i < 26; i++) {
                if ((sums[r][i] ^ sums[l][i]) != 0) {
                    x++;
                }
            }
            return x;
        }
    }


    static class S2 {
        public static void main(String[] args) {
            IoUtil.testUtil(S2.class, "canMakePaliQueries", "in.txt");
        }


        public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
            int n = s.length();
            int[][] sums = new int[n + 1][26];
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                sums[i + 1] = sums[i].clone();
                sums[i + 1][c - 'a']++;
            }
            List<Boolean> ans = new ArrayList<>();
            n = queries.length;
            for (int i = 0; i < n; i++) {
                int l = queries[i][0], r = queries[i][1], k = queries[i][2];
                int v = query(sums, l, r);
                // System.out.println(v);
                ans.add(v / 2 <= k);
            }
            return ans;
        }


        static int query(int[][] sums, int l, int r) {
            if (l == r) {
                return 0;
            }
            r++;
            int x = 0;
            for (int i = 0; i < 26; i++) {
                x += (sums[r][i] - sums[l][i]) % 2;
            }
            return x;
        }
    }


}