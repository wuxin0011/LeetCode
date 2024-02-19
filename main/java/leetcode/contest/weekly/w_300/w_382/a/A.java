package leetcode.contest.weekly.w_300.w_382.a;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 按键的变更次数
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"countKeyChanges");
    }

    public int countKeyChanges(String s) {
        s = s.toLowerCase();
        char pre = s.charAt(0);
        int cnt = 0;
        for (int i = 1; i < s.length(); i++) {
            if (pre == s.charAt(i)) {
                continue;
            }
            pre = s.charAt(i);
            cnt++;
        }
        return cnt;
    }
}
