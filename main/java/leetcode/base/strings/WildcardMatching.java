package leetcode.base.strings;

import code_generation.annotation.Description;
import code_generation.enums.Difficulty;
import code_generation.enums.Tag;
import code_generation.utils.InvocationHandlerMethodTime;
import code_generation.LogarithmicDevice;
import code_generation.utils.TestUtils;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description(value = "通配符匹配", url = "https://leetcode.cn/problems/wildcard-matching/", diff = Difficulty.HARD, tag = Tag.STRING)
public class WildcardMatching implements LogarithmicDevice {
    @Override
    public void logarithmicDevice() {

        TestUtils.testBoolean(isMatch("aa", "a"), false, "ok");
        TestUtils.testBoolean(isMatch("aaa", "a."), false, "ok");
        TestUtils.testBoolean(isMatch("aaa", "a*"), true, "ok");
        TestUtils.testBoolean(isMatch("aa", "*"), true, "???");
        TestUtils.testBoolean(isMatch("cb", "?a"), false, "ok");
    }

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; ++j) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }


    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(WildcardMatching.class);
    }
}
