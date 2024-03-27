package code_generation.utils;

import code_generation.crwal.leetcode.BuildUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: wuxin0011
 * @Description:
 */
public class StringUtils {

    public static final String mod_unicode = "\\u53d6\\u4f59";
    public static final String mod_ans_unicode = "\\u7b54\\u6848\\u53ef\\u80fd\\u5f88\\u5927";


    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean strictIsEmpty(String s) {
        return s == null || s.length() == 0 || "null".equals(s) || "#".equals(s);
    }

    public static boolean strictIsEmpty(String s, String... nullstr) {
        if (isEmpty(s)) {
            return true;
        }
        for (String s1 : nullstr) {
            if (!isEmpty(s1) && s1.equals(s)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isIgnore(char c) {
        return c == '\r' || c == '\n' || c == '\t' || c == '\b' || c == '\f' || c == '\0' || c == '\\' || c == ' ' || c == '\'' || c == '\"';
    }

    public static boolean isIgnoreStrict(char c) {
        return isIgnore(c) || isIgnoreStrict(c, '#');
    }

    public static boolean isIgnoreStrict(char c, char... chars) {
        if (isIgnore(c)) {
            return true;
        }
        for (char c1 : chars) {
            if (c1 == c) {
                return true;
            }
        }
        return false;
    }


    public static String replaceIgnoreContent(String s) {
        if (isEmpty(s)) {
            return s;
        }
        s = s.replace("<div>", "").replace("</div>", "");
        s = s.replace("<ul>", "").replace("</ul>", "");
        s = s.replace("<li>", "").replace("</li>", "");
        s = s.replace("<span>", "").replace("</span>", "");
        s = s.replace("<u>", "").replace("</u>", "");
        s = s.replace("<p>", "").replace("</p>", "");
        s = s.replace("<h>", "").replace("</h>", "");
        return s;
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


    public static String ingoreString(String input) {
        if (input == null || input.length() == 0) {
            throw new NullPointerException("input content is null");
        }

        char[] charArray = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            if (StringUtils.isIgnoreStrict(c)) continue;
            sb.append(c);
        }
        return sb.toString();
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


//    public static String jsonStrGetValueByKey(String jsonStr, String key) {
//        key = "\"" + key + "\"";
//        int find = jsonStr.indexOf(key);
//        if (find == -1) {
//            System.out.println("Not find key " + key);
//            return "";
//        }
//        int startIndex = find + key.length();
//
//        StringBuilder sb = null;
//        int deep = 0;
//        for (int i = startIndex; i < jsonStr.length(); i++) {
//            char c = jsonStr.charAt(i);
//            if (c == ':') {
//                sb = new StringBuilder();
//            } else if (c == ' ') {
//                // ignore ...
//            } else if (c == ',') {
//                break;
//            } else if (c == '\"' || c == '\'') {
//                deep++;
//                if (deep == 2) {
//                    break;
//                }
//            } else {
//                if (sb != null) {
//                    sb.append(c);
//                }
//            }
//        }
//        String s = sb == null ? "" : sb.toString();
//        if (s.length() == 0) {
//            return s;
//        } else {
//            int endIndex = jsonStr.indexOf(",", startIndex);
//            if (endIndex == -1) {
//                endIndex = jsonStr.indexOf("}", startIndex);
//            }
//            return jsonStr.substring(startIndex, endIndex).replace(":", "").replaceAll("\"", "");
//        }
//
//    }


    public static String wrapperKey(String key) {
        if (isEmpty(key)) {
            return key;
        }
        if (!key.startsWith("\"")) {
            key = "\"" + key;
        }
        if (!key.endsWith("\"")) {
            key = key + "\"";
        }
        return key;
    }

    public static String jsonStrGetValueByKey(String jsonStr, String key) {
        key = wrapperKey(key);
        int find = jsonStr.indexOf(key);
        if (find == -1) {
            System.out.println("Not find key " + key);
            return "";
        }
        int startIndex = find + key.length();

        StringBuilder sb = new StringBuilder();
        boolean foundValue = false;
        boolean inQuotes = false;
        Stack<Character> stack = new Stack<>();

        for (int i = startIndex; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (c == '\"' && jsonStr.charAt(i - 1) != '\\') {
                inQuotes = !inQuotes;
            }
            if (!inQuotes) {
                if (c == '{' || c == '[' || c == '(') {
                    stack.push(c);
                } else if (c == '}' || c == ']' || c == ')') {
                    sb.append(c);
                    if (stack.isEmpty() || !isMatchingPair(stack.peek(), c)) {
                        break;
                    }
                    stack.pop();
                } else if (c == ':' && stack.isEmpty()) {
                    foundValue = true;
                } else if (c == ',' && stack.isEmpty()) {
                    break;
                }
            }
            if (foundValue && !Character.isWhitespace(c)) {
                sb.append(c);
            }
        }
        return sb.toString().replaceAll("\"", "").replace(":", "");
    }

    private static boolean isMatchingPair(char left, char right) {
        return (left == '{' && right == '}') || (left == '[' && right == ']') || (left == '(' && right == ')');
    }


    public static String parseId(String url) {
        Pattern compile = Pattern.compile("\\d+");
        Matcher matcher = compile.matcher(url);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";

    }


    public static String parseCodeSnippets(String input, String pattern) {
        return parseCodeSnippets(input, pattern, '{', '}');
    }


    /**
     * 解析代码块
     *
     * @param input
     * @param pattern
     * @param st
     * @param ed
     * @return
     */
    public static String parseCodeSnippets(String input, String pattern, char st, char ed) {
        int i = kmpSearch(input, pattern);
        if (i == -1) {
            throw new RuntimeException("Not fond CodeSnippets " + pattern);
        }
        StringBuilder sb = new StringBuilder();
        int deep = 0;
        i += pattern.length();
        for (; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '\"') {
                continue;
            }
            if (c == st) {
                deep++;
            } else if (c == ed) {
                deep--;
                if (deep == 0) {
                    break;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }


    public static void handlerResult(List<String> ans) {
        while (ans != null && ans.size() > 0 && "\n".equals(ans.get(ans.size() - 1))) {
            ans.remove(ans.size() - 1);
        }
    }


    public static String getMethodName(String codeContent) {
        int st = kmpSearch(codeContent, "(");
        if (st == -1) {
            return "defaultMethodName";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = st - 1; i >= 0; i--) {
            char c = codeContent.charAt(i);
            if (c == ' ') {
                //return sb.toString();
                break;
            }
            sb.append(c);
        }
        sb.reverse();
        return sb.toString();
    }


    /**
     * 这部分主要是替换代码 生成代码模板
     * 实体转义符号参考 <a href="https://www.w3school.com.cn/charsets/ref_html_8859.asp">...</a>
     *
     * @param classStr 类 template
     * @return
     */
    public static String getMethod(String classStr) {
        if (isEmpty(classStr)) return classStr;
        classStr = classStr.replace("class", "");
        classStr = classStr.replace("Solution {\\n", "");
        classStr = classStr.replace("\\n", "");
        classStr = classStr.replace("&lt;", "<");
        classStr = classStr.replace("&gt;", ">");
        classStr = classStr.replace("&amp;", "&");
        classStr = classStr.replace("&quot;", "\"");
        classStr = classStr.replace("&nbsp;", " ");
        return classStr;
    }


    public static boolean isNeedMOD(String desc) {
        if (isEmpty(desc)) {
            return false;
        }
        int a = kmpSearch(desc, "10<sup>9");
        if (a != -1) {
            return true;
        }
        a = kmpSearch(desc, mod_unicode);
        if (a != -1) {
            return true;
        }
        a = kmpSearch(desc, mod_ans_unicode);
        return a != -1;
    }


    /**
     * maximum_length_substring_with_two_occurrences
     * //=> Maximum
     * 短横线更换
     *
     * @return
     */
    public static String toCaseName(String url) {
        String titleSlug = BuildUrl.buildTitleSlug(url);
        return titleSlug.replace("-", "_");

    }


}
