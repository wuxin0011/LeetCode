package leetcode.contest;

import java.util.Calendar;

/**
 * @author: wuxin0011
 * @Description: 生成 template 类
 */
public class Next {
    final static int[] DAYS = {0, 7, 1, 2, 3, 4, 5, 6};

    // create template
    public static void main(String[] args) {
        autoCreateNext();
    }


    public static void autoCreateNext() {
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        if (hour >= 10 && hour <= 12 && DAYS[today] == 7) {
            if (minute >= 30) {
                System.out.println("weekly contest start !");
                Test.WEEK_CONTEST.next();
            } else {
                continueRun(minute);
            }
        } else if (hour >= 22 && DAYS[today] == 6) {
            if (minute >= 30) {
                System.out.println("biweekly contest start !");
                Test.BI_WEEK_CONTEST.next();
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
