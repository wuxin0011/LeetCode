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
        int dayofWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        if (hour >= 10 && hour <= 12 && DAYS[dayofWeek] == 7 && minute >= 30) {
            Test.WEEK_CONTEST.next();
        } else if (hour >= 22 && DAYS[dayofWeek] == 6 && minute >= 30) {
            Test.BI_WEEK_CONTEST.next();
        } else {
            System.out.println("Not contest will start !");
        }
    }


}
