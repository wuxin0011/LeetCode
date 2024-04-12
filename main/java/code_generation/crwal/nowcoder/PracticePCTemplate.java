package code_generation.crwal.nowcoder;

import code_generation.contest.ParseCodeDefaultTemplate;
import code_generation.contest.ParseCodeInfo;

/**
 * @author: wuxin0011
 * @Description:
 */
public class PracticePCTemplate extends ParseCodeDefaultTemplate {

    public static final String JAVA_CODE_PATTERN = "\"langName\":\"Java\",\"template\"";

    public PracticePCTemplate() {
        this(JAVA_CODE_PATTERN);
    }

    public PracticePCTemplate(String startFlag) {
        super(startFlag);
    }

    @Override
    public ParseCodeInfo parseCodeTemplate(String input, String startFlag) {
        ParseCodeInfo parseCodeInfo = super.parseCodeTemplate(input, startFlag);
        String method = parseCodeInfo.getMethod();
        method = method.replace("// write code here","");
        parseCodeInfo.setMethod(method);
        return parseCodeInfo;
    }
}
