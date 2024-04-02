package code_generation.crwal.leetcode;

import code_generation.contest.TestCase;
import code_generation.crwal.TestCaseUtil;
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
                ans.add("\n");
            }
        }
        if (ans.size() == 0) {
            handlerOldOutPut(input, ans);
        }
        StringUtils.handlerResult(ans);
        return ans;
    }


    public static void handlerOldOutPut(String input, List<String> ans) {

        List<Integer> input_pos = StringUtils.kmpSearchList(input, input_flag);
        List<Integer> output_pos = StringUtils.kmpSearchList(input, output_flag);
        int size = output_pos.size();
        if (size == 0) {
            System.out.println("not find any output testcase");
            return;
        }
        // System.out.println("len = " + input.length());
        for (int i = 0; i < input_pos.size(); i++) {
            parseInputTestCase(input.substring(input_pos.get(i), output_pos.get(i)), ans);
            int j = StringUtils.kmpSearch(input.substring(output_pos.get(i)), strong_start);
            if (j != -1) {
                parseOutputTestCase(input.substring(output_pos.get(i), j + output_pos.get(i) + strong_start.length()), ans);
            }
        }
    }

    @Override
    public List<String> parseDefault(String input) {
        input = input.replace("&quot;", "");
        List<Integer> preList = StringUtils.kmpSearchList(input, pre_start);
        List<Integer> preEndList = StringUtils.kmpSearchList(input, pre_end);
        if (preList.size() != preEndList.size()) {
            throw new RuntimeException("error");
        }
        List<String> ans = new ArrayList<>();
        for (int idx = 0; idx < preList.size(); idx++) {
            int st = preList.get(idx);
            int end = preEndList.get(idx);
            String target = input.substring(st, end + pre_end.length());
            TestCaseUtil.parseDefaultTextCase(target, ans);
        }
        StringUtils.handlerResult(ans);
        return ans;
    }


    public static void parseInputTestCase(String s, List<String> ans) {
        s = s.replace(input_flag, "").replace(output_flag, "").replace(strong_start, "").replace(strong_end, "");
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
        s = s.replace(input_flag, "").replace(output_flag, "");
        StringBuilder sb = null;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (StringUtils.isIgnore(c)) {
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


    public static void main(String[] args) {
        String unicodeString = "\\u7ed9\\u4f60\\u4e00\\u4e2a\\u4e8c\\u7ef4\\u6574\\u6570\\u6570";
    }

}
