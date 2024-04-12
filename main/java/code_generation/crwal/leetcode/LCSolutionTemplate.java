package code_generation.crwal.leetcode;

import code_generation.contest.ProblemInfo;
import code_generation.utils.IoUtil;
import code_generation.utils.StringUtils;

import java.io.File;

/**
 * @author: wuxin0011
 * @Description: 这个类能够根据自定义前缀文件生成对应模板 如 Solution_00001 、dp_0000 、dp_0001 、stack_0000
 */
public class LCSolutionTemplate extends LCCustom {

    private static final String Custom_Prefix = "Solution";

    private Class<?> aClass;
    private String prefix;

    private String className;
    private String txtFile;

    private boolean createReadme; // 是否创建 readme.md 文件


    private LCSolutionTemplate() {

    }


    public LCSolutionTemplate(Class<?> aClass) {
        this(aClass, null, null, null);
    }

    public LCSolutionTemplate(Class<?> aClass, boolean createReadme) {
        this(aClass, null, null, null, createReadme);
    }

    public LCSolutionTemplate(Class<?> aClass, String prefix) {
        this(aClass, prefix, null, null);
    }


    public LCSolutionTemplate(Class<?> aClass, String prefix, String className, String txtFile) {
        this(aClass, prefix, className, txtFile, false);
    }

    public LCSolutionTemplate(Class<?> aClass, String prefix, String className, String txtFile, boolean createReadme) {
        super(aClass);
        this.aClass = aClass;
        this.prefix = StringUtils.isEmpty(prefix) ? Custom_Prefix : prefix;
        this.className = StringUtils.isEmpty(className) ? Custom_Prefix : className;
        this.txtFile = StringUtils.isEmpty(txtFile) ? IoUtil.DEFAULT_READ_FILE : txtFile.lastIndexOf(".txt") != -1 ? txtFile : (txtFile + ".txt");
        this.createReadme = createReadme;
    }


    @Override
    public void next() {
        String prefix_dir = this.prefix + "_" + StringUtils.getDirCount(aClass, prefix);
        ProblemInfo problemInfo = new ProblemInfo(this.className, this.txtFile, prefix_dir, testCase, classTemplate, aClass);
        // create readme
        if (createReadme) {
            IoUtil.writeContent(aClass, prefix_dir + File.separator + "readme.md", "");
        }
        createTemplate(problemInfo);
    }


}
