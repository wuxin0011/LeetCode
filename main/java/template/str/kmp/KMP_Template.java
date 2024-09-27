package template.str.kmp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */

@SuppressWarnings("all")
public class KMP_Template {


    /**
     * 匹配数字
     * @param text
     * @param pattern
     * @return
     */
    public static List<Integer> kmpList(int[] text, int[] pattern) {
        List<Integer> ans = new ArrayList<>();
        if (text == null || pattern == null || text.length < pattern.length) {
            return ans;
        }
        int n = text.length, m = pattern.length;
        int[] nexts = new int[m];
        for (int i = 1, cnt = 0; i < m; i++) {
            while (cnt > 0 && pattern[i] != pattern[cnt]) {
                cnt = nexts[cnt - 1];
            }
            if (pattern[i] == pattern[cnt]) {
                cnt++;
            }
            nexts[i] = cnt;
        }
        for (int i = 0, cnt = 0; i < n; i++) {
            while (cnt > 0 && text[i] != pattern[cnt]) {
                cnt = nexts[cnt - 1];
            }
            if (text[i] == pattern[cnt]) {
                cnt++;
            }
            if (cnt == m) {
                ans.add(i - m + 1);
                cnt = nexts[cnt - 1];
            }
        }
        return ans;
    }


    /**
     * 匹配字符串 多个
     * @param text
     * @param pattern
     * @return
     */
    public static List<Integer> kmpList(String text, String pattern) {
        return kmpList(text.toCharArray(), pattern.toCharArray());
    }

    public static List<Integer> kmpList(char[] text, char[] pattern) {
        List<Integer> ans = new ArrayList<>();
        if (text == null || pattern == null || text.length < pattern.length) {
            return ans;
        }
        int n = text.length, m = pattern.length;
        int[] nexts = new int[m];
        for (int i = 1, cnt = 0; i < m; i++) {
            while (cnt > 0 && pattern[i] != pattern[cnt]) {
                cnt = nexts[cnt - 1];
            }
            if (pattern[i] == pattern[cnt]) {
                cnt++;
            }
            nexts[i] = cnt;
        }
        for (int i = 0, cnt = 0; i < n; i++) {
            while (cnt > 0 && text[i] != pattern[cnt]) {
                cnt = nexts[cnt - 1];
            }
            if (text[i] == pattern[cnt]) {
                cnt++;
            }
            if (cnt == m) {
                ans.add(i - m + 1);
                cnt = nexts[cnt - 1];
            }
        }
        return ans;
    }


    /**
     * 查找第一个
     * @param text
     * @param pattern
     * @return
     */
    public static int kmpSearch(String text, String pattern) {
        if (text == null || pattern == null || pattern.length() > text.length()) {
            return -1;
        }
        return kmp(text.toCharArray(),pattern.toCharArray());
    }

    public static int kmp(char[] text, char[] pattern) {
        if (text == null || pattern == null || text.length < pattern.length) {
            return -1;
        }
        int n = text.length, m = pattern.length;
        int[] nexts = new int[m];
        for (int i = 1, cnt = 0; i < m; i++) {
            while (cnt > 0 && pattern[i] != pattern[cnt]) {
                cnt = nexts[cnt - 1];
            }
            if (pattern[i] == pattern[cnt]) {
                cnt++;
            }
            nexts[i] = cnt;
        }
        for (int i = 0, cnt = 0; i < n; i++) {
            while (cnt > 0 && text[i] != pattern[cnt]) {
                cnt = nexts[cnt - 1];
            }
            if (text[i] == pattern[cnt]) {
                cnt++;
            }
            if (cnt == m) {
                return i - m + 1;
            }
        }
        return -1; // Not found !
    }

    public static void main(String[] args) {

    }


}
