package code_generation.crwal.nowcoder;

import code_generation.contest.ClassTemplate;
import code_generation.contest.Problem;
import code_generation.contest.ProblemInfo;
import code_generation.utils.IoUtil;
import code_generation.utils.StringUtils;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author: wuxin0011
 * @Description:
 */
public class NCPractice extends NCCustom {

    Practice practice;

    public static final String PREFIX = "Solution";

    public NCPractice(Class<?> c) {
        this(c, null, null, null, false);
    }

    public NCPractice(Class<?> c, String prefix, String javaClass, String txt, boolean is) {
        this.aClass = c;
        this.prefix = StringUtils.isEmpty(prefix) ? PREFIX : prefix;
        this.className = StringUtils.isEmpty(javaClass) ? PREFIX : javaClass;
        this.txtFile = StringUtils.isEmpty(txt) ? IoUtil.DEFAULT_READ_FILE : txt;
        this.createReadme = is;
    }

    @Override
    public void start(Class<?> c, boolean input) {
        Objects.requireNonNull(c, "class not allow null");
        this.aClass = c;
        Scanner scanner = new Scanner(System.in);
        String s = "";
        do {
            System.out.println("input https://www.nowcoder.com/exam/oj problems url:");
            s = scanner.next();
        } while (!support(s));

        parse(s);
    }

    @Override
    public void parse(String url) {
        String input = NCUrl.getPractice(url);
        this.practice = new Practice(input);
        this.parseCodeInfo = practice.codeInfo;
        this.classTemplate = new ClassTemplate();
        String test = practice.test;


        classTemplate.buildClassName(className)
                .buildTitle("")
                .buildCodeInfo(parseCodeInfo)
                .buildMethod(parseCodeInfo.getMethod())
                .buildMethodName(parseCodeInfo.getMethodName())
                .buildTextFileName(txtFile)
                .buildUrl(url);
        String prefix_dir = this.prefix + "_" + StringUtils.getDirCount(aClass, prefix);
        ProblemInfo problemInfo = new ProblemInfo(className, txtFile, prefix_dir, test, classTemplate, aClass);
        this.info = problemInfo;
        createTemplate(problemInfo);
        if (createReadme) {
            IoUtil.writeContent(aClass, prefix_dir + File.separator + "readme.md", "");
        }

    }

    @Override
    public void createTemplate(ProblemInfo problemInfo) {
        Problem.create(problemInfo);
    }


    public boolean support(String s) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        return s.startsWith(NCUrl.PRACTICE_PREFIX);
    }

}
