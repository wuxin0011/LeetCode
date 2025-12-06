package code_generation.crwal.leetcode;


import code_generation.crwal.request.Request;
import code_generation.utils.StringUtils;

import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author: wuxin0011
 * @Description:
 */
public class BuildUrl {
    public final static String LC_PREFIX = "https://leetcode.cn";
    public final static String LC_PROBLEM_PREFIX = LC_PREFIX + "/problems";
    public final static String LC_LOGIN = LC_PREFIX + "/accounts/login/";
    public final static String graphql = LC_PREFIX + "/graphql/";
    public final static String API_PREFIX = LC_PREFIX + "/contest/api/info/";
    public final static String LC_WEEKLY_CONTEST_PREFIX = LC_PREFIX + "/contest/weekly-contest";
    public final static String LC_BI_WEEKLY_CONTEST_PREFIX = LC_PREFIX + "/contest/biweekly-contest";

    public final static String QuestionWeeklyUrlsPattern = API_PREFIX + "weekly-contest-%s/";
    public final static String QuestionBiWeeklyUrlsPattern = API_PREFIX + "biweekly-contest-%s/";
    public final static String WeeklyUrlPattern = LC_WEEKLY_CONTEST_PREFIX + "-%s/problems/%s/";
    public final static String BiWeeklyUrlPattern = LC_BI_WEEKLY_CONTEST_PREFIX + "-%s/problems/%s/";

    // 经典模式url
    public final static String LC_CLASS_THEME_PREFIX = LC_PREFIX + "/classic" + "/problems";


    // 默认从当前目录下的 request_config 目录读取配置文件 这个目录会自动过滤
    public static final Request request = new Request(BuildUrl.class);

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

    /**
     * 构造一个POSt请求
     * 默认接口 url : <a href="https://leetcode.cn/graphql/">...</a>
     *
     * @param jsonStr jsonStr
     * @return 返回一个json格式字符串
     */
    public static Map<String, List<String>> getResponse(String jsonStr) {
        return request.requestPostResponse(graphql, Request.applicationJSON, jsonStr, null, true);
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
     * 查询token
     *
     * @return
     */
    public static String queryToken() {
        Map<String, List<String>> response = getResponse(LCJsonTemplate.queryToken());
        if (response == null) return "";
        List<String> ls = response.get("Set-Cookie");
        for (String s : ls) {
            String[] splits = s.split(";");
            for (String split : splits) {
                if (StringUtils.isEmpty(split)) continue;
                if (split.contains("csrftoken=")) {
                    return split.replace("csrftoken=", "");
                }
            }
        }
        return "";
    }


    public static String login(String username, String password, String csrftoken) {
        HashMap<String, String> map = new HashMap<>();
        map.put("password", password);
        map.put("login", username);
        map.put("csrfmiddlewaretoken", csrftoken);
        map.put("next", "/");
        HttpURLConnection connection = request.requestBefore(LC_LOGIN, Request.applicationJSON, null, "POST", map, true);
        System.out.println(connection);
        return "";
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
        titleSlug = split[0];
        if(StringUtils.kmpSearch(titleSlug,">")!=-1){
            titleSlug = titleSlug.substring(0,StringUtils.kmpSearch(titleSlug,">"));
        }
        if(StringUtils.kmpSearch(titleSlug,"<")!=-1){
            titleSlug = titleSlug.substring(0,StringUtils.kmpSearch(titleSlug,"<"));
        }

        titleSlug = titleSlug.replace("\"","");
        titleSlug = titleSlug.replace("<","");
        titleSlug = titleSlug.replace(">","");
        titleSlug = titleSlug.replace("'","");
        titleSlug = titleSlug.replace("(","");
        titleSlug = titleSlug.replace(")","");
        titleSlug = titleSlug.replace(",","");
        titleSlug = titleSlug.replace("\\","");
        titleSlug = titleSlug.replace("，","");
        titleSlug = titleSlug.replace("。","");
        return titleSlug;
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


    public static void requestLoginPage() {
//        HttpURLConnection connection = request.requestBefore(LC_PREFIX, Request.applicationHtml, null, "GET", null, true);
//        System.out.println(connection);
//        Map<String, List<String>> stringListMap = connection.getHeaderFields();
//        stringListMap.forEach((key, val) -> {
//            System.out.println("==================");
//            System.out.println("key = " + key + ",v = " + val);
//        });
        try {
            URL loginURL = new URL(LC_LOGIN);
            URI uri = loginURL.toURI();

            // 创建一个 CookieManager 对象来管理 Cookie
            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);

            // 获取 Cookie
            HttpCookie targetCookie = null;
            for (HttpCookie cookie : cookieManager.getCookieStore().get(uri)) {
                if ("LEETCODE_SESSION".equals(cookie.getName())) {
                    targetCookie = cookie;
                    break;
                }
            }

            if (targetCookie != null) {
                // Cookie 存在，进行后续操作
                // ...
            }
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }

    }


    public static String buildSubmitUrl(String titleTlug){
        return LC_PROBLEM_PREFIX + "/" + titleTlug +  "/submit";
    }


    /**
     * 2025-12-06
     * 提交代码接口
     * @param code 代码内容
     * @param questionId questionId
     * @param titleSlug titleSlug
     * @return submitId
     */
    public static String submitCode(String code,String questionId,String titleSlug){
        if(StringUtils.isEmpty(code)){
            throw new RuntimeException("submit code not allowed null !");
        }
        if(StringUtils.isEmpty(titleSlug)){
            throw new RuntimeException("cannot not get problem titleSlug !");
        }
        if(StringUtils.isEmpty(questionId)){
            throw new RuntimeException("cannot not get problem submit id !");
        }
        String url = String.format("%s/%s/submit/",LC_PROBLEM_PREFIX,titleSlug);
        try{Thread.sleep(3000);}catch (Exception ignored){}
        code = StringUtils.escapeForJson(code);
        String submitInfo = LCJsonTemplate.submitCode(questionId,code);
//        System.out.println("**************************************************************");
//        System.out.println(submitInfo);
//        System.out.println("**************************************************************");
        return request.requestPost(url,Request.applicationJSON,submitInfo,null);
    }


    /**
     * 2025-12-06
     * 查询提交结果
     * @param submitID submitID
     * @return 返回一个json字符串 如果ac了 会有 {statusDisplay : "Accepted"} 如果失败 {},如果还在测试中会返回一个待续中...
     */
    public static String querySubmitResult(String submitID){
        return request.requestPost(graphql,LCJsonTemplate.querySubmitStatus(submitID));
    }

    public static String queryQuestionInfo(String titleSlug){
        return request.requestPost(graphql,LCJsonTemplate.getQueryQuestionInfo(titleSlug));
    }



}
