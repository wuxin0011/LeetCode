package code_generation.crwal.leetcode;

import code_generation.contest.ProblemInfo;
import code_generation.utils.IoUtil;
import code_generation.utils.StringUtils;

/**
 * @author: wuxin0011
 * @Description: 使用短横线方式命名规则 AAA-B-C => AAA_B_C
 */
public class LCProblemTemplate extends LCCustom {

    private String className = "Solution";
    private String txtName = IoUtil.DEFAULT_READ_FILE;

    public LCProblemTemplate(Class<?> aClass, String className, String txtName) {
        super(aClass);
        this.className = StringUtils.isEmpty(className) ? "Solution" : className;
        this.txtName = StringUtils.isEmpty(txtName) ? "Solution" : txtName;
    }

    public LCProblemTemplate(Class<?> aClass) {
        super(aClass);
    }

    @Override
    public void next() {
        String prefix_dir = StringUtils.toCaseName(this.titleSlug);
        ProblemInfo problemInfo = new ProblemInfo(this.className, this.txtName, prefix_dir, testCase, classTemplate, aClass);
        createTemplate(problemInfo);
        // start(aClass,true);
    }

}
