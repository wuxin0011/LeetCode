package code_generation.crwal.leetcode;

import code_generation.contest.TestCase;
import code_generation.crwal.TestCaseUtil;
import code_generation.utils.CustomColor;
import code_generation.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: wuxin0011
 * @Description:
 */
public class LCTestCase implements TestCase {

    public static final String input_flag = StringUtils.Input;
    public static final String output_flag = StringUtils.Output;
    public static final String explain_flag = StringUtils.Explanation;
    public static final String tips_flag = "Constraints";
    public static final String zh_input_flag = StringUtils.inputUnicodeOld;
    public static final String zh_output_flag = StringUtils.outputUnicodeOld;
    public static final String zh_explain_flag = StringUtils.explainUnicodeOld;
    public static final String zh_tips_flag = "\\u63d0\\u793a";
    public static final String strong_start = "<strong>";
    public static final String strong_end = "</strong>";
    public static final String pre_start = "<pre";
    public static final String pre_end = "</pre>";
    public static final String EqualFlag = "=";
    public static final String OtherFlag = ":";
    public static final String interFlag = ",";

    public LCTestCase() {
    }

    public List<String> parseContest(String input) {
        input = TestCaseUtil.getTagContent(input, "class=\"question-content source-content\"", 0, "<div", "</div");
        input = input.replace("&quot;", "");
        List<String> ans = new ArrayList<>();
        handlerOldOutPut(input, ans);
        if (ans.size() == 0) {
            matchExampleAll(input, (String) null, ans);
        }

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
        List<Integer> input_pos = StringUtils.kmpSearchList(input, "Input");
        List<Integer> output_pos = StringUtils.kmpSearchList(input, "Output");
        if (input_pos.isEmpty() || output_pos.isEmpty()) {
            input_pos = StringUtils.kmpSearchList(input, "\\u8f93\\u5165");
            output_pos = StringUtils.kmpSearchList(input, "\\u8f93\\u51fa");
        }

        if (input_pos.size() != output_pos.size()) {
            System.out.printf("parse test case is error, %s\n", CustomColor.error(new Object[]{"place Try copying manually"}));
        } else {
            for (int i = 0; i < input_pos.size(); ++i) {
                parseInputTestCase(input.substring((Integer) input_pos.get(i), (Integer) output_pos.get(i)), ans);
                String next = input.substring((Integer) output_pos.get(i));
                int j = findOutput(next, "Explanation", "\\u89e3\\u91ca", "Input", "\\u8f93\\u5165");
                if (j != -1) {
                    parseOutputTestCase(next.substring(0, j), ans);
                } else {
                    System.out.printf("parse test case find error in %s times, %s\n", CustomColor.error(new Object[]{i + 1}), CustomColor.error(new Object[]{"place Try copying manually"}));
                }
            }

        }
    }

    public List<String> parseDefault(String input) {
        input = input.replace("&quot;", "");
        input = input.replace("<em>", "").replace("</em>", "");
        List<String> ans = new ArrayList<>();
        List<Integer> preList = StringUtils.kmpSearchList(input, "<pre");
        List<Integer> preEndList = StringUtils.kmpSearchList(input, "</pre>");
        if (preList.size() != preEndList.size()) {
            System.err.println("parse default testcase is error because pre code size not equal , use default testcase ");
            return this.parseContest(input);
        } else {
            for (int idx = 0; idx < preList.size(); ++idx) {
                int st = (Integer) preList.get(idx);
                int end = (Integer) preEndList.get(idx);
                String target = input.substring(st, end + "</pre>".length());
                TestCaseUtil.parseDefaultTextCase(target, ans);
            }

            if (ans.isEmpty()) {
                handlerOldOutPut(input, ans);
            }

            if (ans.isEmpty()) {
                return this.parseContest(input);
            } else {
                StringUtils.handlerResult(ans);
                return ans;
            }
        }
    }

