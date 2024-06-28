package code_generation.crwal.leetcode;

import code_generation.contest.ClassTemplate;
import code_generation.contest.Constant;
import code_generation.contest.ProblemInfo;
import code_generation.utils.ProblemEveryDayUtils;
import code_generation.utils.StringUtils;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author: wuxin0011
 * @Description: LeetCode 每日一题
 */
public class LCEveryDay extends LCCustom {


    public static LCEveryDay everyDay  = new LCEveryDay();


    @Override
    public void start(Class<?> c, boolean input) {
        Objects.requireNonNull(c,"class not allow NULL");
        this.aClass = c;
        this.classTemplate = new ClassTemplate();
        String questionOfToday = BuildUrl.questionOfToday();
        this.frontendQuestionId = StringUtils.jsonStrGetValueByKey(questionOfToday, "frontendQuestionId");
        String titleSlug = StringUtils.jsonStrGetValueByKey(questionOfToday, "titleSlug");
        System.out.printf("access today problem url : %s/%s\n", BuildUrl.LC_PROBLEM_PREFIX, titleSlug);
        createByTitleSlug(titleSlug);
    }


    public void custom(Class<?> c) {
        Objects.requireNonNull(c,"class not allow NULL");
        this.aClass = c;
        this.classTemplate = new ClassTemplate();
        Scanner scanner = new Scanner(System.in);

        String titleSlug = "";
        do {
            System.out.print("input a url https://leetcode.cn/problems/xxxxxx/ text and input NO exit: \n");
            titleSlug = scanner.next();
            if ("exit".equalsIgnoreCase(titleSlug) || "NO".equalsIgnoreCase(titleSlug)) {
                System.exit(-1);
            }
            // 经典模式URl
            if (!StringUtils.isEmpty(titleSlug) && titleSlug.startsWith(BuildUrl.LC_CLASS_THEME_PREFIX)) {
                titleSlug = titleSlug.replace(BuildUrl.LC_CLASS_THEME_PREFIX, BuildUrl.LC_PROBLEM_PREFIX);
            }
        } while (StringUtils.isEmpty(titleSlug) || !titleSlug.startsWith(BuildUrl.LC_PROBLEM_PREFIX));
        createByTitleSlug(titleSlug);
    }


    @Override
    public void next() {
        int count = Math.max(ProblemEveryDayUtils.getJavaFileCount(aClass), 0);
        String dir = ProblemEveryDayUtils.createPrefix(count);
        String base = ProblemEveryDayUtils.convertDir(count);
        String name = dir + Constant.FIlE_PREFIX + base;
        name = name + "_" + this.frontendQuestionId;
        // String className = name.replace(dir, "").replace("\\", "");
        // classTemplate.buildClassName(className);

        String javaPath = ProblemEveryDayUtils.buildJavaFilePath("", name);
        String txtPath = ProblemEveryDayUtils.buildTxtFilePath(dir, name);
        // System.out.println("name:" + name + ",txt path = " + txtPath);


        // build txt
        classTemplate.buildTextFileName(txtPath.replace(dir, ""));


        // System.out.println(ClassTemplate.getTemplate(classTemplate));
        ProblemInfo problemInfo = new ProblemInfo(javaPath, txtPath, testCase, classTemplate, aClass);

        // System.out.println(problemInfo);
        createTemplate(problemInfo);
    }



}
