package leetcode.contest;

import code_generation.crwal.leetcode.WeekContest;

import java.util.Calendar;

/**
 * @author: wuxin0011
 * @Description:用于生成目录的类
 */
public class WeekContestTest {


    /**
     * 周赛
     */
    public static final WeekContest WEEK_CONTEST = new WeekContest(380, "2024-01-14 10:30:00", 4, WeekContest.WEEKLY_PREFIX, "weekly", 1, WeekContestTest.class);


    /**
     * 双周赛
     */
    public static final WeekContest BI_WEEK_CONTEST = new WeekContest(1, "2019-06-01 22:30:00", 4, WeekContest.BI_WEEKLY_PREFIX, "biweekly", 2, WeekContestTest.class);


    private final static int[] DAYS = {0, 7, 1, 2, 3, 4, 5, 6};

    public static void main(String[] args) {
        System.out.println("WEEK_CONTEST Next is: " + WEEK_CONTEST.getId());
        System.out.println("BI_WEEK_CONTEST Next is :" + BI_WEEK_CONTEST.getId());
    }

    @SuppressWarnings("all")
    public static void autoCreateNext() {
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        if (hour >= 10 && hour <= 12 && DAYS[today] == 7) {
            if (minute >= 30) {
                System.out.println("weekly contest start !");
                WeekContestTest.WEEK_CONTEST.next();
            } else {
                continueRun(minute);
            }
        } else if (hour >= 22 && DAYS[today] == 6) {
            if (minute >= 30) {
                System.out.println("biweekly contest start !");
                WeekContestTest.BI_WEEK_CONTEST.next();
            } else {
                continueRun(minute);
            }
        } else {
            System.out.println("Not contest will start !");
        }
    }

    public static void continueRun(int minute) {
        int rest = 30 - minute;
        System.out.println("contest rest " + (rest) + " min will start ~");
        try {
            // sleep 1 min
            Thread.sleep(1000L * 60);
        } catch (InterruptedException ignored) {

        }
        autoCreateNext();
    }

}
