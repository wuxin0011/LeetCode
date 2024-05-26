package code_generation.crwal.leetcode;

import code_generation.crwal.request.Request;
import code_generation.utils.IoUtil;
import code_generation.utils.StringUtils;

import java.text.ParseException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Test {

    private static final String HOST = "leetcode.cn";
    private static final String referrer = "https://" + HOST + "/";
    private static final String graphql = "https://" + HOST + "/graphql";
    private static final String titleSlug = "count-submatrices-with-top-left-element-and-sum-less-than-k";

    public static final Request request = new Request(Test.class);


    public static final LCTestCase testCase = new LCTestCase();




    static void testPost() {

        final String title_url = "count-submatrices-with-top-left-element-and-sum-less-than-k";
        // 获取全部信息 error
        // System.out.println(request.requestPost(graphql, JsonStrTemplate.getTotalInfo()));

        // 获取测试案例 OK
         System.out.println(request.requestPost(graphql, LCJsonTemplate.createQuestionTestCase("count-submatrices-with-top-left-element-and-sum-less-than-k")));


        // submit OK
        // System.out.println(request.requestPost(graphql, JsonStrTemplate.createQuestionTagInfoSubmitInfo(title_url)));

        // 题目类型 ok
        // System.out.println(request.requestPost(graphql, JsonStrTemplate.createQuestionTagInfo(title_url)));


        // 描述 ok
        System.out.println(request.requestPost(graphql, LCJsonTemplate.questionTitle(title_url)));


        // System.out.println(request.requestPost("https://leetcode.cn/graphql/noj-go", JsonStrTemplate.getUserInfo()));
    }


    /**
     * test result OK
     */
    static void testGetContest() {

        String url = "https://leetcode.cn/contest/weekly-contest-235/problems/number-of-different-subsequences-gcds";
        String html = BuildUrl.getContestQuestionPage(url);
        // String html = IoUtil.readContent("D:\\desktop\\project\\Learn\\leetcode\\temp\\235-d.html");
        // System.out.println("html \n\n" + html);

        System.out.println(testCase.parseContest(html));
        System.out.println("=======================");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        String s = request.requestGet(url);
        System.out.println(testCase.parseContest(s));
    }


    static void testDefaultGet() {
//        HashMap<String, String> cookies = new HashMap<>();
//         cookies.put("csrftoken", "77MX6yVgwMPRyE9PvsnzRNfTLaSrTPvYMkHwymTUTKRyt5IIzQ0hmEMQQUTDOq7Y");
//        request.setCookies(cookies);
//        Map<String, String> headers = request.getHeaders();
//        headers.put("Referer", "https://leetcode.cn/contest/weekly-contest-390/");
//        System.out.println("Referer=" + request.getHeaders().get("Referer"));
//        String content = request.requestGet("https://leetcode.cn/problems/count-submatrices-with-top-left-element-and-sum-less-than-k/description/");
//
//        System.out.println(content);
    }


    public static void testUrl() {
        String s = request.requestGet("https://leetcode.cn/contest/api/info/weekly-contest-390/", Request.applicationJSON, null);
        System.out.println(s);
    }


    public static void testDefaultProblems() {

        // String defaultQuestionDescription = BuildUrl.getDefaultQuestionDescription(titleSlug);
        // System.out.println(defaultQuestionDescription);
        String testCase = BuildUrl.getCreateQuestionTestCase(titleSlug);
        System.out.println(testCase);
    }


    // 测试每日一题
    public static void testEveryDay() {
        String s = BuildUrl.questionOfToday();
        System.out.println(s);
    }


    // questionEditorData
    public static void testQuestionEditorData() {
        String s = BuildUrl.questionEditorData(titleSlug);
        System.out.println(s);
    }


    // test user status
    public static void testUserStatus() {
        String s = BuildUrl.userStatus();
        System.out.println(s);
        System.out.println(StringUtils.jsonStrGetValueByKey(s,"username"));
        System.out.println(StringUtils.jsonStrGetValueByKey(s,"realName"));
        System.out.println(StringUtils.jsonStrGetValueByKey(s,"avatar"));
        System.out.println(StringUtils.jsonStrGetValueByKey(s,"userSlug"));
    }

    public static void testCodeSnippets() {

    }

    public static void testQuestionTranslations() throws ParseException {
        final String t = "longest-common-prefix";


        // String code = BuildUrl.questionEditorData(t);
        // String code = IoUtil.readContent(Test.class, "./test_file/code.txt");
        String code = IoUtil.readContent(Test.class, "./test_file/constructor-code.txt");

        System.out.println(code);
        LCTemplate lcTemplate = new LCTemplate();
        System.out.println(lcTemplate.parseCodeTemplate(code));
//        String questionTranslationInfo = BuildUrl.questionTranslations(t);
//        String s = StringUtils.jsonStrGetValueByKey(questionTranslationInfo, "translatedContent");
//        testCase.parseDefault(s);
    }

    public static void getContestCode() throws ParseException {
        // https://leetcode.cn/contest/biweekly-contest-127/problems/find-the-sum-of-subsequence-powers/
        // String url = BuildUrl.getBiWeeklyContestProblem("127", "find-the-sum-of-subsequence-powers");
        String input = request.requestGet("https://leetcode.cn/contest/biweekly-contest-127/problems/find-the-sum-of-subsequence-powers/");
        // System.out.println(html);
        int i1 = StringUtils.kmpSearch(input, "var pageData");
        System.out.println(i1);
        input = input.substring(i1);
        int i11 = StringUtils.kmpSearch(input, "</script>");
        input = input.substring(0, i11);
        System.out.println(input);
    }


    private static void testResult() throws Exception {
        // https://leetcode.cn/problems/count-ways-to-group-overlapping-ranges/interpret_solution/
        String s = request.requestPost("https://leetcode.cn/problems/count-ways-to-group-overlapping-ranges/interpret_solution/", Str);
//        String s = IoUtil.readContent(Test.class,"./test_file/interpret.txt");
        // System.out.println(s);
        String interpret_id = StringUtils.ingoreString(StringUtils.jsonStrGetValueByKey(s, "interpret_id"));
        String interpret_expected_id = StringUtils.ingoreString(StringUtils.jsonStrGetValueByKey(s, "interpret_expected_id"));
        // System.out.println("interpret_id=" + interpret_id + ",interpret_expected_id=" + interpret_expected_id);

        String s1 = request.requestGet(String.format("https://leetcode.cn/submissions/detail/%s/check/", interpret_id), Request.applicationJSON);
        // System.out.println(s1);
        Thread.sleep(500);
        s1 = request.requestGet(String.format("https://leetcode.cn/submissions/detail/%s/check/", interpret_id), Request.applicationJSON);
        // System.out.println(s1);
        String code_answer = StringUtils.ingoreString(StringUtils.jsonStrGetValueByKey(s1, "code_answer"));
        String expected_code_answer = StringUtils.ingoreString(StringUtils.jsonStrGetValueByKey(s1, "expected_code_answer"));
        System.out.println(code_answer);
        System.out.println(expected_code_answer);
    }

    private static void testResult2() throws Exception {
        String s1 = IoUtil.readContent(Test.class, "./test_file/check.txt");
        System.out.println(s1);
        String code_answer = StringUtils.ingoreString(StringUtils.jsonStrGetValueByKey(s1, "code_answer"));
        String expected_code_answer = StringUtils.ingoreString(StringUtils.jsonStrGetValueByKey(s1, "expected_code_answer"));
        System.out.println(code_answer);
        System.out.println(expected_code_answer);
    }


    private static final String Str = "{\n" +
            "    \"lang\": \"java\",\n" +
            "    \"question_id\": \"2651\",\n" +
            "    \"typed_code\": \"class Solution {\\n    public int countWays(int[][] ranges) {\\n\\n\\n        return 0;\\n    }\\n}\",\n" +
            "    \"data_input\": \"[[6,10],[5,15]]\\n[[1,3],[10,20],[2,5],[4,8]]\"\n" +
            "}";


    public static void queryNewContestQuestion() {
        String info = BuildUrl.queryNewContestQuestion("https://leetcode.cn/contest/weekly-contest-398/problems/special-array-i/");

//        String info = IoUtil.readContent(Test.class, "./test_file/newContestInfo.txt");
        System.out.println("info");
        String p = "\"lang\":\"Java\",\"langSlug\":\"java\"";
        System.out.println(info);
        System.out.println(findCode(info, StringUtils.kmpSearch(info, p)));
        System.out.println(testCase.parseContest(StringUtils.jsonStrGetValueByKey(info, "translatedContent")));
    }

    public static String findCode(String codeInfo, int ed) {
        int deep = 0;
        StringBuilder sb = new StringBuilder();
        boolean find = false;
        final String classStr = "class";
        for (int i = ed; i >= classStr.length(); i--) {
            char c = codeInfo.charAt(i);
            if (c == '}') {
                deep++;
                find = true;
            } else if (c == '{') {
                deep--;
                find = true;
            }
            sb.append(c);
            if (codeInfo.startsWith(classStr, i)) {
                break;
            }
        }

        return sb.reverse().toString();
    }


    public static void testLoginInfo() {
        Map<String, String> getenv = System.getenv();
        // System.out.println(getenv);
        String username = System.getenv("LEET_USERNAME");
        String password = System.getenv("LEET_PASSWORD");
        String csrftoken = BuildUrl.queryToken();
        if (StringUtils.isEmpty(csrftoken)) {
            System.out.println("is NULL");
        } else {
            System.out.println("csrftoken = " + csrftoken);
            BuildUrl.login(username, password, csrftoken);
            BuildUrl.requestLoginPage();
        }


    }


    public static void main(String[] args) throws Exception {
//        testPost();

        // testGetContest();

        // testPost();

        // testDefaultGet();

        // testUrl();

        // parseUrls();

        // testDefaultProblems();

        // testCodeSnippets();

        // testEveryDay();

        // testQuestionEditorData();

        // testUserStatus();


        // testResult2();

        // testQuestionTranslations();

        // getContestCode();

        // fail
        // getProblemsTitle();
        //

        // 测试构造函数类
        // testConstructor();
        // testConstructor2();


        // 测试新版本接口
        // queryNewContestQuestion();


        testLoginInfo();
    }

    private static void getProblemsTitle() {
        String s = request.requestGet("https://leetcode.cn/problems/all-possible-full-binary-trees/description/", Request.applicationHtml);
        String titleFlag = "<title>";
        String titleEnd = "</title>";
        System.out.println(s);
        int i1 = StringUtils.kmpSearch(s, titleFlag);
        int i2 = StringUtils.kmpSearch(s, titleEnd);
        // System.out.println(s.substring(i1+titleFlag.length(),i2));

    }


    public static void testConstructor() throws ParseException {
        String url = "https://leetcode.cn/problems/kth-ancestor-of-a-tree-node/description/?envType=daily-question&envId=2024-04-06";
        // String translations = BuildUrl.questionTranslations(url);
        String translations = IoUtil.readContent(Test.class, "./test_file/constructor-desc.txt");
        // \u5b9e\u73b0  \u7c7b
        Pattern compile = Pattern.compile("\\\\u5b9e\\\\u73b0(.*)\\\\u7c7b", Pattern.DOTALL);
        Matcher matcher = compile.matcher(translations);
        String ans = "";
        if (matcher.find()) {
            // System.out.println(matcher.group());
            ans = matcher.group();
        } else {
            System.out.println("NO");
        }


        ans = ans.replace("\\u5b9e", "");
        ans = ans.replace("\\u73b0", "");
        ans = ans.replace("\\u7c7b", "");
        ans = ans.replace("<code>", "");
        ans = ans.replace("</code>", "");
        ans = ans.replace(" ", "");
        System.out.println("result = " + ans);

        // System.out.println(translations);


        String code = IoUtil.readContent(Test.class, "./test_file/constructor-code.txt");
        System.out.println(code);
        System.out.println(StringUtils.getMethod(code));


        System.out.println(new LCTestCase().parseDefault(translations));
    }

    public static void testConstructor2() throws ParseException {
        String url = "https://leetcode.cn/problems/implement-trie-prefix-tree/?envType=study-plan-v2&envId=top-interview-150";
         String translations = BuildUrl.questionTranslations(url);
        // String translations = IoUtil.readContent(Test.class, "./test_file/constructor-desc.txt");
        // \u5b9e\u73b0  \u7c7b
        Pattern compile = Pattern.compile("\\\\u5b9e\\\\u73b0(.*)\\\\u7c7b", Pattern.DOTALL);
        Matcher matcher = compile.matcher(translations);
        String ans = "";
        if (matcher.find()) {
            ans = matcher.group();
            System.out.println("match result = " + ans);

        } else {
            System.out.println("NO");
        }


        ans = ans.replace("\\u5b9e", "");
        ans = ans.replace("\\u73b0", "");
        ans = ans.replace("\\u7c7b", "");
        ans = ans.replace("<code>", "");
        ans = ans.replace("</code>", "");
        ans = ans.replace(" ", "");
        System.out.println("result = " + ans);

        // System.out.println(translations);


        // String code = IoUtil.readContent(Test.class, "./test_file/constructor-code.txt");
        String code = BuildUrl.questionEditorData(url);
        System.out.println(code);
        LCTemplate lcTemplate = new LCTemplate();
        System.out.println(lcTemplate.parseCodeTemplate(code));
        // System.out.println(new LCTestCase().parseDefault(translations));
    }


}
