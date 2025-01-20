package code_generation.crwal.leetcode;

import code_generation.contest.*;
import code_generation.crwal.TestCaseUtil;
import code_generation.utils.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author: wuxin0011
 * @Description:
 */
public class LCContest implements Contest {

    private static final LocalDate NOW = LocalDate.now();


    public static final String WEEK_DRI = "weekly";
    public static final String BI_WEEK_DRI = "biweekly";


    public static final String WEEKLY_PREFIX = "w_";
    public static final String BI_WEEKLY_PREFIX = "bi_";


    /**
     * 周赛
     */
    public static final LCContest WEEK_CONTEST = new LCContest(380, "2024-01-14 10:30:00", 4, WEEKLY_PREFIX, WEEK_DRI, 1, null);


    /**
     * 双周赛
     */
    public static final LCContest BI_WEEK_CONTEST = new LCContest(1, "2019-06-01 22:30:00", 4, BI_WEEKLY_PREFIX, BI_WEEK_DRI, 2, null);


    private final static int[] DAYS = {0, 7, 1, 2, 3, 4, 5, 6};


    private String originDir;

    private int lastestDateNo; // 最近一次比赛编号
    private LocalDate lastestDate; // 最近一次比赛时间
    private String dir; // 生成的目录
    private int times; // 比赛频率
    private int problems; // 多少个题目 默认4个
    private String dirPrefix; // 前缀

    private Class<?> aClass; // 加载到那个类目录下


