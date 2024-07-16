package code_generation.contest;

import code_generation.utils.IoUtil;
import code_generation.utils.ReflectUtils;
import code_generation.utils.StringUtils;

import java.io.File;
import java.util.Calendar;
import java.util.Scanner;

/**
 * @author: wuxin0011
 * @Description: create acm mode template
 */
public class CFTemplate {

    Class<?> aClass;
    String className = "Main";
    String test_className = "Main_Test";
    String url = "";
    String title = "";
    String dir = "";

    boolean createInput = true;
    boolean createOutput = true;
    boolean createReadme = false;

    boolean useTimeDir = true;
    boolean createTestClass = true;

    boolean createSuccess = true;

    private String path;

    public CFTemplate(Class<?> aClass) {
        this.aClass = aClass;
    }

    public CFTemplate buildClassName(String className) {
        if (StringUtils.isEmpty(className)) {
            throw new RuntimeException("className is null");
        }
        this.className = className;
        return this;
    }

    public CFTemplate buildTestClassName(String test_className, boolean createTestClass) {
        this.createTestClass = createTestClass;
        if (!createTestClass) {
            return this;
        }
        if (StringUtils.isEmpty(test_className)) {
            throw new RuntimeException("test_className is null");
        }
        this.test_className = test_className;
        return this;
    }

    public CFTemplate buildCreateReadme(boolean createReadme) {
        this.createReadme = createReadme;
        return this;
    }

    public CFTemplate buildDir(int dir) {
        this.dir = "_" + dir;
        return this;
    }

    public CFTemplate buildDir(String dir) {
        this.dir = dir;
        return this;
    }

    public CFTemplate buildUrl(String url) {
        this.url = url;
        return this;
    }

    public CFTemplate buildTitle(String title) {
        this.title = title;
        return this;
    }

    public CFTemplate buildUseTimeDir(boolean useTimeDir) {
        this.useTimeDir = useTimeDir;
        return this;
    }


    public String getPath() {
        if (!StringUtils.isEmpty(path)) {
            return path;
        }
        String pre = IoUtil.buildAbsolutePath(aClass);
        StringBuilder sb = new StringBuilder();
        sb.append(pre);
        if (useTimeDir) {
            Calendar instance = Calendar.getInstance();
            int year = instance.get(Calendar.YEAR);
            int month = instance.get(Calendar.MONTH) + 1;
            int day = instance.get(Calendar.DAY_OF_MONTH);

            int[] a = {year, month, day};
            for (int j : a) {
                sb.append("_");
                if (j < 10) {
                    sb.append(0);
                }
                sb.append(j);
                sb.append(File.separator);
            }
        }

        if (!StringUtils.isEmpty(dir)) {
            sb.append(dir);
            sb.append(File.separator);
        }
        path = sb.toString();
        return path;
    }


    public String getJavaFile() {
        return this.getPath() + this.className + ".java";
    }

    public String getTestJava() {
        return this.getPath() + this.test_className + ".java";
    }

    public String getPackageInfo() {
        return ReflectUtils.getPackageInfo(getJavaFile());
    }


    final static String MAIN_PATTERN = "package %s;\n" +
            "\n" +
            "import code_generation.utils.ACM.IO;\n" +
            "import java.util.*;\n" +
            "import java.io.*;\n\n" +
            "/**\n" +
            " * @author: wuxin0011\n" +
            " * @Description:\n" +
            " * @url: %s \n" +
            " * @title: %s \n" +
            " */\n" +
            "public class %s {\n" +
            "    static IO io;\n" +
            "    public static void main(String[] args) {\n" +
            "        io = new IO();\n" +
            "        int T;\n" +
            "        T = 1;\n" +
            "        // T = io.readInt();\n" +
            "        while(T>0) {\n" +
            "            solve();\n" +
            "            T--;\n" +
            "        }\n" +
            "        io.close();\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    public static void solve() {\n" +
            "        \n" +
            "    }\n\n" +
            "}\n";

    String buildMainTemplate() {
        return String.format(MAIN_PATTERN, this.getPackageInfo(), url, title, this.className);
    }


    final static String MAIN_TEST_PATTERN = "package %s;\n" +
            "\n" +
            "import code_generation.utils.ACM.ACMUtil;\n" +
            "/**\n" +
            " * @author: wuxin0011\n" +
            " * @Description:\n" +
            " */\n" +
            "public class %s {\n" +
            "    public static void main(String[] args) {\n" +
            "\t       ACMUtil.run(%s.class);\n" +
            "    }\n" +
            "\n" +
            "}\n";

    String buildMainTestTemplate() {
        return String.format(MAIN_TEST_PATTERN, this.getPackageInfo(), this.test_className, this.className);
    }


    void buildMainJava() {
        IoUtil.createFile(getJavaFile());
        IoUtil.writeContent(new File(getJavaFile()), buildMainTemplate());
        if (createTestClass) {
            IoUtil.createFile(getTestJava());
            IoUtil.writeContent(new File(getTestJava()), buildMainTestTemplate());
        }
        System.out.println("create success" + getJavaFile() + "\n");
    }

    void buildInputAndOutput() {
        if (createInput) {
            IoUtil.createFile(getPath() + "in.txt");
        }
        if (createOutput) {
            IoUtil.createFile(getPath() + "out.txt");
        }
        if (createReadme) {
            IoUtil.createFile(getPath() + "README.md");
        }
    }


    public void run() {
        buildMainJava();
        buildInputAndOutput();
    }


    public static void runContest(int cnt, int contestID, Class<?> aClass) {
        for (int i = 1; i <= cnt; i++) {
            char c = (char) (i - 1 + 'A');
            CFTemplate cfTemplate = new CFTemplate(aClass);
            cfTemplate.buildUseTimeDir(false);
            cfTemplate.buildDir("_" + contestID + File.separator + c);
            String url = String.format("https://codeforces.com/problemset/problem/%s/%s", contestID, c);
            cfTemplate.buildUrl(url);
            System.out.println("access url: " + url);
            try {
                cfTemplate.run();
            } catch (Exception ignore) {
            }
        }
    }


    public static void runContest(Class<?> aClass) {
        Scanner sc = new Scanner(System.in);
        System.out.println("place input contestId   :");
        int contestId = sc.nextInt();
        System.out.println("place input problems cnt :");
        int cnt = sc.nextInt();
        sc.close();
        runContest(cnt, contestId, aClass);
    }

}
