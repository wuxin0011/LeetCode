package code_generation.crwal.leetcode;

import code_generation.contest.*;
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
 * @Description:
 */
public class LCEveryDay extends EveryDay {

    private static final LCTemplate lcTemplate = new LCTemplate();
    private static final LCTestCase lcTestCase = new LCTestCase();
    ClassTemplate classTemplate = new ClassTemplate();
    Class<?> aClass;

    String frontendQuestionId;

    public LCEveryDay() {
    }

    public LCEveryDay(Class<?> aClass) {
        this.aClass = aClass;
    }


    public void auto() {
        this.classTemplate = new ClassTemplate();
        String questionOfToday = BuildUrl.questionOfToday();
        this.frontendQuestionId = StringUtils.jsonStrGetValueByKey(questionOfToday, "frontendQuestionId");
        String titleSlug = StringUtils.jsonStrGetValueByKey(questionOfToday, "titleSlug");
        create(titleSlug);
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
        create(url);
    }

    void create(String titleSlug) {
        String t = titleSlug;
        System.out.println("titleSlug: " + titleSlug);
        String code = BuildUrl.questionEditorData(t);
        String testCase = "";
        String method = "";
        String methodName = "";

        try {
            String s = lcTemplate.parseCodeTemplate(code);
            method = StringUtils.getMethod(s);
            methodName = StringUtils.getMethodName(method);
        } catch (ParseException ignore) {

        }
        String url = BuildUrl.LC_PROBLEM_PREFIX + "/" + titleSlug;

        String questionTranslationInfo = BuildUrl.questionTranslations(t);
        boolean isNeedMod = StringUtils.isNeedMOD(questionTranslationInfo);
        String s = StringUtils.jsonStrGetValueByKey(questionTranslationInfo, "translatedContent");
        testCase = TestCaseUtil.testCaseToString(lcTestCase.parseDefault(s));

        // System.out.println(testCase);


        classTemplate.buildIsNeedMod(isNeedMod)
                .buildTitle(titleSlug)
                .buildUrl(url)
                .buildMethod(method)
                .buildMethodName(methodName);


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
        Problem.create(problemInfo);
    }

    public static boolean checkInputUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        return url.startsWith(BuildUrl.LC_PROBLEM_PREFIX);
    }


}
