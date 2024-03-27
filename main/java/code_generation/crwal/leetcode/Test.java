package code_generation.crwal.leetcode;

import code_generation.crwal.request.Request;
import code_generation.utils.IoUtil;
import code_generation.utils.StringUtils;

import java.text.ParseException;

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
    }

    public static void testCodeSnippets() {

    }

    public static void testQuestionTranslations() throws ParseException {
        final String t = "longest-common-prefix";


        String code = BuildUrl.questionEditorData(t);
//        String code = IoUtil.readContent(Test.class, "./test_file/code.txt");

         System.out.println(code);
        LCTemplate lcTemplate = new LCTemplate();
        try {
            String s = lcTemplate.parseCodeTemplate(code);
            System.out.println("method : " + StringUtils.getMethod(s));
            System.out.println("methodName : " + StringUtils.getMethodName(StringUtils.getMethod(s)));
            System.out.println();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
        }

        String questionTranslationInfo = BuildUrl.questionTranslations(t);
        String s = StringUtils.jsonStrGetValueByKey(questionTranslationInfo, "translatedContent");
        testCase.parseDefault(s);
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


    public static void main(String[] args) throws Exception {
//        testPost();

        // testGetContest();

        // testPost();

        // testDefaultGet();

        // testUrl();

        // parseUrls();

         testDefaultProblems();

        // testCodeSnippets();

        // testEveryDay();

        // testQuestionEditorData();

        // testUserStatus();


        // testResult2();

        testQuestionTranslations();
    }


}
