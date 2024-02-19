package leetcode.base.strings;

import leetcode.annotation.Description;
import leetcode.utils.Bean.Difficulty;
import leetcode.utils.Bean.Tag;
import leetcode.utils.InvocationHandlerMethodTime;
import leetcode.utils.LogarithmicDevice;
import leetcode.utils.TestUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description(value = "无重复字符的最长子串", url = "https://leetcode.cn/problems/longest-substring-without-repeating-characters/", diff = Difficulty.MEDIUM, tag = Tag.STRING)
public class LengthOfLongestSubstring implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(LengthOfLongestSubstring.class);
    }

    /**
     * 解法是 利用 set 去重 如果遇到重复 删除元素 ， 同时指针向后移动
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> ss = new HashSet<>();
        int i = 0, ans = 0;
        for (int j = 0; j < s.length(); ++j) {
            char c = s.charAt(j);
            while (ss.contains(c)) {
                ss.remove(s.charAt(i++));
            }
            ss.add(c);
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }

    @Override
    public void logarithmicDevice() {
        String str = "bbbbb";
        TestUtils.testBoolean(1, lengthOfLongestSubstring(str));
        str = "pwwkew";
        TestUtils.testBoolean(3, lengthOfLongestSubstring(str));
        str = "abcabcbbv";
        TestUtils.testBoolean(3, lengthOfLongestSubstring(str));
    }
}
