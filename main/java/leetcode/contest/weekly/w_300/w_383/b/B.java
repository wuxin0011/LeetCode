package leetcode.contest.weekly.w_300.w_383.b;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 将单词恢复初始状态所需的最短时I
 */
public class B {
    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "minimumTimeToInitialState");
    }

    public int minimumTimeToInitialState(String word, int k) {
        int tot = 1;
        int n = word.length();
        for (int i = k; i < n; i += k) {
            String s = word.substring(i, n);
            if (word.startsWith(s)) {
                break;
            } else {
                tot += 1;
            }
        }
        return tot;
    }
}
