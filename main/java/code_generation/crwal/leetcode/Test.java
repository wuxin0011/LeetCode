package code_generation.crwal.leetcode;

import code_generation.crwal.Constant;
import code_generation.crwal.request.Request;

import java.util.HashMap;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Test {

    private static final String HOST = "leetcode.cn";
    private static final String referrer = "https://" + HOST + "/";
    private static final String graphql = "https://" + HOST + "/graphql";
    private static final String[] strings = {Constant.csrftoken, Constant.LEETCODE_SESSION};
    private static final Request request = new Request(HOST, referrer, Test.class, strings);

    public static void main(String[] args) throws Exception {
//        testGetContest();
//        testPost();
        testDefaultGet();
    }

    static void testPost() {
        HashMap<String, String> body = new HashMap<>();
        body.put("operationName", "globalData");
        body.put("query", "query globalData {\n  feature {\n    questionTranslation\n    subscription\n    signUp\n    discuss\n    mockInterview\n    contest\n    store\n    book\n    chinaProblemDiscuss\n    socialProviders\n    studentFooter\n    cnJobs\n    __typename\n  }\n  userStatus {\n    isSignedIn\n    isAdmin\n    isStaff\n    isSuperuser\n    isTranslator\n    isPremium\n    isVerified\n    isPhoneVerified\n    isWechatVerified\n    checkedInToday\n    username\n    realName\n    userSlug\n    groups\n    jobsCompany {\n      nameSlug\n      logo\n      description\n      name\n      legalName\n      isVerified\n      permissions {\n        canInviteUsers\n        canInviteAllSite\n        leftInviteTimes\n        maxVisibleExploredUser\n        __typename\n      }\n      __typename\n    }\n    avatar\n    optedIn\n    requestRegion\n    region\n    activeSessionId\n    permissions\n    notificationStatus {\n      lastModified\n      numUnread\n      __typename\n    }\n    completedFeatureGuides\n    useTranslation\n    __typename\n  }\n  siteRegion\n  chinaHost\n  websocketUrl\n}\n");
        System.out.println(request.requestPost(graphql, body));
    }

    static void testGetContest() {
        HashMap<String, String> cookies = new HashMap<>();
        cookies.put("csrftoken", "77MX6yVgwMPRyE9PvsnzRNfTLaSrTPvYMkHwymTUTKRyt5IIzQ0hmEMQQUTDOq7Y");
        cookies.put("LEETCODE_SESSION", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfYXV0aF91c2VyX2lkIjoiNDc4MDY2NyIsIl9hdXRoX3VzZXJfYmFja2VuZCI6ImF1dGhlbnRpY2F0aW9uLmF1dGhfYmFja2VuZHMuUGhvbmVBdXRoZW50aWNhdGlvbkJhY2tlbmQiLCJfYXV0aF91c2VyX2hhc2giOiI5MDljYTgyYzFlODM4OWZmMzQ2OTAzMDYxNTdlNzU4OGFkZGVmN2VkMzM2YzZjNzgzZDhhM2VjZGUyOWQzNjc4IiwiaWQiOjQ3ODA2NjcsImVtYWlsIjoiIiwidXNlcm5hbWUiOiJhZ2l0YXRlZC1jdXJyYW5mbmQiLCJ1c2VyX3NsdWciOiJhZ2l0YXRlZC1jdXJyYW5mbmQiLCJhdmF0YXIiOiJodHRwczovL2Fzc2V0cy5sZWV0Y29kZS5jbi9hbGl5dW4tbGMtdXBsb2FkL3VzZXJzL2FnaXRhdGVkLWN1cnJhbmZuZC9hdmF0YXJfMTcwOTk2MzY3NC5wbmciLCJwaG9uZV92ZXJpZmllZCI6dHJ1ZSwiX3RpbWVzdGFtcCI6MTcxMTI2NjcxNy44MDA1NzI5LCJleHBpcmVkX3RpbWVfIjoxNzEzODEyNDAwLCJ2ZXJzaW9uX2tleV8iOjEsImxhdGVzdF90aW1lc3RhbXBfIjoxNzExMjY2OTI1fQ.xz9L6hI4HqcIMdzCfuSw0pDtRA-HbpHzlmXECzU9JL0");
        request.setCookies(cookies);
        String content = request.requestGet("https://leetcode.cn/contest/weekly-contest-390/problems/maximum-length-substring-with-two-occurrences/");
        System.out.println(content);
    }


    static void testDefaultGet() {
//        HashMap<String, String> cookies = new HashMap<>();
//         cookies.put("csrftoken", "77MX6yVgwMPRyE9PvsnzRNfTLaSrTPvYMkHwymTUTKRyt5IIzQ0hmEMQQUTDOq7Y");
//        request.setCookies(cookies);
        String content = request.requestGet("https://leetcode.cn/problems/maximum-length-substring-with-two-occurrences/");

        System.out.println(content);
    }


}
