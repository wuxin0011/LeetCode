package code_generation.crwal.leetcode;

import code_generation.crwal.Constant;
import code_generation.crwal.request.Request;

import java.util.Map;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Test {

    private static final String HOST = "leetcode.cn";
    private static final String referrer = "https://" + HOST + "/";
    private static final String graphql = "https://" + HOST + "/graphql";

    public static final Request request = new Request(Test.class);


    static void testPost() {

        final String title_url = "count-submatrices-with-top-left-element-and-sum-less-than-k";
        // 获取全部信息 error
        // System.out.println(request.requestPost(graphql, JsonStrTemplate.getTotalInfo()));

        // 获取测试案例 OK
        // System.out.println(request.requestPost(graphql, JsonStrTemplate.createQuestionTestCase("count-submatrices-with-top-left-element-and-sum-less-than-k")));


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
        LCTestCase testCase = new LCTestCase();
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
        Map<String, String> headers = request.getHeaders();
        headers.put("Referer", "https://leetcode.cn/contest/weekly-contest-390/");
        System.out.println("Referer=" + request.getHeaders().get("Referer"));
        String content = request.requestGet("https://leetcode.cn/problems/count-submatrices-with-top-left-element-and-sum-less-than-k/description/");

        System.out.println(content);
    }


    public static void testUrl() {
        String s = request.requestGet("https://leetcode.cn/contest/api/info/weekly-contest-390/", Request.applicationJSON, null);
        System.out.println(s);
    }


    public static void main(String[] args) throws Exception {
        testGetContest();
        // testPost();
//        testDefaultGet();
        // testUrl();
        // parseUrls();
    }


}
