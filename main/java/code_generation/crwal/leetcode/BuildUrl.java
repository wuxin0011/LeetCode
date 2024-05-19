package code_generation.crwal.leetcode;


import code_generation.crwal.request.Request;
import code_generation.utils.StringUtils;

/**
 * @author: wuxin0011
 * @Description:
 */
public class BuildUrl {
    public final static String LC_PREFIX = "https://leetcode.cn";
    public final static String LC_PROBLEM_PREFIX = LC_PREFIX + "/problems";
    public final static String graphql = LC_PREFIX + "/graphql/";
    public final static String API_PREFIX = LC_PREFIX + "/contest/api/info/";
    public final static String LC_WEEKLY_CONTEST_PREFIX = LC_PREFIX + "/contest/weekly-contest";
    public final static String LC_BI_WEEKLY_CONTEST_PREFIX = LC_PREFIX + "/contest/biweekly-contest";

    public final static String QuestionWeeklyUrlsPattern = API_PREFIX + "weekly-contest-%s/";
    public final static String QuestionBiWeeklyUrlsPattern = API_PREFIX + "biweekly-contest-%s/";
    public final static String WeeklyUrlPattern = LC_WEEKLY_CONTEST_PREFIX + "-%s/problems/%s/";
    public final static String BiWeeklyUrlPattern = LC_BI_WEEKLY_CONTEST_PREFIX + "-%s/problems/%s/";


    // 默认从当前目录下的 request_config 目录读取配置文件 这个目录会自动过滤
    private static final Request request = new Request(BuildUrl.class);

    // 如果自定义路径 ？
    // 一定要使用绝对路径！
    // private static final Request request = new Request("D:\\desktop\\config");


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

        return request.requestPost(buildTitleSlugUrl(titleSlug), buildTitleSlug(titleSlug));
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


    /**
     * 获取普通题目测试案例 这个接口不好用 BuildUrl#questionTranslations
     *
     * @param titleSlug titleSlug
     * @return jsonStr
     * #
     */
    @Deprecated
    public static String getCreateQuestionTestCase(String titleSlug) {
        return getPost(LCJsonTemplate.createQuestionTestCase(buildTitleSlug(titleSlug)));
    }


    /**
     * 每日一题
     *
     * @return
     */
    public static String questionOfToday() {
        return getPost(LCJsonTemplate.questionOfToday());
    }

    /**
     * 编辑器状态 通过这个获取 代码块
     *
     * @return
     */
    public static String questionEditorData(String titleSlug) {
        return getPost(LCJsonTemplate.questionEditorData(buildTitleSlug(titleSlug)));
    }

    /**
     * 当前账号状态信息
     *
     * @return
     */
    public static String userStatus() {
        return getPost(LCJsonTemplate.userStatus());
    }


    public static String buildTitleSlugUrl(String titleSlug) {
        if (StringUtils.isEmpty(titleSlug)) {
            throw new NullPointerException();
        }
        if (titleSlug.contains(LC_PREFIX) && !titleSlug.startsWith(LC_PROBLEM_PREFIX)) {
            throw new RuntimeException("not support" + titleSlug);
        }
        if (!titleSlug.startsWith(LC_PROBLEM_PREFIX)) {
            titleSlug = LC_PROBLEM_PREFIX + titleSlug;
        }
        return titleSlug;
    }

    public static String buildTitleSlug(String titleSlug) {
        if (StringUtils.isEmpty(titleSlug)) {
            throw new NullPointerException();
        }
        if (titleSlug.startsWith(LC_PROBLEM_PREFIX)) {
            titleSlug = titleSlug.replace(LC_PROBLEM_PREFIX + "/", "");
        }
        if (titleSlug.startsWith(LC_WEEKLY_CONTEST_PREFIX) || titleSlug.startsWith(LC_BI_WEEKLY_CONTEST_PREFIX)) {
            if (!titleSlug.contains("/problems/")) {
                throw new RuntimeException("NOT support" + titleSlug);
            }
            String[] split = titleSlug.split("/problems/");
            titleSlug = split[1];
        }
        String[] split = titleSlug.split("/");
        return split[0];
    }


    /**
     * 题目描述信息
     *
     * @return
     */
    public static String questionTranslations(String titleSlug) {
        return getPost(LCJsonTemplate.questionTranslations(buildTitleSlug(titleSlug)));
    }


    public static String queryNewContestQuestion(String contestUrl) {
        String titleSlug = buildTitleSlug(contestUrl);
        // https://leetcode.cn/contest/weekly-contest-398/problems/special-array-i/

        String contestNo = contestUrl.replace(LC_PREFIX + "/contest/", "").split("/problems")[0];

        return getPost(LCJsonTemplate.newContestQuestion(contestNo, titleSlug));
    }
}
