package code_generation.crwal.leetcode;

import code_generation.contest.ParseCodeInfo;
import code_generation.contest.ParseCodeTemplate;

import java.text.ParseException;

/**
 * @author: wuxin0011
 * @Description:
 */
public class LCTemplate implements ParseCodeTemplate {
    public static final String JAVA_CODE_PATTERN = "\"langSlug\":\"java\",\"code\":";
    public static final String EXPECT_CLASS_NAME = "Solution";
    public static final char startFlag = '{';
    public static final char endFlag = '}';

    @Override
    public ParseCodeInfo parseCodeTemplate(String input) throws ParseException {
        ParseCodeInfo info = new ParseCodeInfo(EXPECT_CLASS_NAME);
        info.setClassName(EXPECT_CLASS_NAME);
        info.setMethod("dfs");
        info.setMethodName("");
        info.setOrigin(input);
        return info;
    }


}
