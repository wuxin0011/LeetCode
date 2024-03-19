package code_generation;

import code_generation.utils.IoUtil;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

/**
 * @author: wuxin0011
 * @Description:
 */
public class WeekContest implements Contest {


    private static final Date currentDate = new Date();
    private static final LocalDate NOW = LocalDate.now();

    private int lastestDateNo; // 最近一次比赛编号
    private LocalDate lastestDate; // 最近一次比赛时间
    private String dir; // 生成的目录
    private int times; // 比赛频率
    private int problems; // 多少个题目 默认4个
    private String dirPrefix; // 前缀

    private Class<?> aClass; // 加载到那个类目录下


    public WeekContest(int no, String lastestDate, int problems, String dirPrefix, String dir, int times, Class<?> c) {
        this.lastestDateNo = no;
        this.dir = dir;
        this.lastestDate = Objects.requireNonNull(parse(lastestDate), "date format is fail");
        this.setClass(c, dir);
        this.times = Math.max(1, times);
        this.problems = problems;
        this.dirPrefix = dirPrefix.endsWith("_") ? dirPrefix : (dirPrefix + "_");
    }


    /**
     * 可以自动调整类目录
     *
     * @param c
     * @param _dir
     */
    public void setClass(Class<?> c, String _dir) {
        this.aClass = c == null ? WeekContest.class : c;
        this.dir = IoUtil.wrapperAbsolutePath(aClass, _dir);
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
        int weeksBetween = (int) (ChronoUnit.WEEKS.between(lastestDate, NOW) / times);
        return (int) (this.lastestDateNo + weeksBetween);
    }


    public LocalDate parse(String text) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(text, formatter);
            return localDateTime.toLocalDate();
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



}
