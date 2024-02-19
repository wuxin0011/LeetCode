package leetcode.contest.weekly.w_300.w_383.d;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 将单词恢复初始状态所需的最短时II
 */
public class D {
    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"minimumTimeToInitialState");
    }
    public int minimumTimeToInitialState(String S, int k) {
        char[] s = S.toCharArray();
        int n = s.length;
        int[] z = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(z[i - l], r - i + 1);
            }
            while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
                l = i;
                r = i + z[i];
                z[i]++;
            }
            if (i % k == 0 && z[i] >= n - i) {
                return i / k;
            }
        }
        return (n - 1) / k + 1;
    }
}
