package leetcode.strings;

import leetcode.utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
public class KMP {

    public static void main(String[] args) {
        String text = "ababcacadafababcd";
        String pattern = "ab";
//        System.out.println(search(text, pattern));
//        System.out.println(kmp(text, pattern));
        TestUtils.testList(search(text, pattern), kmp(text, pattern));
    }

    public static List<Integer> search(String text, String pattern) {
        List<Integer> ans = new ArrayList<>();
        if (text == null || pattern == null || text.length() < pattern.length()) {
            return ans;
        }
        int m = text.length(), n = pattern.length();
        for (int i = 0; i < m - n + 1; i++) {
            String p = text.substring(i, i + n);
            if (p.equals(pattern)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static List<Integer> kmp(String text, String pattern) {
        List<Integer> ans = new ArrayList<>();
        if (text == null || pattern == null || text.length() < pattern.length()) {
            return ans;
        }
        int m = text.length(), n = pattern.length();

        // 计算nexts数组
        int[] nexts = new int[n];
        int count = 0;
        for (int i = 1; i < n; i++) {
            while (count > 0 && pattern.charAt(count) != pattern.charAt(i)) {
                count = nexts[count - 1];
            }
            if (pattern.charAt(count) == pattern.charAt(i)) {
                count++;
            }
            nexts[i] = count;
        }

        count = 0;
        for (int i = 0; i < m; i++) {
            while (count > 0 && pattern.charAt(count) != text.charAt(i)) {
                count = nexts[count - 1];
            }
            if (pattern.charAt(count) == text.charAt(i)) {
                count++;
            }
            if (count == n) {
                ans.add(i - n + 1);
                count = nexts[count - 1];
            }
        }
        return ans;
    }
}
