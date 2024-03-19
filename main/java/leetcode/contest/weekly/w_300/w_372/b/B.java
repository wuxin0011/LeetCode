package leetcode.contest.weekly.w_300.w_372.b;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 区分黑球与白球
 * @url https://leetcode.cn/problems/separate-black-and-white-balls/
 */
public class B {
    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "minimumSteps", "in.txt");
    }

    public long minimumSteps(String s) {
        int cnt = 0;
        long ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                cnt++;
            } else {
                ans += cnt;
            }
        }

        return ans;
    }
}
