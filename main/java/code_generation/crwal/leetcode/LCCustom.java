package code_generation.crwal.leetcode;

import code_generation.contest.*;
import code_generation.crwal.TestCaseUtil;
import code_generation.utils.ExceptionUtils;
import code_generation.utils.IoUtil;
import code_generation.utils.ReflectUtils;
import code_generation.utils.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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
    public  ParseCodeInfo parseCodeInfo; // parseCodeInfo

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

        List<String> urls = new ArrayList<>();
        if (input) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("input a content contains https://leetcode.cn/problems/xxxxxx/ text and input NO exit: \n");

            // 读取所有输入内容
            StringBuilder inputContent = new StringBuilder();
            String line = "";
            while (!StringUtils.isEmpty((line = scanner.nextLine()))) {
                if (StringUtils.isEmpty(line)) {
                    break;
                }
                if ("NO".equalsIgnoreCase(line) || "exit".equalsIgnoreCase(line)) {
                    System.exit(0);
                }
                inputContent.append(line).append("\n");
            }

            // 匹配LeetCode URL
            urls.addAll(matchLeetCodeUrls(inputContent.toString()));
        }
        int sleepTime = urls.size() > 10 ? 10 : 5;
        for (String lcUrl : urls) {
            ExceptionUtils.sleep(sleepTime);
            createByTitleSlug(lcUrl);
            // System.out.println("run url : " + lcUrl);
        }
        // start(aClass, true);
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
        ParseCodeInfo parseCodeInfo = lcTemplate.parseCodeTemplate(code);
        Objects.requireNonNull(parseCodeInfo,"parseCodeInfo is null");
        this.parseCodeInfo = parseCodeInfo;
        String method = parseCodeInfo.getMethod();
        String methodName = parseCodeInfo.getMethodName();

        String url = BuildUrl.LC_PROBLEM_PREFIX + "/" + titleSlug;

        //
        String questionTranslationInfo = BuildUrl.questionTranslations(titleSlug);

        boolean isNeedMod = StringUtils.isNeedMOD(questionTranslationInfo);

        String s = StringUtils.jsonStrGetValueByKey(questionTranslationInfo, "translatedContent");
        this.testCase = TestCaseUtil.testCaseToString(lcTestCase.parseDefault(s));


        classTemplate.buildIsNeedMod(isNeedMod)
                .buildTitle(titleSlug)
                .buildUrl(url)
                .buildMethod(method)
                .buildCodeInfo(parseCodeInfo)
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
        // start(aClass,true);
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


    public void updateClassName(ProblemInfo problemInfo) {
    }


    /**
     * 匹配所有的链接
     *
     * @param s 输入内容
     * @return 返回一个 urls
     */

    public static List<String> matchLeetCodeUrls(String s) {
        if (StringUtils.isEmpty(s)) {
            return Collections.emptyList();
        }
        List<String> urls = StringUtils.matchUrls(s);
        return urls.stream().filter(url -> !StringUtils.isEmpty(s) && url.startsWith(BuildUrl.LC_PROBLEM_PREFIX)).collect(Collectors.toList());
    }

}
