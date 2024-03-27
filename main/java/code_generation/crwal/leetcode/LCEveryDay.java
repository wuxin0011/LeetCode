package code_generation.crwal.leetcode;

import code_generation.contest.ClassTemplate;
import code_generation.contest.Problem;
import code_generation.contest.ProblemInfo;
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
    public void create(ProblemInfo problemInfo) {
        Problem.create(problemInfo);
    }
}
