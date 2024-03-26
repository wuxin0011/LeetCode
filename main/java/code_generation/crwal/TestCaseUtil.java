package code_generation.crwal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
public class TestCaseUtil {


    /**
     * 构造一个不完整的标签 div => [<div,</div>]
     * <p>
     * 为什么不是？ [<div>,</div>]
     * 因为 <div class="xxxxx" ></div> 中间空格不匹配
     *
     * @param tagName
     * @return
     */
    public static String[] buildStartTagAndEngTag(String tagName) {
        String start = "<" + tagName;
        String end = "</" + tagName + ">";
        return new String[]{start, end};
    }


    public static boolean isIgnore(char c) {
        return c == '\r' || c == '\n' || c == '\t' || c == '\b' || c == '\f' || c == '\0' || c == '\\' || c == ' ' || c == '\'' || c == '\"';
    }

    /**
     * 解析周赛案例
     *
     * @param input     输入的内容
     * @param startFlag 开始
     * @param endFlag   结束
     * @param ans       结果
     */
    public static void startParseContestTestCase(String input, String startFlag, String endFlag, List<String> ans) {
        char[] charArray = input.toCharArray();
        int deep = 0;
        StringBuilder sb = null;
        for (char c : charArray) {
            if (isIgnore(c)) {
                continue;
            }
            switch (c) {
                case '=':
                case ':':
                case '\uFF1A':
                    sb = new StringBuilder();
                    break;
                case '[':
                case '{':
                case '\u3010':
                    deep++;
                    sb = new StringBuilder();
                    sb.append(c);
                    break;
                case ']':
                case '}':
                case '\u3011':
                    deep--;
                    if (deep == 0) {
                        if (sb != null) {
                            sb.append(c);
                            ans.add(sb.toString());
                            sb = null;
                        }
                    }
                    break;
                case ',':
                case '\uFF0C':
                    if (sb != null) {
                        if (deep == 0) {
                            ans.add(sb.toString());
                            sb = new StringBuilder();
                        } else {
                            sb.append(c);
                        }
                    }
                    break;
                default:
                    if (sb != null) {
                        sb.append(c);
                    }
            }


        }
        if (sb != null) {
            ans.add(sb.toString());
            sb = null;
        }

    }

    /**
     * @param input             输入的内容
     * @param classSelectorFlag 标签类名
     * @param start             搜索位置
     * @param tagName           HTML 标签名称 如 div span p 等
     * @return 返回查找的内容
     */
    public static String getTagContent(String input, String classSelectorFlag, int start, String tagName) {
        String[] strings = buildStartTagAndEngTag(tagName);
        return getTagContent(input, classSelectorFlag, start, strings[0], strings[1]);
    }


    /**
     * 解析标签内容
     *
     * @param input             输入的字符串
     * @param classSelectorFlag 查找的标签类名
     * @param start             其实位置
     * @param startTag          匹配其实标签
     * @param endTag            匹配结束位置信息
     * @return 返回匹配后的内容
     */
    public static String getTagContent(String input, String classSelectorFlag, int start, String startTag, String endTag) {
        if (input == null || startTag == null || endTag == null) {
            throw new RuntimeException("input content is null or tag is null");
        }
        if (input.length() < startTag.length() || input.length() < endTag.length()) {
            throw new RuntimeException("input length not ");
        }
        int stIdx = kmpSearch(input, start, classSelectorFlag);
        if (stIdx == -1) {
            //throw new RuntimeException("input content Not find " + classSelectorFlag);
            return input;
        }
        int slen = startTag.length();
        int elen = endTag.length();
        int deep = 0;
        boolean findTag = false;
        int findSt = stIdx;

        // 检查是否包含标签名称
        if (!classSelectorFlag.contains(startTag)) {
            for (int i = stIdx; i >= 0; i--) {
                String s1 = input.substring(i, i + slen);
                if (s1.equals(startTag)) {
                    findSt = i;
                    deep++;
                    break;
                }
            }
        }
        for (int i = findSt; i < input.length(); i++) {
            String s1 = input.substring(i, i + slen);
            if (startTag.equals(s1)) {
                deep++;
                findTag = true;
                continue;
            }
            String s2 = input.substring(i, i + elen);
            if (endTag.equals(s2)) {
                deep--;
            }
            if (deep == 0 && findTag) {
                return input.substring(findSt, i);
            }
        }
        return "";
    }


