package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/find-longest-awesome-substring
 * @title: 找出最长的超赞子字符串
 */
public class Code_0069_1542 {

    public static void main(String[] args) {
        // IoUtil.testUtil(Code_0069_1542.class, "longestAwesome", "txt_file\\Code_0069_1542.txt");
        // IoUtil.testUtil(Code_0069_1542.class, "longestAwesome1", "txt_file\\Code_0069_1542.txt");
        IoUtil.testUtil(Code_0069_1542.class, "longestAwesomeLongTimeError", "txt_file\\Code_0069_1542.txt");
    }


    public int longestAwesome(String s) {

        return 0;
    }


    /**
     * 暴力方法
     *
     * @param s
     * @return
     */
    public int longestAwesomeLongTimeError(String s) {
        int[] map = new int[10];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            map[c - '0']++;
        }
        int n = chars.length;
        if (check(map)) {
            return n;
        }
        int[] h = new int[10];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                h[chars[j] - '0']++;
                if (check(h)) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
            Arrays.fill(h, 0);
        }
        return ans;
    }

    public static boolean check(int[] map) {
        boolean f = true;
        for (int val : map) {
            if (val % 2 == 1) {
                if (!f) {
                    return false;
                }
                f = false;
            }
        }
        return true;
    }


    // 构造成为前缀和

    public int longestAwesome2(String s) {
        int n = s.length();
        int[][] sums = new int[n + 1][10];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int id = chars[i] - '0';
            for (int k = 0; k < 10; k++) {
                if (k != id) {
                    sums[i + 1][k] = sums[i][k];
                } else {
                    sums[i + 1][id] = sums[i][id] + 1;
                }
            }
        }
//        System.out.println(s);
//        for (int i = 0; i < chars.length; i++) {
//            System.out.println("row = " + Arrays.toString(sums[i]));
//        }
        int k = n;
        while (k > 0) {
            if (slideWindow(sums, chars, k) != -1) {
                break;
            }
            k--;
        }
        // System.out.println("======================" + s + " ans = " + k);
        return k;
    }

    public static int slideWindow(int[][] sums, char[] chars, int k) {
        int n = chars.length;
        for (int l = 0, r = k; r <= n; r++, l++) {
            if (check(l, r, sums)) {
                return k;
            }
        }
        return -1;
    }

    public static boolean check(int l, int r, int[][] sums) {
        int need = 0;
        // 如果长度偶数
        // 出现必须为偶数次数
        // 如果长度为奇数
        // 比如出现一个奇数个数
        boolean o = (r - l) % 2 == 0;
        for (int k = 0; k < 10; k++) {
            if (o) {
                if ((sums[r][k] - sums[l][k]) % 2 == 1) {
                    return false;
                }
            }
            if ((sums[r][k] - sums[l][k]) % 2 == 1) {
                need++;
                if (need > 1) {
                    return false;
                }
            }
        }
        return o || need == 1;
    }
}