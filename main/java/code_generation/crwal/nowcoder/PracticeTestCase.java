package code_generation.crwal.nowcoder;

import code_generation.contest.TestCase;
import code_generation.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
public class PracticeTestCase implements TestCase {

    static final String INPUT = "input";
    static final String OUTPUT = "output";

    @Override
    public List<String> parseContest(String input) {
        return parseDefault(input);
    }

    @Override
    public List<String> parseDefault(String input) {
        int i = StringUtils.kmpSearch(input, "samples");
        if (i == -1) {
            throw new RuntimeException("NOT FOUND");
        }
        return parse(input.substring(i));
    }


    static List<String> parse(String input) {
        int deep = 0;
        StringBuilder sb = null;
        List<String> inputs = new ArrayList<>();
        int o = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (StringUtils.isIgnore(c)) {
                continue;
            }
            if (c == '[') {
                o++;
                continue;
            }
            if (c == ']') {
                o--;
                if (o == 0) break;
                continue;
            }
            if (c == '{') {
                deep++;
                if (deep == 1) {
                    sb = new StringBuilder();
                }
                if (sb != null) {
                    sb.append(c);
                }

            } else if (c == '}') {
                deep--;
                if (sb != null) {
                    sb.append(c);
                }
                if (deep == 0 && sb != null) {
                    inputs.add(sb.toString());
                    sb = null;
                }
            } else if (c == ',') {
                if (deep > 0 && sb != null) {
                    sb.append(c);
                }
            } else {
                if (sb != null) {
                    sb.append(c);
                }
            }
        }
        List<String> result = new ArrayList<>();
        for (String an : inputs) {
            getCase(an, result);
        }
        StringUtils.handlerResult(result);
        return result;
    }

    public static void getCase(String s, List<String> ans) {
        handlerInputOutput(s, StringUtils.kmpSearch(s, INPUT), INPUT.length(), ans);
        handlerInputOutput(s, StringUtils.kmpSearch(s, OUTPUT), OUTPUT.length(), ans);
        ans.add("\n");
    }


    public static void handlerInputOutput(String s, int st, int len, List<String> ans) {
        if (st == -1 || st + len >= s.length()) {
            throw new IndexOutOfBoundsException();
        }
        st += len;
        int deep = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = st; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ':') {
                continue;
            }
            if (c == '{' || c == '[') {
                deep++;
            } else if (c == '}' || c == ']') {
                deep--;
                if (deep == 0) {
                    sb.append(c);
                    break;
                }
            } else if (c == ',') {
                if (deep == 0) {
                    break;
                }
            }
            sb.append(c);
        }
        ans.add(sb.toString());
    }
}