    public static void parseInputTestCase(String s, List<String> ans) {
        s = StringUtils.replaceIgnoreContent(s);
        s = s.replace("\\n", "");
        StringBuilder sb = null;
        int deep = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (!StringUtils.isIgnore(c)) {
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
                } else if (c != '[' && c != '{') {
                    if (c != ']' && c != '}') {
                        if (sb != null) {
                            sb.append(c);
                        }
                    } else {
                        --deep;
                        if (sb != null) {
                            sb.append(c);
                            if (deep == 0) {
                                ans.add(sb.toString());
                                sb = null;
                            }
                        }
                    }
                } else {
                    if (sb != null) {
                        sb.append(c);
                    }

                    ++deep;
                }
            }
        }

        if (sb != null) {
            ans.add(sb.toString());
            StringBuilder var8 = null;
        }

    }

    public static void parseOutputTestCase(String s, List<String> ans) {
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
            TestCaseUtil.startParseContestTestCase(result, "=", ",", ans);
        }

    }

    public static List<String> _2025NewHandlerInputAndOutput(String jsonStr) {
        List<String> inputs = _2025NewHandlerInput(jsonStr);
        List<String> outputs = _2025NewHandlerOutPut(jsonStr);
        List<String> result = new ArrayList<>();
        int m = inputs.size();
        int n = outputs.size();
        if (n == 0) {
            if (inputs.isEmpty()) {
                return inputs;
            } else {
                for (int i = 0; i < m; ++i) {
                    inputs.set(i, (String) inputs.get(i) + "\n");
                }

                return inputs;
            }
        } else {
            int group = m / n;
            if (m % n != 0) {
                System.out.println(CustomColor.error("案例解析失败，请尝试手动copy！"));
            }

            int i = 0;
            int j = 0;

            for (int k = 0; i < m && j < n; ++i) {
                ++k;
                result.add(StringUtils.replaceIgnoreContent((String) inputs.get(i)));
                result.add("\n");
                if (k == group) {
                    result.add(StringUtils.replaceIgnoreContent((String) outputs.get(j)));
                    if (j != n - 1) {
                        result.add("\n\n");
                    }
                    ++j;
                    k = 0;
                }
            }

            return result;
        }
    }

    public static List<String> _2025NewHandlerInput(String jsonStr) {
        String examples = null;
        List<String> ans = new ArrayList<>();
        if (StringUtils.kmpSearch(jsonStr, "exampleTestcaseList") != -1) {
            examples = StringUtils.jsonStrGetValueByKey(jsonStr, "exampleTestcaseList");
        } else if (StringUtils.kmpSearch(jsonStr, "exampleTestcases") != -1) {
            examples = StringUtils.jsonStrGetValueByKey(jsonStr, "exampleTestcases");
        } else if (StringUtils.kmpSearch(jsonStr, "jsonExampleTestcases") != -1) {
            examples = StringUtils.jsonStrGetValueByKey(jsonStr, "jsonExampleTestcases");
        }

        if (StringUtils.isEmpty(examples)) {
            return ans;
        } else {
            List<Integer> leftIds = StringUtils.kmpSearchList(examples, "[");
            List<Integer> rightIdx = StringUtils.kmpSearchList(examples, "]");
            if (!leftIds.isEmpty() && !rightIdx.isEmpty() && leftIds.size() <= rightIdx.size()) {
                examples = examples.substring((Integer) leftIds.get(0) + 1, (Integer) rightIdx.get(leftIds.size() - 1));
                int d = 0;
                StringBuilder cur = new StringBuilder();

                for (int i = 0; i < examples.length(); ++i) {
                    char c = examples.charAt(i);
                    if (!StringUtils.isIgnoreStrict(c)) {
                        switch (c) {
                            case ',':
                                if (d == 0) {
                                    if (!StringUtils.isEmpty(cur.toString())) {
                                        ans.add(cur.toString());
                                        cur = new StringBuilder();
                                    }
                                } else {
                                    cur.append(c);
                                }
                                break;
                            case '[':
                                ++d;
                                if (d == 1 && !StringUtils.isEmpty(cur.toString())) {
                                    ans.add(cur.toString());
                                    cur = new StringBuilder();
                                }

                                cur.append(c);
                                break;
                            case ']':
                                cur.append(c);
                                --d;
                                if (d == 0 && !StringUtils.isEmpty(cur.toString())) {
                                    ans.add(cur.toString());
                                    cur = new StringBuilder();
                                }
                                break;
                            case 'n':
                                if (i > 0 && examples.charAt(i - 1) == '\\') {
                                    if (!StringUtils.isEmpty(cur.toString())) {
                                        ans.add(cur.toString());
                                    }

                                    cur = new StringBuilder();
                                } else {
                                    cur.append(c);
                                }
                                break;
                            default:
                                cur.append(c);
                        }
                    }
                }

                if (!StringUtils.isEmpty(cur.toString())) {
                    ans.add(cur.toString());
                }

                return ans;
            } else {
                return ans;
            }
        }
    }

    public static List<String> _2025NewHandlerOutPut(String jsonStr) {
        int index = StringUtils.kmpSearch(jsonStr, input_flag);
        if (index != -1) {
            return parseOutput(jsonStr.substring(index), input_flag, output_flag, explain_flag, tips_flag);
        } else {
            index = StringUtils.kmpSearch(jsonStr, zh_input_flag);
            return parseOutput(jsonStr.substring(index), zh_input_flag, zh_output_flag, zh_explain_flag, zh_tips_flag);
        }
    }

    public static List<String> parseOutput(String inputstring, String input_flag, String output_flag, String explain_flag, String tips_flag) {
        List<String> outputs = new ArrayList<>();
        List<Integer> startIds = StringUtils.kmpSearchList(inputstring, pre_start);
        List<Integer> endIds = StringUtils.kmpSearchList(inputstring, pre_end);
        if (!startIds.isEmpty() && startIds.size() == endIds.size()) {
            int n = startIds.size();

            for (int i = 0; i < n; ++i) {
                String cur = inputstring.substring(startIds.get(i), endIds.get(i));
                int outputIndex = StringUtils.kmpSearch(cur, output_flag);
                int explainIndex = StringUtils.kmpSearch(cur, explain_flag);
                if (outputIndex != -1) {
                    outputs.add(StringUtils.replaceIgnoreContent(cur.substring(outputIndex + output_flag.length(), explainIndex != -1 && explainIndex >= outputIndex ? explainIndex : cur.length())));
                }
            }

            if (outputs.size() == startIds.size()) {
                return outputs;
            }

            outputs = new ArrayList<>();
        }


        // 20250615
        // fix bug
        startIds = StringUtils.kmpSearchList(inputstring, output_flag);
        endIds = StringUtils.kmpSearchList(inputstring, explain_flag);
        int n = Math.min(startIds.size(), endIds.size());
        TreeSet<Integer> treeSet = new TreeSet<>(endIds);
        int[][] group = new int[n][2];
        for(int i = 0;i < n;i++){
            group[i][0] = startIds.get(i);
            Integer key = treeSet.ceiling(startIds.get(i));
            if(key != null) {
                group[i][1] = key;
                treeSet.remove(key);
            }else{
                throw new RuntimeException("parse Exception");
            }
        }
        startIds = new ArrayList<>();
        endIds = new ArrayList<>();
        for(int i = 0;i < n;i++) {
            startIds.add(group[i][0]);
            endIds.add(group[i][1]);
        }

        if (!startIds.isEmpty()) {
            int addcnt = 0;

            for (int i = 0; i < Math.min(startIds.size(), endIds.size()); ++i) {
                outputs.add(StringUtils.replaceIgnoreContent(inputstring.substring(startIds.get(i), endIds.get(i))));
                ++addcnt;
            }

            if (startIds.size() - endIds.size() == 1) {
                String cur = inputstring.substring((Integer) startIds.get(startIds.size() - 1) + output_flag.length());
                int k = StringUtils.kmpSearch(cur, tips_flag);
                if (startIds.size() != endIds.size() && k != -1) {
                    outputs.add(StringUtils.replaceIgnoreContent(cur.substring(0, k)));
                } else {
                    outputs.add(StringUtils.replaceIgnoreContent(cur));
                }
            } else {
                List<Integer> inputIdx = StringUtils.kmpSearchList(inputstring, input_flag);
                if (inputIdx.size() == startIds.size()) {
                    int i = endIds.size();

                    for (int j = Math.max(0, endIds.size() - 1); i < inputIdx.size(); ++j) {
                        outputs.add(StringUtils.replaceIgnoreContent(inputstring.substring(startIds.get(j), inputIdx.get(i))));
                        ++addcnt;
                        ++i;
                    }
                }

                if (addcnt != startIds.size()) {
                    String cur = inputstring.substring((Integer) startIds.get(startIds.size() - 1) + output_flag.length());
                    int k = StringUtils.kmpSearch(cur, tips_flag);
                    if (startIds.size() != endIds.size() && k != -1) {
                        outputs.add(StringUtils.replaceIgnoreContent(cur.substring(0, k)));
                    } else {
                        outputs.add(StringUtils.replaceIgnoreContent(cur));
                    }
                }
            }
        }

        return outputs;
    }
}
