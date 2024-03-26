package leetcode.contest;

import code_generation.crwal.leetcode.WeekContest;

/**
 * @author: wuxin0011
 * @Description:用于生成目录的类
 */
public class Test {


    /**
     * 周赛
     */
    public static final WeekContest WEEK_CONTEST = new WeekContest(380, "2024-01-14 10:30:00", 4, WeekContest.WEEKLY_PREFIX, "weekly", 1, Test.class);


    /**
     * 双周赛
     */
    public static final WeekContest BI_WEEK_CONTEST = new WeekContest(100, "2023-03-18 22:30:00", 4, WeekContest.BI_WEEKLY_PREFIX, "biweekly", 2, Test.class);


}
