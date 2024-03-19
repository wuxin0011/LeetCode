package leetcode.contest.weekly.w_200.w_232.a;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/check-if-one-string-swap-can-make-strings-equal/
 * @title
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "areAlmostEqual", "A.txt");
    }

    public boolean areAlmostEqual(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.equals(s2)) return true;
        int[] h1 = init(s1);
        int[] h2 = init(s2);
        for (int i1 = 0; i1 < h1.length; i1++) {
            if (h1[i1] != h2[i1]) {
                return false;
            }
        }
        char c1 = '\0', c2 = '\0';
        int compare = 0;
        for (int i1 = 0; i1 < s1.length(); i1++) {
            if (s1.charAt(i1) != s2.charAt(i1)) {

                if (c1 != '\0' && c2 != '\0') {
                    if (compare > 0) {
                        return false;
                    }
                    if (s1.charAt(i1) != c2 || s2.charAt(i1) != c1) {
                        return false;
                    }

                    compare++;

                }


                c1 = s1.charAt(i1);
                c2 = s2.charAt(i1);
            }
        }


        return true;
    }


    public static int[] init(String s) {
        int[] h = new int[26];
        for (int i1 = 0; i1 < s.length(); i1++) {
            h[s.charAt(i1) - 'a']++;
        }
        return h;
    }

}
