package leetcode.contest;

import leetcode.utils.IoUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 * @author: wuxin0011
 * @Description:
 */
public class WeekContest {


    /**
     * 周赛
     */
    public static final WeekContest WEEK_CONTEST = new WeekContest(380, "2024-01-14 10:30:00", 4, "w_", "weekly", 1, null);


    /**
     * 双周赛
     */
    public static final WeekContest BI_WEEK_CONTEST = new WeekContest(100, "2023-03-18 22:30:0", 4, "bi_", "biweekly", 2, null);
    private static final Date currentDate = new Date();


    private int lastestDateNo; // 最近一次比赛编号
    private Date lastestDate; // 最近一次比赛时间
    private String dir; // 生成的目录
    private int times; // 比赛频率
    private int problems; // 多少个题目 默认4个
    private String dirPrefix; // 前缀

    private Class<?> aClass; // 加载到那个类目录下


    public WeekContest(int no, String lastestDate, int problems, String dirPrefix, String dir, int times, Class<?> c) {
        this.lastestDateNo = no;
        this.aClass = c == null ? WeekContest.class : c;
        this.lastestDate = Objects.requireNonNull(parse(lastestDate), "date format is fail");
        this.dir = IoUtil.wrapperAbsolutePath(aClass, dir);
        this.times = Math.max(1, times);
        this.problems = problems;
        this.dirPrefix = dirPrefix.endsWith("_") ? dirPrefix : (dirPrefix + "_");
    }

    /**
     * 比较场次符合要求
     */
    public void valid() {
        // todo
    }


    public void next() {
        valid();
        createTestProblem(getNO(), getProblemDir());
    }

    private void createTestProblem(int No, String dir) {
        System.out.println("==========================================" + No + " start ==========================================");
        Problem.createProblems(problems, dir);
        System.out.println("==========================================" + No + " end ==========================================");
    }


    public String getWrapperDir() {
        int wrapperNoDir = (this.getNO() / 100) * 100;
        return String.format("%s%s%s%s", dir, File.separator, dirPrefix, wrapperNoDir);
    }

    public String getProblemDir() {
        return String.format("%s%s%s%s%s", getWrapperDir(), File.separator, dirPrefix, this.getNO(), File.separator);
    }


    public int getNO() {
        long millisecondsBetween = currentDate.getTime() - lastestDate.getTime();
        long weeksBetween = (millisecondsBetween / (7 * 24 * 60 * 60 * 1000) + times) / times;
        return (int) (this.lastestDateNo + weeksBetween);
    }


    public static Date parse(String text) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(text);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 自定义生成
     *
     * @param NO
     */
    public void createNo(int NO) {
        int wrapperNoDir = (NO / 100) * 100;
        StringBuilder sb = new StringBuilder();
        sb.append(dir);
        sb.append(File.separator);
        sb.append(dirPrefix);
        sb.append(wrapperNoDir);
        sb.append(File.separator);
        sb.append(dirPrefix);
        sb.append(NO);
        sb.append(File.separator);
        String wrapperDir = sb.toString();
        createTestProblem(NO, wrapperDir);
    }


    public static void auto() {
        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();
        if (9 <= hour && hour <= 12) {
            System.out.println("create week" + WEEK_CONTEST.getNO());
            WEEK_CONTEST.next();
        } else if (21 <= hour) {
            System.out.println("create BI_WEEK_CONTEST" + BI_WEEK_CONTEST.getNO());
            BI_WEEK_CONTEST.next();
        } else {
            System.out.println("no contest start ");
        }
    }


}
