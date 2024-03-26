package code_generation.crwal.leetcode;

import code_generation.contest.TestCase;
import code_generation.crwal.TestCaseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: wuxin0011
 * @Description:
 */
public class LCTestCase implements TestCase {

    static final String input_flag = "Input:";
    static final String output_flag = "Output:";
    static final String strong_start = "<strong>";
    static final String strong_end = "</strong>";

    static final String pre_start = "<pre";
    static final String pre_end = "</pre>";


    public static final String EqualFlag = "=";
    public static final String OtherFlag = ":";
    public static final String interFlag = ",";


    @Override
    public List<String> parseContest(String input) {
        input = TestCaseUtil.getTagContent(input, "class=\"question-content source-content\"", 0, "<div", "</div");
        input = input.replace("&quot;", "");
        String pattern = "<span\\s+class=\"example-io\">(.*?)</span>";
        //System.out.println(input);
        Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
        Matcher m = r.matcher(input);
        List<String> ans = new ArrayList<>();
        while (m.find()) {
            String result = m.group(1);
            if (result.contains(EqualFlag) || result.contains(OtherFlag)) {
                TestCaseUtil.startParseContestTestCase(result, EqualFlag, interFlag, ans);
            } else {
                ans.add(result);
            }
        }
        if (ans.size() != 0) {
            return ans;
        }
        handlerOldOutPut(input, ans);
        return ans;
    }


    public static void handlerOldOutPut(String input, List<String> ans) {

        List<Integer> input_pos = TestCaseUtil.kmpSearchList(input, input_flag);
        List<Integer> output_pos = TestCaseUtil.kmpSearchList(input, output_flag);
        int size = output_pos.size();
        if (size == 0) {
            System.out.println("not find any output testcase");
            return;
        }
        StringBuilder sb = null;
        // System.out.println("len = " + input.length());
        for (int i = 0; i < input_pos.size(); i++) {
            parseInputTestCase(input.substring(input_pos.get(i), output_pos.get(i)), ans);
            int j = TestCaseUtil.kmpSearch(input.substring(output_pos.get(i)), strong_start);
            if (j != -1) {
                parseOutputTestCase(input.substring(output_pos.get(i), j + output_pos.get(i) + strong_start.length()), ans);
            }
        }
    }

    @Override
    public List<String> parseDefault(String input) {
        final String classSelector = "class=\"elfjS\" data-track-load=\"description_content\">";
        input = TestCaseUtil.getTagContent(input, classSelector, 0, "<div", "</div");
        String example = "class=\"example\"";
        int i2 = TestCaseUtil.kmpSearch(input, example);
        if (i2 != -1) {
            input = input.substring(i2);
        }

        // parse1(input);
        List<Integer> preList = TestCaseUtil.kmpSearchList(input, pre_start);
        List<Integer> preEndList = TestCaseUtil.kmpSearchList(input, pre_end);
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
            return TestCaseUtil.parseDefaultTextCase(target);
        }
        throw new RuntimeException("NO supprot");
    }


    public static void parseInputTestCase(String s, List<String> ans) {
        s = s.replace(input_flag, "").replace(output_flag, "").replace(strong_start, "").replace(strong_end, "");
        // System.out.println("input s => " + s);
        StringBuilder sb = null;
        int deep = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isIgnore(c)) {
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
        s = s.replace(input_flag, "").replace(output_flag, "");
        StringBuilder sb = null;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isIgnore(c)) {
                continue;
            }
            if (c == '>') {
                sb = new StringBuilder();
            } else if (c == '<') {
                if (sb != null) {
                    sb.append("\n");
                    ans.add(sb.toString());
                    break;
                }
            } else {
                if (sb != null) {
                    sb.append(c);
                }
            }
        }
    }


    public static boolean isIgnore(char c) {
        return c == '\r' || c == '\n' || c == '\t' || c == '\b' || c == '\f' || c == '\0' || c == '\\' || c == ' ' || c == '\'' || c == '\"';
    }


}
