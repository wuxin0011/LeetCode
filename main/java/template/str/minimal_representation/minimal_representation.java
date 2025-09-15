package template.str.minimal_representation;

import code_generation.utils.RandomArrayUtils;

/**
 * 最小表示法
 *
 * @author: wuxin0011
 * @Description: 给定一个字符串求最小字典序的位置 （这个字符串是循环的）
 */
public class minimal_representation {

    public static void main(String[] args) {
        boolean ok = true;
        for (int T = 100; T > 0; T--) {
            String S = RandomArrayUtils.randomString(10, 30, 'a', 'z');
//            System.out.println(getMinPos(S));
//            System.out.println(test(S));
            if (!getMinExpression(S).equals(test(S))) {
                ok = false;
                break;
            }
        }
        System.out.println(ok ? "ok" : "error");
    }

    public static String getMinExpression(String S) {
        char[] a = (S + S).toCharArray();
        int n = a.length >> 1;
        int i = 0, j = 1, k = 0;
        while (i < n && j < n) {
            k = 0;
            while (k < n && a[i + k] == a[j + k]) {
                k++;
            }
            if (k == n) break;
            if (a[i + k] < a[j + k]) {
                j = j + k + 1;
            } else {
                i = i + k + 1;
            }
            if (i == j) {
                j++;
            }
        }
        return new String(a, Math.min(i, j), n);
    }


    // 暴力方法用于测试
    public static String test(String S) {
        int n = S.length();
        String ans = "";
        for (int i = 0; i < n * 2; i++) {
            String cur = "";
            for (int k = 0; k < n; k++) {
                cur += S.charAt((i + k) % n);
            }
            if ("".equals(ans) || ans.compareTo(cur) > 0) {
                ans = cur;
            }
        }
        return ans;
    }
}
