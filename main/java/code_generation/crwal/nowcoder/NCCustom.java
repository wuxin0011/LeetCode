package code_generation.crwal.nowcoder;

import code_generation.contest.ClassTemplate;
import code_generation.contest.CustomProblem;
import code_generation.contest.ParseCodeInfo;
import code_generation.contest.ProblemInfo;
import code_generation.utils.IoUtil;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author: wuxin0011
 * @Description:
 */
public abstract class NCCustom implements CustomProblem {
    public Class<?> aClass;
    public ParseCodeInfo parseCodeInfo;
    public ClassTemplate classTemplate;
    public ProblemInfo info;
    public String prefix;
    public String className;
    public String txtFile;

    boolean createReadme; // 是否创建 readme.md 文件


    @Override
    public void start(Class<?> c) {

        this.start(c, true);
    }

    @Override
    public void start(Class<?> c, boolean input) {
        Objects.requireNonNull(c, "class not allow null");
        this.aClass = c;
        Scanner scanner = new Scanner(System.in);
        String s = "";
        do {
            System.out.println("input a url : ");
            s = scanner.next();
        } while (!support(s));

        parse(s);
    }

    public void run() {
        this.start(aClass, true);
    }

    public abstract void parse(String url);


    public abstract boolean support(String s);


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
