package code_generation.crwal.leetcode;

import code_generation.utils.CustomColor;
import code_generation.utils.ExceptionUtils;
import code_generation.utils.IoUtil;
import code_generation.utils.StringUtils;
import leetcode.contest.weekly.w_400.w_473.A;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author: wuxin0011
 * @Description: 代码提交
 */
public class LCSubmit {


    /**
     * 是否预览代码
     */
    private static final boolean is_show_submit_code = false;

    /**
     * 是否自动提交
     */
    private static final boolean is_auto_submit = true;

    /**
     * 如果题目链接为周赛url是否提交到周赛？
     */
    public static final boolean is_contest_url_to_submit_contest = true;

    public static void submit(Class<?> c) {
        String javaFilePath = String.format("%s%s.java", IoUtil.buildAbsolutePath(c), c.getSimpleName());
        String code = filterSubmitCode(c, IoUtil.readContent(javaFilePath));
        List<String> urls = LCCustom.matchLeetCodeUrlsAndContest(code);
        if (urls.isEmpty()) {
            throw new RuntimeException("cannot not problem submit url!");
        }
        if (is_show_submit_code) {
            System.out.println("************************  preview your submit code  ************************ ");
            System.out.println(code);
            System.out.println("************************************************************** ");
        }
        if (!is_auto_submit) {
            System.out.println("Are you sure you want to submit? (y|yes) ");
            Scanner sc = new Scanner(System.in);
            String next = sc.next();
            if (!("y".equalsIgnoreCase(next) || "yes".equalsIgnoreCase(next))) {
                return;
            }
        }
        String problemUrl = urls.get(0);
        String titleSlug = BuildUrl.buildTitleSlug(problemUrl);
        String questionId = null;
        // 查询题目ID
        for (int __ = 0; __ < 10; __++) {
            questionId = getId(BuildUrl.queryQuestionInfo(titleSlug), "questionId");
            if (!StringUtils.isEmpty(questionId)) break;
            ExceptionUtils.sleep(Math.max(1, new Random().nextInt(5)));
        }
        String result = BuildUrl.submitCode(code, questionId, titleSlug,problemUrl);
        ExceptionUtils.sleep(5);
        String submission_id = getId(result, "submission_id");
        if (StringUtils.isEmpty(submission_id)) {
            throw new RuntimeException("Submit Fail !");
        }
        if(is_contest_url_to_submit_contest && (problemUrl.startsWith(BuildUrl.LC_WEEKLY_CONTEST_PREFIX) || problemUrl.startsWith(BuildUrl.LC_BI_WEEKLY_CONTEST_PREFIX))){
            System.out.printf("\nclick link views details: %s/submissions/%s\n", problemUrl, submission_id);
        }else{
            System.out.printf("\nclick link views details: %s/submissions/detail/%s\n", BuildUrl.LC_PREFIX, submission_id);
        }


        // 返回一个提交结果 包含 submission_id
        // 这里尝试休眠10次
        // 防止有些题目测试时间过长而拿不到数据
        for (int __ = 0; __ < 10; __++) {
            ExceptionUtils.sleep(Math.max(1, new Random().nextInt(5)));
            String status = BuildUrl.querySubmitResult(submission_id);
            if (StringUtils.kmpSearch(status, "submissionDetail") != -1) {
                if (StringUtils.kmpSearch(status, "Accepted") != -1) {
                    System.out.println("Accepted!");
                } else {
                    System.out.println(CustomColor.error("Fail !"));
                }
                break;
            }
        }
    }

    public static final String GITHUB = "https://github.com/wuxin0011/leetcode";

    private static String filterSubmitCode(Class<?> c, String code) {
        code = code.replace(String.format("public class %s", c.getSimpleName()), "public class Solution");
        code = addComment(code, "package");
        code = addComment(code, "import");
        code = addComment(code, "IoUtil.testUtil");
        code = addComment(code, "@TestCaseGroup");
        code = addComment(code, "System.out");
        if (StringUtils.kmpSearch(code, "@author: agitated-curranfnd") != -1 || StringUtils.kmpSearch(code, "@author: qitongwei") != -1) {
            code = String.format("// Template Generation by : %s\n\n", GITHUB) + code;
        }
        if (StringUtils.kmpSearch(code, "public static void main") != -1) {
            StringBuilder sb = new StringBuilder();
            int t = StringUtils.kmpSearch(code, "public static void main");
            for (int i = 0, deep = 0, start = -1; i < code.length(); i++) {
                char cc = code.charAt(i);
                if (i == t) {
                    sb.append("/**");
                    start = i;
                }
                sb.append(cc);
                if (start >= 0) {
                    if (cc == '{') {
                        deep++;
                    } else if (cc == '}') {
                        deep--;
                        if (deep == 0) {
                            sb.append("*/");
                            start = -1;
                        }
                    }
                }


            }
            return sb.toString();
        }


        return code;
    }

    private static String addComment(String code, String target) {
        return code.replace(target, String.format("// %s", target)).replace("////", "//");
    }

    public static String getId(String result, String key) {
        if (StringUtils.kmpSearch(result, key) == -1) {
            return null;
        }
        String submission_id = StringUtils.jsonStrGetValueByKey(result, key);
        submission_id = submission_id.replace("{", "").replace("}", "");
        StringBuilder cur = new StringBuilder();
        for (char c : submission_id.toCharArray()) {
            if ('0' <= c && c <= '9') {
                cur.append(c);
            }
        }
        return cur.toString();
    }


    public static void main(String[] args) {
        Class c = A.class;
        System.out.println(filterSubmitCode(c, IoUtil.readContent(String.format("%s%s.java", IoUtil.buildAbsolutePath(c), c.getSimpleName()))));
    }


}
