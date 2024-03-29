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


    private LCSolutionTemplate() {

    }


    public LCSolutionTemplate(Class<?> aClass) {
        this(aClass, null, null, null);
    }


    public LCSolutionTemplate(Class<?> aClass, String prefix) {
        this(aClass, prefix, null, null);
    }

    public LCSolutionTemplate(Class<?> aClass, String prefix, String className, String txtFile) {
        super(aClass);
        this.aClass = aClass;
        this.prefix = StringUtils.isEmpty(prefix) ? Custom_Prefix : prefix;
        this.className = StringUtils.isEmpty(className) ? Custom_Prefix : className;
        this.txtFile = StringUtils.isEmpty(txtFile) ? IoUtil.DEFAULT_READ_FILE : txtFile.lastIndexOf(".txt") != -1 ? txtFile : (txtFile + ".txt");
    }


    public void run() {
        start(aClass, true);
    }


    @Override
    public void next() {
        String prefix_dir = this.prefix + "_" + getDir(count());
        ProblemInfo problemInfo = new ProblemInfo(this.className, this.txtFile, prefix_dir, testCase, classTemplate, aClass);
        createTemplate(problemInfo);
    }

    public int count() {
        String s = IoUtil.buildAbsolutePath(this.aClass);
        File file = new File(s);
        int cnt = 0;
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1 != null && file1.isDirectory() && file1.getName().startsWith(this.prefix)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }


    public String getDir(int cnt) {
        if (cnt < 10) {
            return "000" + cnt;
        } else if (cnt < 100) {
            return "00" + cnt;
        } else if (cnt < 1000) {
            return "0" + cnt;
        } else {
            return String.valueOf(cnt);
        }
    }


}