package code_generation.crwal;

import code_generation.crwal.leetcode.LCTestCase;
import code_generation.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
public class TestCaseUtil {


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


    /**
     * 解析周赛案例
     *
     * @param input     输入的内容
     * @param startFlag 开始
     * @param endFlag   结束
     * @param ans       结果
     */
    public static void startParseContestTestCase(String input, String startFlag, String endFlag, List<String> ans) {
        // System.out.println("input======>\n"+input);
        input = input.replace("&quot;", "");
        char[] charArray = input.toCharArray();
        int deep = 0;
        StringBuilder sb = null;
        if (StringUtils.kmpSearch(input, "=") == -1 && StringUtils.kmpSearch(input, ":") == -1) {
            ans.add(input);
        } else {
            for (char c : charArray) {
                if (StringUtils.isIgnore(c)) {
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
                        if (deep == 0 && sb == null) {
                            sb = new StringBuilder();
                        }
                        if (sb != null) {
                            sb.append(c);
                        }
                        deep++;
                        break;
                    case ']':
                    case '}':
                    case '\u3011':
                        deep--;
                        if (sb != null) {
                            sb.append(c);
                            if (deep == 0) {
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
            }
            ans.add("\n");
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
        int stIdx = StringUtils.kmpSearch(input, start, classSelectorFlag);
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


    public static void parseDefaultTextCase(String input, List<String> ans) {
        input = input.replace("\\n", "");
        input = input.replace("&quot;", "");
        if (checkHasUnicodeInputOutput(input)) {
            unicodeParseInputOutPut(input, ans);
        } else {
            int i1 = StringUtils.kmpSearch(input, "=");
            int i2 = StringUtils.kmpSearch(input, ":");
            if (i1 == -1 && i2 == -1) {
                ans.add(input);
                return;
            }
            List<String> temp = new ArrayList<>();
            int end = Math.min(Math.max(input.length() - LCTestCase.pre_end.length(), 1), input.length());
            StringBuilder sb = null;
            int deep = 0, args = 0;
            for (int i = i1; i < end; i++) {
                char c = input.charAt(i);
                if (StringUtils.isIgnore(c)) {
                    if (sb != null && i == end - 1) {
                        temp.add(sb.toString());
                        sb = null;
                    }
                    continue;
                }

                switch (c) {
                    case '<':
                        if (args > 0 && sb != null) {
                            temp.add(sb.toString());
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
                            temp.add(sb.toString());
                            if (args > 0) args--;
                            sb = null;
                        }
                        break;
                    case ',':
                        if (sb != null) {
                            if (deep == 0) {
                                temp.add(sb.toString());
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
            temp.add("\n");
            ans.addAll(temp);
        }

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

    public static int handlerUnicodeInputAndOutPut(String input, String newStr, String oldStr, boolean isAdd) {
        int idx = StringUtils.kmpSearch(input, newStr);
        if (idx != -1) {
            return isAdd ? idx + newStr.length() : idx;
        } else {
            idx = StringUtils.kmpSearch(input, oldStr);
            if (idx == -1) {
                return -1;
            }
            return isAdd ? idx + oldStr.length() : idx;
        }
    }


    public static void unicodeParseInputOutPut(String input, List<String> ans) {
        int a = handlerUnicodeInputAndOutPut(input, inputUnicode, inputUnicodeOld, true);
        int b = handlerUnicodeInputAndOutPut(input, outputUnicode, outputUnicodeOld, false);
        if (a == -1 || b == -1) {
            System.err.println("Not find test case !");
            return;
        }
        int c = StringUtils.kmpSearch(input, explainUnicode);
        if (c == -1) {
            c = input.length();
        }
        String inputStr = handlerIgnoreStr(input.substring(a, b));
        // if not have explain
        // c == -1 ? input.length() : c
        String outputStr = handlerIgnoreStr(input.substring(b, c));
        startParseContestTestCase(inputStr, LCTestCase.EqualFlag, LCTestCase.interFlag, ans);
        StringUtils.handlerResult(ans);
        startParseContestTestCase(outputStr, LCTestCase.EqualFlag, LCTestCase.interFlag, ans);
        ans.add("\n");
    }


    // 是否是unicode格式
    public static boolean checkHasUnicodeInputOutput(String input) {
        int a = StringUtils.kmpSearch(input, inputUnicodeOld);
        int b = StringUtils.kmpSearch(input, outputUnicodeOld);
        return a != -1 && b != -1 && a < b;
    }


    public static String handlerIgnoreStr(String s) {
        if (StringUtils.isEmpty(s)) return s;
        s = s.replace(inputUnicode, "").replace(outputUnicode, "").replace(explainUnicode, "");
        s = s.replace(inputUnicodeOld, "").replace(outputUnicodeOld, "");
        s = s.replace("<b>", "").replace("</b>", "");
        s = s.replace("<p>", "").replace("</p>", "");
        s = s.replace("<span>", "").replace("</span>", "");
        s = s.replace("<strong>", "").replace("</strong>", "");
        s = s.replace("<div>", "").replace("</div>", "");
        s = s.replace("<br>", "").replace("</br>", "");
        s = s.replace("<pre>", "").replace("</pre>", "");
        s = s.replace("<pre", "").replace("/pre>", "");
        return s;
    }


}
