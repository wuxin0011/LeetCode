package code_generation.crwal.leetcode;

import code_generation.contest.ClassTemplate;
import code_generation.contest.Constant;
import code_generation.contest.CustomProblem;
import code_generation.contest.ProblemInfo;
import code_generation.crwal.TestCaseUtil;
import code_generation.utils.IoUtil;
import code_generation.utils.ProblemEveryDayUtils;
import code_generation.utils.ReflectUtils;
import code_generation.utils.StringUtils;

import java.text.ParseException;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author: wuxin0011
 * @Description: Lc 自动抓取
 */
public class LCCustom implements CustomProblem {

    public static final LCTemplate lcTemplate = new LCTemplate();
    public static final LCTestCase lcTestCase = new LCTestCase();
    public ClassTemplate classTemplate = new ClassTemplate();
    public Class<?> aClass;

    public String frontendQuestionId;

    public LCCustom() {
    }

    public LCCustom(Class<?> aClass) {
        this.aClass = aClass;
    }


    @Override
    public void start(Class<?> c) {
        start(c, true);
    }


    @Override
    public void start(Class<?> c, boolean input) {
        Objects.requireNonNull(c, "class not allow null");
        this.aClass = c;
        String url = "";
        if (input) {
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.print("place input a problem url :  ");
                url = scanner.next();
            } while (!checkInputUrl(url));
        }
        createByTitleSlug(url);
    }

    public String testCase;

    public void createByTitleSlug(String titleSlug) {
        titleSlug = BuildUrl.buildTitleSlug(titleSlug);
        System.out.println("titleSlug: " + titleSlug);
        String code = BuildUrl.questionEditorData(titleSlug);
        this.testCase = "";
        String method = "";
        String methodName = "";

        try {
            String s = lcTemplate.parseCodeTemplate(code);
            method = StringUtils.getMethod(s);
            methodName = StringUtils.getMethodName(method);
        } catch (ParseException ignore) {

        }
        String url = BuildUrl.LC_PROBLEM_PREFIX + "/" + titleSlug;

        String questionTranslationInfo = BuildUrl.questionTranslations(titleSlug);
        boolean isNeedMod = StringUtils.isNeedMOD(questionTranslationInfo);
        String s = StringUtils.jsonStrGetValueByKey(questionTranslationInfo, "translatedContent");
        this.testCase = TestCaseUtil.testCaseToString(lcTestCase.parseDefault(s));

        // System.out.println(testCase);


        classTemplate.buildIsNeedMod(isNeedMod)
                .buildTitle(titleSlug)
                .buildUrl(url)
                .buildMethod(method)
                .buildMethodName(methodName);


        next();
    }

    public void next() {
        int count = Math.max(ProblemEveryDayUtils.getJavaFileCount(aClass), 0);
        String dir = ProblemEveryDayUtils.createPrefix(count);
        String base = ProblemEveryDayUtils.convertDir(count);
        String name = dir + Constant.FIlE_PREFIX + base;
        name = name + "_" + this.frontendQuestionId;
        String className = name.replace(dir, "").replace("\\", "");
        classTemplate.buildClassName(className);

        String javaPath = ProblemEveryDayUtils.buildJavaFilePath("", name);
        String txtPath = ProblemEveryDayUtils.buildTxtFilePath(dir, name);
        // System.out.println("name:" + name + ",txt path = " + txtPath);


        String absolutePath = IoUtil.wrapperAbsolutePath(aClass, javaPath);

        // build txt
        classTemplate.buildTextFileName(txtPath.replace(dir, ""));


        // package info
        String packageInfo = ReflectUtils.getPackageInfo(absolutePath);

        classTemplate.buildPackageInfo(packageInfo);
        // System.out.println(ClassTemplate.getTemplate(classTemplate));
        ProblemInfo problemInfo = new ProblemInfo(javaPath, txtPath, testCase, classTemplate, aClass);

        // System.out.println(problemInfo);
        create(problemInfo);
    }


    public static boolean checkInputUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        return url.startsWith(BuildUrl.LC_PROBLEM_PREFIX);
    }

}
