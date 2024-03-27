package leetcode.solution;

import code_generation.contest.Problem;
import code_generation.contest.ProblemInfo;
import code_generation.crwal.leetcode.LCCustom;
import code_generation.utils.IoUtil;
import code_generation.utils.ReflectUtils;

import java.io.File;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Custom extends LCCustom {


    public static final String Custom_Prefix = "Solution";

    private static final Custom custom = new Custom(Custom.class);

    public Custom(Class<Custom> customClass) {
        super(customClass);
    }


    @Override
    public void next() {
        // String name = StringUtils.toCaseName(this.classTemplate.title);
//        String prefix_dir = "Solution_" + getDir() + "_" + name;
        String prefix_dir = Custom_Prefix + "_" + getDir();
        String name = Custom_Prefix;
        String packageInfo = ReflectUtils.getPackageInfo(IoUtil.wrapperAbsolutePath(aClass, prefix_dir));
        this.classTemplate.buildPackageInfo(packageInfo);
        // System.out.println(classTemplate);
        ProblemInfo problemInfo = new ProblemInfo(name, IoUtil.DEFAULT_READ_FILE, prefix_dir, testCase, classTemplate, aClass);
        create(problemInfo);
    }

    @Override
    public void create(ProblemInfo problemInfo) {
        Problem.create(problemInfo);
    }

    public static void main(String[] args) {
//        custom.start(Custom.class, true);
        count();
    }


    public static int count() {
        String s = IoUtil.buildAbsolutePath(Custom.class);
        File file = new File(s);
        int cnt = 0;
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1 != null && file1.isDirectory() && file1.getName().startsWith(Custom_Prefix)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }


    public static String getDir() {

        int cnt = count();
        if (cnt < 10) {
            return "000" + cnt;
        } else if (cnt < 100) {
            return "00" + cnt;
        } else if (cnt < 1000) {
            return "0" + cnt;
        } else {
            return "" + cnt;
        }
    }
}
