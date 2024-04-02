package code_generation.crwal.leetcode;

import code_generation.contest.ParseCodeTemplate;
import code_generation.utils.StringUtils;

import java.text.ParseException;

/**
 * @author: wuxin0011
 * @Description:
 */
public class LCTemplate implements ParseCodeTemplate {
    public static final String JAVA_CODE_PATTERN = "\"langSlug\":\"java\",\"code\":";
    public static final char startFlag = '{';
    public static final char endFlag = '}';

    @Override
    public String parseCodeTemplate(String input) throws ParseException {
        if (StringUtils.isEmpty(input)) {
            return input;
        }
        return StringUtils.getMethod(input, JAVA_CODE_PATTERN);
    }


}
