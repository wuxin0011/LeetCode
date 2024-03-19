package leetcode.contest;

import java.time.LocalTime;

/**
 * @author: wuxin0011
 * @Description: 生成 template 类
 */
public class Next {
    // create template
    public static void main(String[] args) {
        autoCreateNext();
    }


    public static void autoCreateNext() {
        LocalTime lc = LocalTime.now();
        int hour = lc.getHour();
        if (hour >= 9 && hour <= 12) {
            LeetCodeContest.WEEK_CONTEST.next();
        } else if (hour >= 21) {
            LeetCodeContest.BI_WEEK_CONTEST.next();
        } else {
            System.out.println("Not contest will start !");
        }
    }


}
