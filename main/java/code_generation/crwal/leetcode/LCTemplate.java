package code_generation.crwal.leetcode;

import code_generation.contest.ParseCodeDefaultTemplate;

/**
 * @author: wuxin0011
 * @Description:
 */
public class LCTemplate extends ParseCodeDefaultTemplate {
    public static final String JAVA_CODE_PATTERN = "\"langSlug\":\"java\",\"code\":";

    public LCTemplate() {
        this(JAVA_CODE_PATTERN);
    }
    public LCTemplate(String startFlag) {
        super(startFlag);
    }
}
