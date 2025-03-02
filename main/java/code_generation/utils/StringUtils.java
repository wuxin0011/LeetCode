package code_generation.utils;

import code_generation.crwal.leetcode.BuildUrl;
import code_generation.crwal.leetcode.LCTemplate;

import java.io.File;
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

    static final String[] removeTagName = {"strong",
            "abbr", "address", "br", "cite", "div", "ul", "span", "pre", "em", "b", "u", "li", "code", "data", "dd", "del", "p", "img", "src", "video",
            "details", "dl", "dd", "html", "body", "footer", "h1", "h2", "h3", "h4", "h5", "p", "i", "link", "main", "map", "mark", "meta", "nav", "desc"
    };

    // 后续可以添加
    //


    public static final String mod_unicode = "\\u53d6\\u4f59";
    public static final String mod_ans_unicode = "\\u7b54\\u6848\\u53ef\\u80fd\\u5f88\\u5927";


    // 输入 unicode
    public static final String inputUnicode = "\\u8f93\\u5165\\uff1a";
    // 不包含:
    public static final String inputUnicodeOld = "\\u8f93\\u5165";

    // 输出 unicode

    public static final String outputUnicode = "\\u8f93\\u51fa\\uff1a";

    // 不包含:
    public static final String outputUnicodeOld = "\\u8f93\\u51fa";


    // 解释 unicode
    public static final String explainUnicode = "\\u89e3\\u91ca\\uff1a";
    // 不包含:
    public static final String explainUnicodeOld = "\\u89e3\\u91ca";


    public static final String DOT = ":";
    public static final String Input = "Input";
    public static final String Output = "Output";
    public static final String Explanation = "Explanation";
    public static final String OutputDot = Output + DOT;
    public static final String InputDot = Input + DOT;
    public static final String ExplanationDot = Explanation + DOT;

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

    // https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree/submissions/
    // 由于 # 这个符号与之前设计符号冲突了，加入特殊情况判断
    public static boolean isIgnore(char c, char st) {
        if (st == '#' && isIgnoreStrict(c)) {
            return true;
        }
        return st != '#' && isIgnore(c);
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
            return "\"\"";
        }
        // 迭代删除所以多余的换行
        while (s.contains("\n\n\n")) {
            s = s.replace("\n\n\n", "\n\n");
        }
        s = s.replace("`", "");
        s = s.replace(inputUnicodeOld, "");
        s = s.replace(outputUnicodeOld, "");
        s = s.replace(explainUnicodeOld, "");
        s = s.replace("uff1a", "");
        s = s.replace(InputDot, "").replace(Input, "");
        s = s.replace(OutputDot, "").replace(Output, "");
        for (String tag : removeTagName) {
            s = removeTag(s, tag);
        }
        s = s.replace("&lt;", "");
        s = s.replace("\\\\n;", "").replace("\\\\n", "");
        s = s.replace("\\n;", "").replace("\\n", "");
        s = s.replace("\\", "");
        s = s.replace("&gt;", "");
        s = s.replace("&amp;", "");
        s = s.replace("&quot;", "");
        s = s.replace("&nbsp;", "");
        s = s.replace(":", "");
        s = s.replace(" ", "");
        s = s.replace("<", "").replace(">", "");
        if (isEmpty(s)) {
            return "\"\"";
        }
        return s;
    }


    public static List<Integer> kmpSearchList(String text, String pattern) {
        int id = -1;
        List<Integer> ans = new ArrayList<>();
        while (true) {
            id = kmpSearch(text, id == -1 ? 0 : id + pattern.length(), pattern);
            if (id == -1) {
                break;
            }
            ans.add(id);
        }
        return ans;
    }


    public static String ingoreString(String input) {
        if (input == null || input.length() == 0) {
            //throw new NullPointerException("input content is null");
            return input;
        }
        input = input.replace("&quot;", "");
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
            return -1;
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
        return jsonStrGetValueByKey(jsonStr, key, true);
    }

    public static String jsonStrGetValueByKey(String jsonStr, String key, boolean isWrapper) {
        try {
            if (isWrapper) {
                key = wrapperKey(key);
            }
            int find = kmpSearch(jsonStr, key);
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
                if (c == ' ') {
                    sb.append(c);
                    continue;
                }
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
        } catch (Exception ignore) {
            return "";
        }
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




    // 处理方法中的特殊unicode
    public static String handerMethodString(String input){
        if (isEmpty(input)) return input;
        input = input.replace("\\u000A", "");
        input = input.replace("\\u003C", "<");
        input = input.replace("\\u003E", ">");
        input = input.replace("\\u0028", "(");
        input = input.replace("\\u0029", ")");
        input = input.replace("&lt;", "<");
        input = input.replace("&gt;", ">");
        input = input.replace("&amp;", "&");
        input = input.replace("&quot;", "");
        input = input.replace("&nbsp;", "");
        input = input.replace("\\n", "");
        input = input.replace("\n", "");
        int i = 0;
        // 移出前面的空格
        while (i < input.length()) {
            if (!StringUtils.isIgnore(input.charAt(i))) {
                break;
            }
            i++;
        }
        input = input.substring(i);
        return input;
    }


    public static String handlerAnnotationTemplate(String input){
        int classIdx = kmpSearch(input,"Solution");
        if(classIdx != -1){
            return input.substring(classIdx);
        }
//        if(kmpSearch(input,"TreeNode") == -1 || kmpSearch(input, "ListNode") == -1 ){
//            return input;
//        }
//        int has = kmpSearch(input, "/*");
//        if(has == -1){
//            return input;
//        }
//        int i = kmpSearch(input, has + 2, "*/");
//        if(i != -1){
//            return input.substring(i+2);
//        }
        return input;
    }


    public static void handlerResult(List<String> ans) {
        while (ans != null && ans.size() > 0 && "\n".equals(ans.get(ans.size() - 1))) {
            ans.remove(ans.size() - 1);
        }
    }


    public static String getMethodName(String codeContent) {
        int st = kmpSearch(codeContent, "(");
        if (st == -1) {
            return "IoUtil.DEFAULT_METHOD_NAME";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = st - 1; i >= 0; i--) {
            char c = codeContent.charAt(i);
            if (c == ' ' && !StringUtils.isEmpty(sb.toString())) {
                //return sb.toString();
                break;
            }
            if (c == ' ') {
                continue;
            }
            sb.append(c);
        }
        sb.reverse();
        return sb.toString();
    }

    public static String getMethod(String classStr) {
        return getMethod(classStr, LCTemplate.JAVA_CODE_PATTERN);
    }


    /**
     * 这部分主要是替换代码 生成代码模板
     * 实体转义符号参考 <a href="https://www.w3school.com.cn/charsets/ref_html_8859.asp">...</a>
     *
     * @param classStr 类 template
     * @return
     */
    public static String getMethod(String classStr,String javaCodeFlag) {
        if (isEmpty(javaCodeFlag) || isEmpty(classStr)) return classStr;

        // 查找含有Java code 的标志
        int i = StringUtils.kmpSearch(classStr, javaCodeFlag);
        if (i == -1) {
            return "";
        }
        classStr = classStr.substring(i+javaCodeFlag.length());
        // 如果有注释先处理注释
        classStr = StringUtils.handlerAnnotationTemplate(classStr);
        int deep = 0;
        StringBuilder sb = null;
        for (i = 0; i < classStr.length(); i++) {
            char c = classStr.charAt(i);
            if (c == '{') {
                deep++;
                if (deep==1 && sb == null){
                    sb = new StringBuilder();
                } else if( deep == 2 && sb != null){
                    sb.append(c);
                }
            } else if (c == '}') {
                deep--;
                if (deep == 0) {
                    break;
                }
                if (sb != null) {
                    sb.append(c);
                }

            } else {
                if (sb != null) sb.append(c);
            }
        }
        classStr = sb != null ? sb.toString() : null;
        classStr = StringUtils.handerMethodString(classStr);
        return "// " + classStr;
    }


    public static boolean isNeedMOD(String desc) {
        if (isEmpty(desc)) {
            return false;
        }
        int a = -1;
//        int a = kmpSearch(desc, "10<sup>9");
//        if (a != -1) {
//            return true;
//        }
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


    // 提取输入内容的所有链接
    public static List<String> matchUrls(String s) {
        List<String> urls = new ArrayList<>();
        try {

            Pattern pattern = Pattern.compile("https://(.*)/[^\\s\\)]+");
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                String url = matcher.group();
                urls.add(url);
                // System.out.println("URL: " + url);
            }
        } catch (Exception ignore) {

        }

        return urls;
    }


    // 将 unicode转换为中文
    public static String unicodeToChinese(String unicodeStr) {
        StringBuilder sb = new StringBuilder();

        try {
            String[] hex = unicodeStr.split("\\\\u");
            for (int i = 1; i < hex.length; i++) {
                int data = Integer.parseInt(hex[i], 16);
                sb.append((char) data);
            }
        } catch (Exception e) {
            return "";
        }

        return sb.toString();
    }


    public static String getDirCount(Class<?> aclass,String prefix)  {
        String s = IoUtil.buildAbsolutePath(aclass);
        File file = new File(s);
        int cnt = 0;
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1 != null && file1.isDirectory() && file1.getName().startsWith(prefix)) {
                    cnt++;
                }
            }
        }
        if (cnt < 10) {
            return "000" + cnt;
        } else if (cnt < 100) {
            return "00" + cnt;
        } else if (cnt < 1000) {
            return "0" + cnt;
        } else {
            return String.valueOf(cnt);
        }
    }


    // 输入输出的unicode替换位英文表示
    public static String inputOrOutputUnicodeConvertEnglish(String input) {
        if (StringUtils.isEmpty(input)) {
            return input;
        }
        input = input.replace(inputUnicodeOld, Input);
        input = input.replace(outputUnicodeOld, Output);
        input = input.replace(inputUnicode, InputDot);
        input = input.replace(outputUnicode, OutputDot);
        input = input.replace(explainUnicodeOld, Explanation);
        input = input.replace(explainUnicode, ExplanationDot);
        return input;
    }

    // 输入输出的的英文用unicode表示
    public static String inputOrOutputConvertUnicode(String input) {
        if (StringUtils.isEmpty(input)) {
            return input;
        }
        input = input.replace("input", Input);
        input = input.replace("output", Output);
        input = input.replace(Input, inputUnicodeOld);
        input = input.replace(Output, outputUnicodeOld);
        input = input.replace(InputDot, inputUnicode);
        input = input.replace(OutputDot, outputUnicode);
        input = input.replace(Explanation, explainUnicodeOld);
        input = input.replace(ExplanationDot, explainUnicode);
        return input;
    }

    public static String removeTag(String input, String tagName) {
        if (StringUtils.kmpSearch(input, "<" + tagName) == -1 && StringUtils.kmpSearch(input, tagName + ">") == -1) {
            return input;
        }
        Pattern pattern = Pattern.compile(String.format("</?%s[^>]*>", tagName));
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll("");
    }


}
