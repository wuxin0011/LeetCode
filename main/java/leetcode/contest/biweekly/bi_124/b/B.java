package leetcode.contest.biweekly.bi_124.b;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 进行操作使字符串为空
 */
public class B {
    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"lastNonEmptyString");
    }

    public String lastNonEmptyString(String s) {
        int[] h = new int[26];
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            h[idx]++;
            max = Math.max(max, h[idx]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            int idx = s.charAt(i) - 'a';
            if (h[idx] == max) {
                sb.insert(0, s.charAt(i));
                h[idx] = 0;
            }
        }
        return sb.toString();
    }
}
