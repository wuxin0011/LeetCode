package code_generation.crwal.nowcoder;

import code_generation.crwal.request.Request;

/**
 * @author: wuxin0011
 * @Description: url 请求
 */
public class NCUrl {

    public static final String PREFIX = "https://www.nowcoder.com/";
    public static final String PRACTICE_PREFIX = PREFIX + "practice/";
    public static final String QUERY_BY_UUID_URL = "https://questionbank.nowcoder.com/api/qmp/question/detail/by-uuid?uuid=";


    // https://questionbank.nowcoder.com/api/qmp/question/detail/by-uuid?uuid=75e878df47f24fdc9dc3e400ec6058ca

    private static final Request REQUEST = new Request(NCUrl.class);


    public static String getPractice(String url) {
        String uuid = NCUtil.getUUIDByPracticeUrl(url);
        return REQUEST.requestGet(QUERY_BY_UUID_URL + uuid, Request.applicationJSON);
    }


}
