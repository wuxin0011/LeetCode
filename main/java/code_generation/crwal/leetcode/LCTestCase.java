package code_generation.crwal.leetcode;

import code_generation.contest.TestCase;
import code_generation.crwal.TestCaseUtil;
import code_generation.utils.CustomColor;
import code_generation.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: wuxin0011
 * @Description:
 */
public class LCTestCase implements TestCase {

    public static final String input_flag = "Input:";
    public static final String output_flag = "Output:";
    public static final String strong_start = "<strong>";
    public static final String strong_end = "</strong>";

    public static final String pre_start = "<pre";
    public static final String pre_end = "</pre>";


    public static final String EqualFlag = "=";
    public static final String OtherFlag = ":";
    public static final String interFlag = ",";


    @Override
    public List<String> parseContest(String input) {
        input = TestCaseUtil.getTagContent(input, "class=\"question-content source-content\"", 0, "<div", "</div");
        input = input.replace("&quot;", "");
        List<String> ans = new ArrayList<>();
        handlerOldOutPut(input, ans);
        // 有时候获取不到测试案例 或者测试案例获取不全面
        // 这里尝试将周赛测试案例方式移动到后面检查
        if (ans.size() == 0) {
            // matchExampleAll(input, ".*<span\\s+class=\"example-io\".*>(.*?)</span>", ans);
            matchExampleAll(input, null, ans);
        }


//        之前处理方案
//        String pattern = "<span\\s+class=\"example-io\">(.*?)</span>";
//        // String pattern = "<span\\\\s*class=(?:\\\"|')?example-io(?:\\\"|')?>(.*?)</span>";
//        //System.out.println(input);
//        Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
//        Matcher m = r.matcher(input);
//        while (m.find()) {
//            String result = m.group(1);
//            TestCaseUtil.startParseContestTestCase(result, EqualFlag, interFlag, ans);
//        }
//        if (ans.size() == 0) {
//            handlerOldOutPut(input, ans);
//        }
        StringUtils.handlerResult(ans);
        return ans;
    }


    public static int findOutput(String next, String... flags) {
        int idx = -1;
        for (String flag : flags) {
            idx = StringUtils.kmpSearch(next, flag);
            if (idx != -1) {
                break;
            }
        }
        return idx;
    }


    public static void handlerOldOutPut(String input, List<String> ans) {
        List<Integer> input_pos = StringUtils.kmpSearchList(input, input_flag);
        List<Integer> output_pos = StringUtils.kmpSearchList(input, output_flag);
        if (input_pos.size() == 0 || output_pos.size() == 0) {
            input_pos = StringUtils.kmpSearchList(input, StringUtils.inputUnicodeOld);
            output_pos = StringUtils.kmpSearchList(input, StringUtils.outputUnicodeOld);
        }
        if (input_pos.size() != output_pos.size()) {
            System.out.printf("parse test case is error, %s\n", CustomColor.error("place Try copying manually"));
            return;
        }
        // System.out.println("len = " + input.length());
        for (int i = 0; i < input_pos.size(); i++) {
            parseInputTestCase(input.substring(input_pos.get(i), output_pos.get(i)), ans);
            String next = input.substring(output_pos.get(i));
            // Explanation 为测试案例解释标识符  Explanation 或者 inputUnicodeOld 字符
            // 如果上面两个都没找到 则说明该测试没有解释 尝试直接 找到下一行 输出标识符 Input 或者 inputUnicodeOld
            int j = findOutput(next, StringUtils.Explanation, StringUtils.explainUnicodeOld, StringUtils.Input, StringUtils.inputUnicodeOld);
            if (j != -1) {
                parseOutputTestCase(next.substring(0, j), ans);
            } else {
                // 第几个测试案例错误
                System.out.printf("parse test case find error in %s times, %s\n", CustomColor.error(i + 1), CustomColor.error("place Try copying manually"));
            }
        }
    }

    @Override
    public List<String> parseDefault(String input) {
        input = input.replace("&quot;", "");
        input = input.replace("<em>", "").replace("</em>", "");
        List<String> ans = new ArrayList<>();
        List<Integer> preList = StringUtils.kmpSearchList(input, pre_start);
        List<Integer> preEndList = StringUtils.kmpSearchList(input, pre_end);
        if (preList.size() != preEndList.size()) {
            System.err.println("parse default testcase is error because pre code size not equal , use default testcase ");
            return parseContest(input);
        }

        for (int idx = 0; idx < preList.size(); idx++) {
            int st = preList.get(idx);
            int end = preEndList.get(idx);
            String target = input.substring(st, end + pre_end.length());
            TestCaseUtil.parseDefaultTextCase(target, ans);
        }
        if (ans.size() == 0) {
            handlerOldOutPut(input, ans);
        }
        if (ans.size() == 0) { // 尝试其他方式3
            return parseContest(input);
        }
        StringUtils.handlerResult(ans);
        return ans;
    }


    public static void parseInputTestCase(String s, List<String> ans) {
        // s = s.replace(input_flag, "").replace(output_flag, "").replace(strong_start, "").replace(strong_end, "");
        s = StringUtils.replaceIgnoreContent(s);
        // s = matchExample(s);
        s = s.replace("\\n", "");
        // System.out.println("input s => " + s);
        StringBuilder sb = null;
        int deep = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (StringUtils.isIgnore(c)) {
                continue;
            }
            if (c == '=') {
                sb = new StringBuilder();
            } else if (c == ',') {
                if (sb != null) {
                    if (deep == 0) {
                        ans.add(sb.toString());
                    } else {
                        sb.append(c);
                    }
                }
            } else if (c == '[' || c == '{') {
                if (sb != null) {
                    sb.append(c);
                }
                deep++;
            } else if (c == ']' || c == '}') {
                deep--;
                if (sb != null) {
                    sb.append(c);
                    if (deep == 0) {
                        ans.add(sb.toString());
                        sb = null;
                    }
                }
            } else {
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

    public static void parseOutputTestCase(String s, List<String> ans) {
        // s = matchExample(s);
        s = StringUtils.replaceIgnoreContent(s);
        if (!StringUtils.isEmpty(s)) {
            s = StringUtils.ingoreString(s);
        }
        ans.add(s);
        ans.add("\n");
    }

    public static String matchExample(String input) {
        return StringUtils.replaceIgnoreContent(input);
    }

    public static void matchExampleAll(String input, String pattern, List<String> ans) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = "<span.*?>(.*?)</span>";
        }
        Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
        Matcher m = r.matcher(input);
        while (m.find()) {
            String result = m.group(1);
            TestCaseUtil.startParseContestTestCase(result, EqualFlag, interFlag, ans);
        }
    }


}
