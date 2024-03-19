package leetcode.contest;

import code_generation.Contest;
import code_generation.WeekContest;

import java.time.LocalTime;

/**
 * @author: wuxin0011
 * @Description:用于生成目录的类
 */
public class LeetCodeContest extends WeekContest {

    /**
     * 周赛
     */
    public static final LeetCodeContest WEEK_CONTEST = new LeetCodeContest(380, "2024-01-14 10:30:00", 4, "w_", "weekly", 1, LeetCodeContest.class);


    /**
     * 双周赛
     */
    public static final LeetCodeContest BI_WEEK_CONTEST = new LeetCodeContest(100, "2023-03-18 22:30:00", 4, "bi_", "biweekly", 2, LeetCodeContest.class);

    public LeetCodeContest(int no, String lastestDate, int problems, String dirPrefix, String dir, int times, Class<?> c) {
        super(no, lastestDate, problems, dirPrefix, dir, times, c);
    }

    @Override
    public void auto() {
        super.auto();
    }
}