    public static List<Integer> kmpSearchList(String text, String pattern) {
        int id = 0;
        List<Integer> ans = new ArrayList<>();
        while (true) {
            id = kmpSearch(text, id == 0 ? id : id + pattern.length(), pattern);
            if (id == -1) {
                break;
            }
            ans.add(id);
        }
        return ans;
    }


    public static int kmpSearch(String text, String pattern) {
        return kmpSearch(text, 0, pattern);
    }

    public static int kmpSearch(String text, int st, String pattern) {
        if (pattern == null || text == null || pattern.length() > text.length()) {
            return -1;
        }
        if (st < 0 || st >= text.length()) {
            throw new RuntimeException("out");
        }
        int[] next = new int[pattern.length()];
        for (int i = 1, cnt = 0; i < pattern.length(); i++) {
            while (cnt > 0 && pattern.charAt(i) != pattern.charAt(cnt)) {
                cnt = next[cnt - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(cnt)) {
                cnt++;
            }
            next[i] = cnt;
        }
        for (int i = st, cnt = 0; i < text.length(); i++) {
            while (cnt > 0 && text.charAt(i) != pattern.charAt(cnt)) {
                cnt = next[cnt - 1];
            }
            if (text.charAt(i) == pattern.charAt(cnt)) {
                cnt++;
            }
            if (cnt == pattern.length()) {
                return i - pattern.length() + 1; // 找到第一个内容
            }
        }
        return -1;
    }


    public static List<String> parseDefaultTextCase(String input) {
        List<String> ans = new ArrayList<>();
        int i1 = TestCaseUtil.kmpSearch(input, "=");
        List<Integer> strongList = TestCaseUtil.kmpSearchList(input, "<strong>");
        int end = strongList.size() >= 3 ? strongList.get(2) : -1;
        if (end == -1) {
            throw new RuntimeException("error");
        }
        // System.out.println(strongList + ":end = " + end + ",end = " + (input.length() - 1));
        StringBuilder sb = null;
        int deep = 0, args = 0;
        for (int i = i1; i < end; i++) {
            char c = input.charAt(i);
            if (TestCaseUtil.isIgnore(c)) {
                if (sb != null && i == end - 1) {
                    ans.add(sb.toString());
                    sb = null;
                }
                continue;
            }

            switch (c) {
                case '<':
                    if (args > 0 && sb != null) {
                        ans.add(sb.toString());
                        args--;
                        sb = null;
                    }
                case '=':
                case '>':
                case ':':
                    if (c == '=') {
                        args++;
                    }
                    sb = new StringBuilder();
                    break;
                case '[':
                    deep++;
                    if (sb != null) {
                        sb.append(c);
                    }
                    break;
                case ']':
                    deep--;
                    if (sb != null) {
                        sb.append(c);
                    }
                    if (deep == 0 && sb != null) {
                        ans.add(sb.toString());
                        if (args > 0) args--;
                        sb = null;
                    }
                    break;
                case ',':
                    if (sb != null) {
                        if (deep == 0) {
                            ans.add(sb.toString());
                            if (args > 0) args--;
                            sb = null;
                        } else {
                            sb.append(c);
                        }
                    }
                    break;
                default:
                    if (sb != null) {
                        sb.append(c);
                    }
                    break;
            }
        }
        for (int i = 0; i < ans.size(); i++) {
            if (i == ans.size() - 1) {
                System.out.println("result  = " + ans.get(i));
            } else {
                System.out.println("args" + i + " = " + ans.get(i));
            }
        }
        return ans;
    }


    public static String testCaseToString(List<String> ans) {
        StringBuilder sb = new StringBuilder();
        if (ans == null || ans.size() == 0) {
            return sb.toString();
        }
        for (int i = 0; i < ans.size(); i++) {
            sb.append(ans.get(i));
            if (i != ans.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }


}
