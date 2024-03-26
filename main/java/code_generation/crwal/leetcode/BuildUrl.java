package code_generation.crwal.leetcode;


import code_generation.crwal.request.Request;

/**
 * @author: wuxin0011
 * @Description:
 */
public class BuildUrl {
    public final static String LC_PREFIX = "https://leetcode.cn";
    public final static String LC_PROBLEM_PREFIX = LC_PREFIX + "/problems";
    public final static String graphql = "https://leetcode.cn/graphql/";
    public final static String API_PREFIX = "https://leetcode.cn/contest/api/info/";
    public final static String LC_WEEKLY_CONTEST_PREFIX = "https://leetcode.cn/contest/weekly-contest";
    public final static String LC_BI_WEEKLY_CONTEST_PREFIX = "https://leetcode.cn/contest/biweekly-contest";

    public final static String QuestionWeeklyUrlsPattern = API_PREFIX + "weekly-contest-%s/";
    public final static String QuestionBiWeeklyUrlsPattern = API_PREFIX + "biweekly-contest-%s/";
    public final static String WeeklyUrlPattern = LC_WEEKLY_CONTEST_PREFIX + "-%s/problems/%s/";
    public final static String BiWeeklyUrlPattern = LC_BI_WEEKLY_CONTEST_PREFIX + "-%s/problems/%s/";


    private static final Request request = new Request(BuildUrl.class);


    /**
     * 获取页面题目描述信息
     * Example
     * <a href="https://leetcode.cn/problems/maximum-length-substring-with-two-occurrences/">...</a>
     * titleSlug=> maximum-length-substring-with-two-occurrences
     *
     * @param titleSlug 题目后缀 一般
     * @return 返回一个json字符串
     */
    public static String getDefaultQuestionDescription(String titleSlug) {
        if (titleSlug == null || titleSlug.isEmpty()) {
            throw new NullPointerException();
        }
        if (titleSlug.contains(LC_PREFIX) && !titleSlug.startsWith(LC_PROBLEM_PREFIX)) {
            throw new RuntimeException("not support" + titleSlug);
        }
        if (!titleSlug.startsWith(LC_PROBLEM_PREFIX)) {
            titleSlug = LC_PROBLEM_PREFIX + titleSlug;
        }
        return request.requestGet(titleSlug, null, null);
    }


    /**
     * 获取比赛题目信息
     *
     * @param url url
     * @return 返回一个html页面
     */
    public static String getContestQuestionPage(String url) {
        return request.requestGet(url, Request.applicationHtml);
    }


    /**
     * 获取比赛题目信息
     *
     * @param url url
     * @return 返回一个json格式字符串
     */
    public static String getContestUrls(String url) {
        return request.requestGet(url, Request.applicationJSON);
    }


    /**
     * 构造一个POSt请求
     *
     * @param url     url
     * @param jsonStr jsonStr
     * @return 返回一个json格式字符串
     */
    public static String getPost(String url, String jsonStr) {
        return request.requestPost(url, jsonStr);
    }


    /**
     * 构造一个POSt请求
     * 默认接口 url : <a href="https://leetcode.cn/graphql/">...</a>
     *
     * @param jsonStr jsonStr
     * @return 返回一个json格式字符串
     */
    public static String getPost(String jsonStr) {
        return request.requestPost(graphql, jsonStr);
    }


    /**
     * 获取今日提交信息
     *
     * @return 返回一个json格式字符串
     */
    public static String questionOfToday() {
        return getPost(LCJsonTemplate.questionOfToday());
    }


    public static String getWeeklyContestUrls(String id) {
        String url = String.format(QuestionWeeklyUrlsPattern, id);
        return request.requestGet(url, Request.applicationJSON);
    }

    public static String getBiWeeklyContestUrls(String id) {
        String url = String.format(QuestionBiWeeklyUrlsPattern, id);
        return request.requestGet(url, Request.applicationJSON);
    }


    public static String getWeeklyContestProblem(String id, String title) {
        String url = String.format(WeeklyUrlPattern, id, title);
        return request.requestGet(url, Request.applicationHtml);
    }

    public static String getBiWeeklyContestProblem(String id, String title) {
        String url = String.format(BiWeeklyUrlPattern, id, title);
        return request.requestGet(url, Request.applicationHtml);
    }


}
