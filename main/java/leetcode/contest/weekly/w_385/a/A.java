package leetcode.contest.weekly.w_385.a;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class);
    }

    public int countPrefixSuffixPairs(String[] words) {
        int cnt = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].length() > words[j].length()) {
                    continue;
                }
                if (words[j].startsWith(words[i]) && words[j].endsWith(words[i])) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

}