    public LCContest(int no, String lastestDate, int problems, String dirPrefix, String dir, int times, Class<?> c) {
        this.lastestDateNo = no;
        this.originDir = dir;
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
        this.aClass = c == null ? LCContest.class : c;
        this.dir = IoUtil.wrapperAbsolutePath(aClass, StringUtils.isEmpty(_dir) ? !StringUtils.isEmpty(originDir) ? originDir : dir : _dir);
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

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    // 如果最新一次周赛获取不到 可以使用这个模板
    public void useOldTemplate(int No, int maxId, int idx, Question question) {
        if (maxId == No) {
            AtomicReference<String> tc = new AtomicReference<>("");
            ExceptionUtils.executeWithExceptionHandling(() -> {
                tc.set(question.getUrl());
            });
            ExceptionUtils.executeWithExceptionHandling(() -> {
                Problem.create(idx, dir, tc.get(), "");
            });
        } else {
            this.useDefaultTemplate(idx, question);
        }
    }


    public void useDefaultTemplate(int idx, Question question) {
        ExceptionUtils.executeWithExceptionHandling(() -> {
            createContestTemplate(idx, dir, question);
        });
    }

    private void createTestProblem(int No, String dir) {

        int maxId = getId();


        System.out.println("==========================================" + No + " start ==========================================");
        List<Question> questions = getQuestions(No);
        if (questions.size() == 0) {
            System.out.println("No contest !" + No);
            return;
        }

        // not setting username
        // form network get
        if (StringUtils.isEmpty(username)) {
            this.username = getUserName();
        }

        if (!StringUtils.isEmpty(username)) {
            System.out.println("\n welcome coming " + CustomColor.error(username) + " \n");
        }
        System.out.println("fetch problems urls success ! start parse problems ");
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            if (question == null) {
                continue;
            }
            String tempTitle = (StringUtils.isEmpty(question.getTitle()) ? question.getTitle_slug() : question.getTitle());
            System.out.println("\nstart parse question :  " + CustomColor.pink(tempTitle) + " , credit: " + (StringUtils.isEmpty(question.getCredit()) ? "unknown" : question.getCredit()));
            System.out.println("access url : " + (StringUtils.isEmpty(question.getUrl()) ? "unknown" : question.getUrl()));
            final int idx = i;

            try {
                createContestTemplate(idx, dir, question);
            } catch (Exception e) {
                e.printStackTrace();
                // if error use default template
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
            // useOldTemplate(maxId,No,idx,question);


            ExceptionUtils.sleep(Math.min(1, (int) (Math.random() * 5)));
        }
        // Problem.createProblems(problems, dir);
        System.out.println("==========================================" + No + " end ==========================================");
    }

    private static final LCTemplate lcTemplate = new LCTemplate("{'value': 'java', 'text': 'Java', 'defaultCode':");

    public void createContestTemplate(int curId, String dir, Question question) {
        if (question == null) {
            throw new NullPointerException();
        }
        ClassTemplate classTemplate = new ClassTemplate();
        String titleSlug = question.getTitle_slug();
        String info = "";

        // test case
        List<String> strings = null;
        String testCase = null;


        // important info
        // info = parseScriptCodeInfo(contestHtml);
        // if (StringUtils.isEmpty(info)) {
        //     throw new RuntimeException("parse error");
        // }
        // 旧版 这个其实也可以 但是没有返回值
        // String method = getMethod(info);
        // String methodName = StringUtils.getMethodName(method);

        // 新方式处理 有返回值
        ParseCodeInfo parseCodeInfo = null;
        //使用新的接口 https://leetcode.cn/contest/weekly-contest-398/
        parseCodeInfo = this.parseCodeTemplate(question);
        if (parseCodeInfo != null) {
            List<String> parseResult = LCTestCase._2025NewHandlerInputAndOutput(parseCodeInfo.getOrigin());
            if (!parseResult.isEmpty()) {
                StringBuilder sb = new StringBuilder();

                for(String s : parseResult) {
                    sb.append(StringUtils.isEmpty(s) ? "" : s);
                }

                testCase = sb.toString();
            } else {
                testCase = TestCaseUtil.testCaseToString(TEST_CASE.parseContest(StringUtils.jsonStrGetValueByKey(parseCodeInfo.getOrigin(), "translatedContent")));
            }
        } else {
            String contestHtml = BuildUrl.getContestQuestionPage(question.getUrl());
            testCase = TestCaseUtil.testCaseToString(TEST_CASE.parseContest(contestHtml));
            parseCodeInfo = this.parseCodeTemplate(contestHtml);
        }

        if (parseCodeInfo == null) {
            throw new RuntimeException("place check your cookie ! maybe already expire " +  BuildUrl.request.getConfigPath());
        }


        String method = parseCodeInfo.getMethod();
        String methodName = parseCodeInfo.getMethodName();
        String title = question.title;
        if (StringUtils.isEmpty(title)) {
            title = getTitle(info);
        }
        if (StringUtils.isEmpty(title)) {
            title = titleSlug;
        }
        question.buildTitle(title);

        // title
        classTemplate.buildTitle(title).buildCodeInfo(parseCodeInfo);


        String className = Problem.createDir(curId, true);

//        如果使用旧版本dir方式
//        String prefix = Problem.createDir(curId, true, Problem.createDir(curId, false, dir));
//        String javaFile = prefix + ".java";
//        String txtFile = prefix + ".txt";

        String javaFile = dir + className + ".java";
//        String txtFile = dir  + "\\__test_case__\\"+ className + ".txt";
        String txtFile = dir  + className + ".txt";

        String packageInfo = ReflectUtils.getPackageInfo(javaFile);


        classTemplate.buildIsNeedMod(StringUtils.isNeedMOD(info))
                .buildUrl(question.getUrl())
                .buildMethod(method)
                .buildAuthor(this.username)
                .buildMethodName(methodName)
                .buildPackageInfo(packageInfo)
                // .buildClassName(className)
                // 如果使用旧版本dir方式
                .buildTextFileName(className)
//                .buildTextFileName("__test_case__\\" + className)
                .buildTitle(question.getTitle());


        Problem.create(new ProblemInfo(javaFile, txtFile, "", testCase, classTemplate, aClass));
    }

    private static String getTestCase(String s) {
        String contestHtml = BuildUrl.getContestQuestionPage(s);
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


    public void createNo(Class<?> aClass) {
        this.setClass(aClass, null);
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
     * @param NO 周赛序号
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


    public ParseCodeInfo parseCodeTemplate(Question question) {
        String info = BuildUrl.queryNewContestQuestion(question.getUrl());
        if (StringUtils.kmpSearch(info, "authenticated") != -1) {
            throw new RuntimeException("authenticated access ,place check your cookie in dir " + BuildUrl.request.getConfigPath());
        }
        String p = "\"lang\":\"Java\",\"langSlug\":\"java\"";
        return lcTemplate.parseCodeTemplate(info, p, true);
    }


    @Override
    public ParseCodeInfo parseCodeTemplate(String contestHtml) {
        // important info
        String info = parseScriptCodeInfo(contestHtml);
        if (StringUtils.isEmpty(info)) {
            return null;
        }
        // 新方式处理 有返回值
        return lcTemplate.parseCodeTemplate(info);
    }


    private String testCase;
    private String code;

    @Override
    public void generatorTemplate() {

    }





    private List<Question> questionList;


    public static boolean isWeekContest(String prefix) {
        return prefix != null && prefix.equals(WEEKLY_PREFIX);
    }

    public static boolean isBiWeekContest(String prefix) {
        return prefix != null && prefix.equals(BI_WEEKLY_PREFIX);
    }


    @SuppressWarnings("all")
    public static String parseScriptCodeInfo(String input) {
        int i = StringUtils.kmpSearch(input, "var pageData");
        if (i == -1) {
            return "";
        }
        input = input.substring(i);
        i = StringUtils.kmpSearch(input, "</script>");
        if (i == -1) {
            return "";
        }
        input = input.substring(0, i);
        return input;
    }

    @SuppressWarnings("all")
    public static String getTitle(String input) {
        final String key = "questionTitle:";
        int i = StringUtils.kmpSearch(input, key);
        if (i == -1) {
            return "";
        }
        int i2 = StringUtils.kmpSearch(input, i, ",");
        if (i2 == -1) {
            return "";
        }
        String title = input.substring(i + key.length(), i2);
        title = title.replace(",", "");
        title = title.replace("\'", "");
        title = title.replace("\"", "");
        return title;
    }

    @SuppressWarnings("all")
    public static String getMethod(String input) {
        final String t = "{'value': 'java', 'text': 'Java', 'defaultCode':";
        return StringUtils.getMethod(input,t);
    }

    public static String getUserName() {
        final String key = "username";
        // String key = "realName";
        // String key = "userSlug";
        String userStatusInfo = BuildUrl.userStatus();
        // System.out.println(userStatus);
        return StringUtils.jsonStrGetValueByKey(userStatusInfo, key);

    }


    public static void main(String[] args) {
        System.out.println("WEEK_CONTEST Next is: " + WEEK_CONTEST.getId());
        System.out.println("BI_WEEK_CONTEST Next is :" + BI_WEEK_CONTEST.getId());
    }

    public static void autoCreateNext(Class<?> aClass) {
        Objects.requireNonNull(aClass,"class not allow null");
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        if (hour >= 10 && hour <= 12 && DAYS[today] == 7) {
            if (minute >= 30) {
                System.out.println("weekly contest start !");
                LCContest.WEEK_CONTEST.setClass(aClass, null);
                LCContest.WEEK_CONTEST.next();
            } else {
                continueRun(minute, aClass);
            }
        } else if (hour >= 22 && DAYS[today] == 6) {
            if (minute >= 30) {
                System.out.println("biweekly contest start !");
                LCContest.BI_WEEK_CONTEST.setClass(aClass, null);
                LCContest.BI_WEEK_CONTEST.next();
            } else {
                continueRun(minute, aClass);
            }
        } else {
            System.out.println("Not contest will start !");
        }
    }


    public static void continueRun(int minute, Class<?> aClass) {
        int rest = 30 - minute;
        System.out.println("contest rest " + (rest) + " min will start ~");
        try {
            // sleep 1 min
            Thread.sleep(1000L * 60);
        } catch (InterruptedException ignored) {

        }
        autoCreateNext(aClass);
    }


}
