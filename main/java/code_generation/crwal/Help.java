package code_generation.crwal;

/**
 * @author: wuxin0011
 * @Description:
 */

import code_generation.utils.IoUtil;

import java.util.ArrayList;
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
        String read = IoUtil.readContent("D:\\desktop\\project\\Learn\\leetcode\\temp\\390-b-1.html");
    }






    /**
     * 获取代码块内容
     * @param input
     */
    public static void parseReactCodeMirrorCode(String input) {
        String con = "<textarea name=\"lc-codemirror\"";
        // System.out.println(input);
        String endTagContent = "</textarea>";
        String tagContent = TestCaseUtil.getTagContent(input, con, 0, "<textarea", endTagContent);
        if (tagContent.length() != 0) {
            int i1 = TestCaseUtil.kmpSearch(tagContent, "class Solution");
            String yourCodeContent = tagContent.substring(i1, tagContent.length() - endTagContent.length());
            System.out.println(genCodeTemplate(yourCodeContent));
        }
        System.out.println();
    }


    public static boolean isIgnore(char c) {
        return c == '\n' || c == '\t' || c == '\f' || c == ' ';
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
        int st = TestCaseUtil.kmpSearch(codeContent, "(");
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
