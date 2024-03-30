package code_generation.crwal.leetcode;

import code_generation.contest.ClassTemplate;
import code_generation.contest.Constant;
import code_generation.contest.ProblemInfo;
import code_generation.utils.ProblemEveryDayUtils;
import code_generation.utils.StringUtils;

/**
 * @author: wuxin0011
 * @Description: LeetCode 每日一题
 */
public class LCEveryDay extends LCCustom {


    @Override
    public void start(Class<?> c, boolean input) {
        this.aClass = c;
        this.classTemplate = new ClassTemplate();
        String questionOfToday = BuildUrl.questionOfToday();
        this.frontendQuestionId = StringUtils.jsonStrGetValueByKey(questionOfToday, "frontendQuestionId");
        String titleSlug = StringUtils.jsonStrGetValueByKey(questionOfToday, "titleSlug");
        createByTitleSlug(titleSlug);
        // super.start(c, input);
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
