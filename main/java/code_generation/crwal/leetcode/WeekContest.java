package code_generation.crwal.leetcode;

import code_generation.contest.ClassTemplate;
import code_generation.contest.Contest;
import code_generation.contest.Problem;
import code_generation.contest.ProblemInfo;
import code_generation.crwal.TestCaseUtil;
import code_generation.utils.ExceptionUtils;
import code_generation.utils.IoUtil;
import code_generation.utils.ReflectUtils;
import code_generation.utils.StringUtils;

import java.io.File;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author: wuxin0011
 * @Description:
 */
public class WeekContest implements Contest {

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
        createTestProblem(getId(), getProblemDir());
    }

    private void createTestProblem(int No, String dir) {

        int maxId = getId();

        System.out.println("==========================================" + No + " start ==========================================");
        List<Question> questions = getQuestions(No);
        if (questions.size() == 0) {
            System.out.println("No contest !" + No);
            return;
        }
        System.out.println("fetch problems urls success ! start parse problems ");
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            if (question == null) {
                continue;
            }
            System.out.println("start parse question :  " + question.getTitle());
            final int idx = i;

            try {
                createContestTemplate(idx, dir, question);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("try use default contest template ");
                AtomicReference<String> tc = new AtomicReference<>("");
                ExceptionUtils.executeWithExceptionHandling(() -> {
                    tc.set(getTestCase(questions.get(idx).getUrl()));
                });
                ExceptionUtils.executeWithExceptionHandling(() -> {
                    Problem.create(idx, dir, tc.get(), "");
                });
            }

            // 是否取用最新版不使用template


//            if (maxId == No) {
//
//                AtomicReference<String> tc = new AtomicReference<>("");
//                ExceptionUtils.executeWithExceptionHandling(() -> {
//                    tc.set(getTestCase(questions.get(idx).getUrl()));
//                });
//                ExceptionUtils.executeWithExceptionHandling(() -> {
//                    Problem.create(idx, dir, tc.get(), "");
//                });
//            } else {
//                ExceptionUtils.executeWithExceptionHandling(() -> {
//                    createContestTemplate(idx, dir, question);
//                });
//            }


            ExceptionUtils.sleep(Math.min(1, (int) (Math.random() * 5)));
        }
        // Problem.createProblems(problems, dir);
        System.out.println("==========================================" + No + " end ==========================================");
    }

    private final LCTestCase lcTestCase = new LCTestCase();
    private final LCTemplate lcTemplate = new LCTemplate();

    public void createContestTemplate(int curId, String dir, Question question) {
        if (question == null) {
            return;
        }
        ClassTemplate classTemplate = new ClassTemplate();
        String titleSlug = question.getTitle_slug();
        // System.out.println("titleSlug : " + titleSlug);


        String questionTranslationInfo = BuildUrl.questionTranslations(titleSlug);


        // 获取测试案例
        String testCase = getTestCase(question.getUrl());
        String method = "";
        String methodName = "";

        try {
            String code = BuildUrl.questionEditorData(titleSlug);
            String s = lcTemplate.parseCodeTemplate(code);
            method = StringUtils.getMethod(s);
            methodName = StringUtils.getMethodName(method);
        } catch (ParseException ignore) {

        }

        String className = Problem.createDir(curId, true);
        String prefix = Problem.createDir(curId, true, Problem.createDir(curId, false, dir));
        String javaFile = prefix + ".java";
        String txtFile = prefix + ".txt";

        String packageInfo = ReflectUtils.getPackageInfo(javaFile);
        System.out.println("package info :" + packageInfo);


        classTemplate.buildIsNeedMod(StringUtils.isNeedMOD(questionTranslationInfo))
                .buildUrl(question.getUrl())
                .buildMethod(method)
                .buildMethodName(methodName)
                .buildPackageInfo(packageInfo)
                // .buildClassName(className)
                .buildTextFileName(className)
                .buildTitle(question.getTitle());

        Problem.create(new ProblemInfo(javaFile, txtFile, "", testCase, classTemplate, aClass));
    }

    private static String getTestCase(String s) {
        String contestHtml = BuildUrl.getContestQuestionPage(s);
        // System.out.println("html\n\n\n" + contestHtml);
        List<String> strings = TEST_CASE.parseContest(contestHtml);
        return TestCaseUtil.testCaseToString(strings);
    }


    public static String wrapper(int dirNo) {
        dirNo = dirNo / 100 * 100;
        return dirNo == 0 ? "000" : String.valueOf(dirNo);
    }


    public String getWrapperDir() {
        return String.format("%s%s%s%s", dir, File.separator, dirPrefix, wrapper(this.getId()));
    }

    public String getProblemDir() {
        return String.format("%s%s%s%s%s", getWrapperDir(), File.separator, dirPrefix, this.getId(), File.separator);
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


    public void createNo() {
        int NO = Integer.MAX_VALUE;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("place input a valid contest number , input NO break : ");
            try {
                String next = sc.next();
                if ("NO".equalsIgnoreCase(next)) {
                    break;
                }
                NO = Integer.parseInt(next);
                if (NO <= 0) {
                    continue;
                }
                break;
            } catch (Exception ignored) {
                sc.next();
            }
        }
        createNo(NO);
    }


    /**
     * 自定义生成
     *
     * @param NO
     */
    public void createNo(int NO) {
        if (NO == Integer.MAX_VALUE) {
            return;
        }
        int maxId = getId();
        if (NO > maxId || NO < 0) {
            throw new RuntimeException("max NO = " + maxId + ",you input No = " + NO);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(dir);
        sb.append(File.separator);
        sb.append(dirPrefix);
        sb.append(wrapper(NO));
        sb.append(File.separator);
        sb.append(dirPrefix);
        sb.append(NO);
        sb.append(File.separator);
        String wrapperDir = sb.toString();
        createTestProblem(NO, wrapperDir);
    }


    @Override
    public int getId() {
        int weeksBetween = (int) (ChronoUnit.WEEKS.between(lastestDate, NOW) / times);
        return (int) (this.lastestDateNo + weeksBetween);
    }

    @Override
    public List<String> getUrls(int id) {
        List<Question> questions = getQuestions(id);
        this.questionList = questions;
        return questions.stream().map(Question::getUrl).collect(Collectors.toList());
    }

    public List<Question> getQuestions(int id) {
        String JSONStr = "";
        String apiUrl = "";
        if (isWeekContest(this.dirPrefix)) {
            JSONStr = BuildUrl.getWeeklyContestUrls(String.valueOf(id));
            apiUrl = String.format(BuildUrl.QuestionWeeklyUrlsPattern, id);
        } else if (isBiWeekContest(this.dirPrefix)) {
            JSONStr = BuildUrl.getBiWeeklyContestUrls(String.valueOf(id));
            apiUrl = String.format(BuildUrl.QuestionBiWeeklyUrlsPattern, id);
        } else {
            throw new RuntimeException("NO support !" + this.dirPrefix + " contest ~~");
        }
        return Question.getContestQuestion(JSONStr, apiUrl);
    }


    static final LCTestCase TEST_CASE = new LCTestCase();

    @Override
    public String parseTestCase(String input) {
        this.testCase = TestCaseUtil.testCaseToString(TEST_CASE.parseContest(input));
        return this.testCase;
    }

    @Override
    public String parseCodeTemplate(String input) {
        this.code = "";
        return "";
    }


    private String testCase;
    private String code;

    @Override
    public void generatorTemplate() {

    }


    public static final String WEEKLY_PREFIX = "w_";
    public static final String BI_WEEKLY_PREFIX = "bi_";


    private List<Question> questionList;


    public static boolean isWeekContest(String prefix) {
        return prefix != null && prefix.equals(WEEKLY_PREFIX);
    }

    public static boolean isBiWeekContest(String prefix) {
        return prefix != null && prefix.equals(BI_WEEKLY_PREFIX);
    }

}
