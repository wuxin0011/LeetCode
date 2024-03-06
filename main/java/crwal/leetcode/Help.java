package crwal.leetcode;

/**
 * @author: wuxin0011
 * @Description:
 */

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Help {

    private static final String LEET_CODE_NAME = "LEET_CODE_NAME";
    private static final String LEET_CODE_PASS = "LEET_CODE_PASS";


    private static final String CONTEST_URL = "https://leetcode.cn/contest/";
    private static final String CONTEST_TIMES_TEMPLATE = "https://leetcode.cn/contest/weekly-contest-{0}/";


    private static final String ua = "";

    public static void main(String[] args) throws Exception {
//        System.out.println(System.getenv(LEET_CODE_NAME));
//        System.out.println(System.getenv(LEET_CODE_PASS));
        // test();
//        List<List<String>> cs = parseContest(read("contest.html"));
//        // System.out.println(cs);
//        for (List<String> c : cs) {
//            System.out.println(c);
//        }

        // parseTestCase(read("D:\desktop\Learn\awesome\java\test-python-java\a.html"));
        // parseWeeklyTestCase(read("D:\desktop\Learn\awesome\java\test-python-java\as.html"));
        // parseTestCase(read("D:\desktop\Learn\awesome\java\test-python-java\b.html"));
        // System.out.println(parseContest(read("D:\desktop\Learn\awesome\java\test-python-java\contest.html")));
        getContainers(read("D:\\desktop\\Learn\\awesome\\java\\test-python-java\\cs.html"));
        // parse1();
    }

    public static String read(String url) throws Exception {
        File file = new File(url);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        StringBuilder sb = new StringBuilder();
        byte[] buf = new byte[1024 * 1024];
        while (bis.read(buf) != -1) {
            sb.append(new String(buf));
        }
        bis.close();
        return sb.toString();
    }


    /**
     * 获取周赛题目链接
     * @param input
     * @return
     * @throws Exception
     */
    public static List<List<String>> parseContest(String input) throws Exception {
        if (input == null || "".equals(input)) {
            throw new RuntimeException("input is null ");
        }
        List<List<String>> temp = new ArrayList<>();
        String ul_list = "list-group hover-panel contest-question-list";
        int i = kmpSearch(input, ul_list);
        if (i == -1) {
            throw new RuntimeException("Not found");
        }
        final String ul_start = "<ul";
        final String ul_end = "</ul>";
        int st = kmpSearch(input, i - ul_list.length(), ul_start);
        if (st == -1) {
            throw new RuntimeException("Not find" + ul_start);
        }
        int ed = kmpSearch(input, i, ul_end);
        if (ed == -1 || st == ed) {
            throw new RuntimeException("Not find" + ul_end);
        }

        input = input.substring(st, ed + ul_end.length());
        // System.out.println(input);
        Pattern compile = Pattern.compile("<a\\s+href=\"(.*?)\">(.*?)<\\/a>");
        Matcher matcher = compile.matcher(input);
        while (matcher.find()) {
            List<String> t = new ArrayList<>();
            String link = matcher.group(1); // href
            String title = matcher.group(2); // title
            t.add(link);
            t.add(title);
            temp.add(t);
        }
        return temp;
    }


    /**
     * 解析周赛
     * @param input
     */
    public static void parseWeeklyTestCase(String input) {
        final String template = "<div class=\"question-content default-content\">";
        parseTestCase(input, template);
    }

    /**
     * 解析普通题目
     * @param input
     */
    public static void parseTestCase(String input) {
        final String template = "<div class=\"elfjS\" data-track-load=\"description_content\">";
        parseTestCase(input, template);
    }


    /**
     * 解析测试案例
     *
     * @param input
     */
    public static void parseTestCase(String input, String classSelector) {
        int i = input.indexOf(classSelector);
        if (i == -1) {
            throw new RuntimeException("Not found class selector " + classSelector + ",place check exists !!!");
        }
        Deque<String> stack = new ArrayDeque<>();
        final String div_start = "<div";
        final String div_end = "</div>";
        final int m = div_end.length();
        final int n = div_start.length();
        final int z = input.length();
        int ed = i;
        for (; ed < z - m; ed++) {
            String s1 = input.substring(ed, ed + n);
            if (div_start.equals(s1)) {
                stack.push(s1);
                continue;
            }
            String s2 = input.substring(ed, ed + m);
            if (div_end.equals(s2) && !stack.isEmpty()) {
                stack.pop();
                if (stack.isEmpty()) {
                    ed += m + 1;
                    break;
                }
            }

        }
        input = input.substring(i, ed);
        String example = "class=\"example\"";
        int i2 = kmpSearch(input, example);
        if (i2 != -1) {
            // get example str !!!
            input = input.substring(i2);
        }
        stack.clear();


        final String pre_start = "<pre";
        final String pre_end = "</pre>";
        // parse1(input);
        List<Integer> preList = kmpSearchList(input, pre_start);
        List<Integer> preEndList = kmpSearchList(input, pre_end);
        if (preList.size() != preEndList.size()) {
            throw new RuntimeException("error");
        }
        for (int idx = 0; idx < preList.size(); idx++) {
            int st = preList.get(idx);
            int end = preEndList.get(idx);
            if (st > end) {
                throw new RuntimeException("error");
            }
            String target = input.substring(st, end + pre_end.length());
            parse1(target);
        }
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


    public static List<Integer> getContainers(String input) {
        final String con = "class=\"container\"";
//        List<Integer> ans = kmpSearchList(input, con);
//        System.out.println(ans);
        /// System.out.println(input.indexOf(con));
        parseReactCodeMirrorCode(input);


        return null;
    }

    /**
     * 获取代码块内容
     * @param input
     */
    public static void parseReactCodeMirrorCode(String input) {
        String con = "<textarea name=\"lc-codemirror\"";
        // System.out.println(input);
        String endTagContent = "</textarea>";
        String tagContent = getTagContent(input, con, 0, "<textarea", endTagContent);
        if (tagContent.length() != 0) {
            int i1 = kmpSearch(tagContent, "class Solution");
            String yourCodeContent = tagContent.substring(i1, tagContent.length() - endTagContent.length());
            System.out.println(genCodeTemplate(yourCodeContent));
        }
        System.out.println();
    }

    public static List<String> parseInputOutPut(String input) {
        List<String> ans = new ArrayList<>();
        if (input == null) {
            return ans;
        }

        return ans;
    }

    public static void parse1(String input) {
        List<String> ans = new ArrayList<>();
        int i1 = kmpSearch(input, "=");
        List<Integer> strongList = kmpSearchList(input, "<strong>");
        int end = strongList.size() >= 2 ? strongList.get(2) : -1;
        if (end == -1) {
            throw new RuntimeException("error");
        }
        // System.out.println(strongList + ":end = " + end + ",end = " + (input.length() - 1));
        StringBuilder sb = null;
        int deep = 0, args = 0;
        for (int i = i1; i < end; i++) {
            char c = input.charAt(i);
            // System.out.println(c);
            if (isIgnore(c)) {
                // this is expect
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
//        System.out.println("ans = > " + ans);
        for (int i = 0; i < ans.size(); i++) {
            if (i == ans.size() - 1) {
                System.out.println("result  = " + ans.get(i));
            } else {
                System.out.println("args" + i + " = " + ans.get(i));
            }
        }
        // System.out.println("args = " + args + " deep " + deep);
    }

    public static boolean isIgnore(char c) {
        return c == '\n' || c == '\t' || c == '\f' || c == ' ';
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
            throw new RuntimeException("input content Not find " + classSelectorFlag);
        }
        int slen = startTag.length();
        int elen = endTag.length();
        int deep = 0;
        boolean findTag = false;
        for (int i = stIdx; i < input.length(); i++) {
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
                return input.substring(stIdx, i + elen);
            }
        }
        return "";
    }


    public static String genCodeTemplate(String codeContent) {
        return genCodeTemplate(codeContent, null);
    }

    /**
     * 这部分主要是替换代码 生成代码模板
     * 实体转义符号参考 https://www.w3school.com.cn/charsets/ref_html_8859.asp
     *
     * @param codeContent
     * @return
     */
    public static String genCodeTemplate(String codeContent, String className) {
        // int i1 = kmpSearch(codeContent, "public");
        System.out.println("methodName  = " + getMethodName(codeContent));
        codeContent = "public " + codeContent.replace("Solution", className == null ? "A" : className)
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&amp;", "&")
                .replace("&quot;", "\"")
                .replace("&nbsp;", " ");


        return codeContent;
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


}
