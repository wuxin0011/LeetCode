package code_generation.crwal.leetcode;

import code_generation.contest.*;
import code_generation.crwal.TestCaseUtil;
import code_generation.utils.*;

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

    public String translatedTitle; // 题目title 获取到是一个 unicode格式
    public String translatedContent; // 题目描述
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
        System.out.println("total problems : " + urls.size());
        System.out.println("=======================start==================\n");
        for (int i = 0; i < urls.size(); i++) {
            String lcUrl = urls.get(i);
            System.out.printf("access url %d:  %s  place wait %d s\n", i + 1, lcUrl, sleepTime);
            ExceptionUtils.sleep(sleepTime);
            try {
                createByTitleSlug(lcUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n=======================end==================");
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

        // 获取题目代码快内容
        String code = BuildUrl.questionEditorData(titleSlug);

        // 解析题目代码快内容
        ParseCodeInfo parseCodeInfo = lcTemplate.parseCodeTemplate(code);

        Objects.requireNonNull(parseCodeInfo,"parseCodeInfo is null");


        this.parseCodeInfo = parseCodeInfo;
        String method = parseCodeInfo.getMethod();
        String methodName = parseCodeInfo.getMethodName();

        if(StringUtils.isEmpty(this.frontendQuestionId)){
            this.frontendQuestionId = StringUtils.jsonStrGetValueByKey(code, "questionFrontendId");
        }
        String url = BuildUrl.LC_PROBLEM_PREFIX + "/" + titleSlug;

        // 获取题目信息 title desc test case 等
        String questionTranslationInfo = BuildUrl.questionTranslations(titleSlug);

        // 是否需要取余
        boolean isNeedMod = StringUtils.isNeedMOD(questionTranslationInfo);

        // 题目描述
        this.translatedContent = StringUtils.jsonStrGetValueByKey(questionTranslationInfo, "translatedContent");

        // 标题 是一个unicode格式的
        this.translatedTitle = StringUtils.jsonStrGetValueByKey(questionTranslationInfo, "translatedTitle");

        // 获取测试案例
        this.testCase = TestCaseUtil.testCaseToString(lcTestCase.parseDefault(translatedContent));

        // 获取中文标题
        String tempUnicodeTitle  = StringUtils.unicodeToChinese(translatedTitle);
        titleSlug = !StringUtils.isEmpty(tempUnicodeTitle) ? tempUnicodeTitle : titleSlug;

        System.out.println("正在解析题目: " + CustomColor.error(titleSlug));;


        // 构建模板
        classTemplate.buildIsNeedMod(isNeedMod)
                .buildTitle(titleSlug)
                .buildUrl(url)
                .buildMethod(method)
                .buildAuthor(LCContest.getUserName())
                .buildCodeInfo(parseCodeInfo)
                .buildMethodName(methodName);

        // 接下来需要做上面之自定义实现 基本测试信息都已经获取到了
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
        return urls.stream().filter(
                        url -> {
                            if (StringUtils.isEmpty(url)) {
                                return false;
                            }
                            if (url.startsWith(BuildUrl.LC_PROBLEM_PREFIX)) {
                                return true;
                            }
                            if (url.startsWith(BuildUrl.LC_WEEKLY_CONTEST_PREFIX)) {
                                return true;
                            }
                            if (url.startsWith(BuildUrl.LC_CLASS_THEME_PREFIX)) {
                                return true;
                            }
                            return url.startsWith(BuildUrl.LC_BI_WEEKLY_CONTEST_PREFIX);
                        }
                ).map(u -> {
                    if (u.startsWith(BuildUrl.LC_CLASS_THEME_PREFIX)) {
                        return u.replace(BuildUrl.LC_CLASS_THEME_PREFIX, BuildUrl.LC_PROBLEM_PREFIX);
                    }
                    StringBuilder sb = new StringBuilder();
                    for(char c : u.toCharArray()){
                        if(c == '>'||c=='<')break;
                        if(0 < c && c <= Byte.MAX_VALUE){
                            sb.append(c);
                        }
                    }
                    u = sb.toString();
                    String[] sss = u.split("problems");
                    return BuildUrl.LC_PROBLEM_PREFIX + sss[sss.length - 1] ;
                }).
                collect(Collectors.toList());
    }

    public static List<String> matchLeetCodeUrlsAndContest(String s) {
        if (StringUtils.isEmpty(s)) {
            return Collections.emptyList();
        }
        List<String> urls = StringUtils.matchUrls(s);
        return urls.stream().filter(
                        url -> {
                            if (StringUtils.isEmpty(url)) {
                                return false;
                            }
                            if (url.startsWith(BuildUrl.LC_PROBLEM_PREFIX)) {
                                return true;
                            }
                            if (url.startsWith(BuildUrl.LC_WEEKLY_CONTEST_PREFIX)) {
                                return true;
                            }
                            if (url.startsWith(BuildUrl.LC_CLASS_THEME_PREFIX)) {
                                return true;
                            }
                            return url.startsWith(BuildUrl.LC_BI_WEEKLY_CONTEST_PREFIX);
                        }
                ).map(u -> {
                    if (u.startsWith(BuildUrl.LC_CLASS_THEME_PREFIX)) {
                        return u.replace(BuildUrl.LC_CLASS_THEME_PREFIX, BuildUrl.LC_PROBLEM_PREFIX);
                    }
                    StringBuilder sb = new StringBuilder();
                    for(char c : u.toCharArray()){
                        if(c == '>'||c=='<')break;
                        if(c=='\"')continue;
                        if(0 < c && c <= Byte.MAX_VALUE){
                            sb.append(c);
                        }
                    }
                    return StringUtils.ingoreString(sb.toString());
                }).
                collect(Collectors.toList());
    }

}
