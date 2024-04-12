package code_generation.crwal.nowcoder;

import code_generation.utils.StringUtils;

import static code_generation.crwal.nowcoder.NCUrl.PRACTICE_PREFIX;

/**
 * @author: wuxin0011
 * @Description: 工具包
 */
public class NCUtil {

    public static void main(String[] args) {

    }



    public static String getUUIDByPracticeUrl(String url){
        // https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca?tpId=295&tqId=23286&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj
        if(StringUtils.isEmpty(url)) return url;
        if(!url.startsWith(PRACTICE_PREFIX)){
            throw new RuntimeException("only allow " + PRACTICE_PREFIX + " starts with ");
        }
        url = url.replace(PRACTICE_PREFIX,"");
        int i = url.indexOf('?');
        if(i != -1){
            return url.substring(0,i);
        }
        return url;
    }
}
