package code_generation.crwal.leetcode;

import code_generation.contest.ClassTemplate;
import code_generation.contest.CustomProblem;
import code_generation.contest.Problem;
import code_generation.contest.ProblemInfo;
import code_generation.crwal.TestCaseUtil;
import code_generation.utils.IoUtil;
import code_generation.utils.ReflectUtils;
import code_generation.utils.StringUtils;

import java.text.ParseException;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author: wuxin0011
 * @Description: Lc 自动抓取
 */
public abstract class LCCustom implements CustomProblem {

    public static final LCTemplate lcTemplate = new LCTemplate();
    public static final LCTestCase lcTestCase = new LCTestCase();
    public ClassTemplate classTemplate = new ClassTemplate();
    public Class<?> aClass; // 生成在那个类路径下
    public String frontendQuestionId; // 题目编号
    public String testCase; // test case
    public String titleSlug; // title name

    public LCCustom() {
    }

    public LCCustom(Class<?> aClass) {
        this.aClass = aClass;
    }

    public void run() {
        this.start(this.aClass, true);
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




    /**
     * 根据标题生成 一下信息
     * 方法
     * 方法名
     * 标题
     * 是否需要取余
     *
     * @param titleSlug 标题链接
     */
    public void createByTitleSlug(String titleSlug) {
        titleSlug = BuildUrl.buildTitleSlug(titleSlug);
        this.titleSlug = titleSlug;
        System.out.println("titleSlug: " + titleSlug);
        String code = BuildUrl.questionEditorData(titleSlug);
        this.testCase = "";
        String method = "";
        String methodName = "";

        try {
            method = StringUtils.getMethod(code);
            methodName = StringUtils.getMethodName(method);
        } catch (Exception ignore) {

        }
        String url = BuildUrl.LC_PROBLEM_PREFIX + "/" + titleSlug;

        String questionTranslationInfo = BuildUrl.questionTranslations(titleSlug);
        boolean isNeedMod = StringUtils.isNeedMOD(questionTranslationInfo);
        String s = StringUtils.jsonStrGetValueByKey(questionTranslationInfo, "translatedContent");
        this.testCase = TestCaseUtil.testCaseToString(lcTestCase.parseDefault(s));

//        BuildUrl.getDefaultQuestionDescription()

        // System.out.println(testCase);


        classTemplate.buildIsNeedMod(isNeedMod)
                .buildTitle(titleSlug)
                .buildUrl(url)
                .buildMethod(method)
                .buildMethodName(methodName);


        next();
    }


    /**
     * 根据上面题目提供的信息
     * 这个方法构造
     * Java file name  => classTemplate.buildClassName
     * txt  file name  => classTemplate.buildTextFileName
     * prefix dir
     * 包信息会自动更加 Java file 创建
     */
    public abstract void next();




    // next 方法实现后请不要忘记调用 createTemplate
    @Override
    public void createTemplate(ProblemInfo problemInfo) {
        autoCreatePackageInfo(problemInfo);
        updateClassName(problemInfo);
        Problem.create(problemInfo);
    }


    /**
     * 自动封装 package 信息
     *
     * @param problemInfo info
     */
    public void autoCreatePackageInfo(ProblemInfo problemInfo) {
        if (problemInfo == null) {
            System.out.println("auto create package info fail , is null");
            return;
        }
        String absolutePath = IoUtil.wrapperAbsolutePath(aClass, problemInfo.getJavaFile());
        // package info
        String packageInfo = ReflectUtils.getPackageInfo(absolutePath);

        classTemplate.buildPackageInfo(packageInfo);
    }


    public void updateClassName(ProblemInfo problemInfo){
    }

    public static boolean checkInputUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        return url.startsWith(BuildUrl.LC_PROBLEM_PREFIX);
    }

}
