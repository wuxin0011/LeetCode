package code_generation.contest;

import code_generation.crwal.leetcode.WeekContest;
import code_generation.utils.IoUtil;
import code_generation.utils.StringUtils;

import java.io.File;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author: wuxin0011
 * @Description: 生成模板
 */
public class Problem {


    private static final String ROOT_DIR = IoUtil.getProjectRootDir();


    public static void createTemplate(String classFilePath, String testCase, String code) {
        createTemplate(new File(classFilePath), null, testCase, code);
    }

    public static void createTemplate(String classFilePath) {
        createTemplate(new File(classFilePath));
    }

    public static void createTemplate(File classFilePath) {
        createTemplate(classFilePath, null);
    }


    public static String handlerTxt(String txtFile, String name, File javaFilePath) {
        return handlerTxt(txtFile, name, javaFilePath, "");
    }

    public static String handlerTxt(String txtFile, String name, File javaFilePath, String testCase) {
        if (txtFile == null) {
            txtFile = name;
        }
        if (txtFile.endsWith(".txt")) {
            txtFile = txtFile.replace(".txt", "");
        }
        String temp = IoUtil.isAbsolutePath(txtFile) ? (txtFile + ".txt") : javaFilePath.getParent() + File.separator + txtFile + ".txt";
        File file = IoUtil.createFile(temp);
        if (file == null) {
            return txtFile;
        }
        if (testCase != null && testCase.length() > 0) {
            IoUtil.writeContent(file, StringUtils.replaceIgnoreContent(testCase));
        }
        if (javaFilePath != null && file.getAbsolutePath().contains(javaFilePath.getParent())) {
            txtFile = "." + file.getAbsolutePath().replace(javaFilePath.getParent(), "").replace("\\", "\\\\");
            txtFile = txtFile.replace(".\\\\", "");
        }
        if (txtFile.endsWith(".txt")) {
            txtFile = txtFile.replace(".txt", "");
        }
        return txtFile;
    }

    public static void createTemplate(File classFilePath, String txtFile) {
        try {
            String packageInfo = getPackageInfo(classFilePath.getAbsolutePath());
            String name = classFilePath.getName().replace(".java", "");
            txtFile = handlerTxt(txtFile, name, classFilePath);
            String info = String.format(pattern, packageInfo, name, name, txtFile);
            IoUtil.writeContent(classFilePath, info);
            System.out.println(classFilePath + "  create success !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void createTemplate(File classFilePath, String txtFile, String testCase, String code) {
        try {
            String packageInfo = getPackageInfo(classFilePath.getAbsolutePath());
            String name = classFilePath.getName().replace(".java", "");
            txtFile = handlerTxt(txtFile, name, classFilePath, testCase);
            String info = String.format(pattern, packageInfo, name, name, txtFile);
            IoUtil.writeContent(classFilePath, info);
            System.out.println(classFilePath + "  create success !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    final static String pattern = "package %s;\n" +
            "\n" +
            "import code_generation.utils.IoUtil;\n" +
            "import java.util.*;\n" +
            "/**\n" +
            " * @author: wuxin0011\n" +
            " * @Description:\n" +
            " * @url\n" +
            " * @title\n" +
            " */\n" +
            "public class %s {\n" +
            "    public static void main(String[] args) {\n" +
            "        IoUtil.testUtil(%s.class,IoUtil.DEFAULT_METHOD_NAME,\"%s.txt\");\n" +
            "    }\n" +
            "}\n";

    private static String createDir(int id, boolean isUpper) {
        if (id < 0 || id >= 26) {
            throw new RuntimeException("place input 0-25 number");
        }
        char c = isUpper ? (char) (id + 'A') : (char) (id + 'a');
        return String.valueOf(c);
    }

    private static String createDir(int id, boolean isUpper, String dir) {
        if (id < 0 || id >= 26) {
            throw new RuntimeException("place input 0-25 number");
        }
        char c = isUpper ? (char) (id + 'A') : (char) (id + 'a');
        return dir + File.separatorChar + c;
    }

    public static void createProblems(int problems, String dir) {
        File file = new File(dir);
        if (file.exists() && !file.isDirectory()) {
            throw new RuntimeException("file is exists and not is Directory ");
        }
        if (!file.exists()) file.mkdirs();
        for (int i = 0; i < problems; i++) {
            try {
                create(i, file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public static void createLcContestTemplate(int id, String dir, String textCase, String code) {
        File file = new File(dir);
        if (file.exists() && !file.isDirectory()) {
            throw new RuntimeException("file is exists and not is Directory ");
        }
        try {
            if (!file.exists()) file.mkdirs();
        } catch (Exception e) {
            // ignore
        }

    }


    final static String LCContestTemplate = "package %s;\n" +
            "\n" +
            "import code_generation.utils.IoUtil;\n" +
            "import java.util.*;\n" +
            "/**\n" +
            " * @author: wuxin0011\n" +
            " * @Description:\n" +
            " * @url:   %s\n" +
            " * @title: %s\n" +
            " */\n" +
            "public class %s {\n" +
            "    public static void main(String[] args) {\n" +
            "        IoUtil.testUtil(%s.class,IoUtil.DEFAULT_METHOD_NAME,\"%s.txt\");\n" +
            "    }\n" +
            "    " +
            "    %s" +
            "     \n" +
            "}\n";

    private static String createLcContestTemplate(String packageInfo, String url, String title, String className, String textName, String method) {
        return String.format(LCContestTemplate, packageInfo, url, title, className, className, textName, method);
    }


    private static File createFile(String fileName) {

        try {
            File file = new File(fileName);
            Objects.requireNonNull(file, "file is null");
            String parent = file.getParent();
            Objects.requireNonNull(file, "file dir is null");
            File parentFile = new File(parent);
            if (!parentFile.exists()) parentFile.mkdirs();
            if (!file.exists()) {
                file.createNewFile();
                return file;
            }
            System.out.println(fileName + " is exists create fail");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void create(String javaFilePath, String txtFilePath, Class<?> c) {
        javaFilePath = IoUtil.wrapperAbsolutePath(c, javaFilePath);
        txtFilePath = IoUtil.wrapperAbsolutePath(c, txtFilePath);
        create(javaFilePath, txtFilePath);
    }


    public static void create(String javaFilePath, String txtFilePath) {
        File classFile = createFile(javaFilePath);
        if (classFile != null) {
            createTemplate(classFile, txtFilePath);
        }
    }


    public static void create(int id, String path) {
        // create dir
        File dirFile = new File(createDir(id, false, path));
        if (!dirFile.exists()) dirFile.mkdir();
        String prefix = createDir(id, true, dirFile.getAbsolutePath());
        File classFile = createFile(prefix + ".java");
        if (classFile != null) {
            createTemplate(classFile);
        }
        // createFile(prefix + ".txt");
    }

    public static void create(int id, String path, String testCase, String code) {
        // create dir
        File dirFile = new File(createDir(id, false, path));
        if (!dirFile.exists()) dirFile.mkdir();
        String prefix = createDir(id, true, dirFile.getAbsolutePath());
        File classFile = createFile(prefix + ".java");
        if (classFile != null) {
            createTemplate(classFile.getAbsolutePath(), testCase, code);
        }
    }

    public static String getPackageInfo(String classFile) {
        File file = new File(classFile);
        String dir = "";
        if (file.isFile()) {
            dir = file.getParent();
        }
        if (!dir.contains(IoUtil.getWorkDir())) {
            throw new RuntimeException("place check project running on " + IoUtil.getWorkDir());
        }
        int id = dir.indexOf(ROOT_DIR);
        if (id == -1) {
            throw new RuntimeException("place check " + dir + " is local work dir ");
        }
        String packageDir = dir.substring(id + ROOT_DIR.length());
        char[] charArray = packageDir.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c != '\\' && c != '/') {
                continue;
            }
            if (i == charArray.length - 1) {
                charArray[i] = ' ';
                break;
            } else {
                c = '.';
            }
            charArray[i] = c;
        }
        return new String(charArray);
    }


    public static void customContest(int problems, String dirPrefix, String dirName, Class<?> c) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonth().getValue();
        int day = now.getDayOfMonth();
        customContest(problems, year, month, day, dirPrefix, dirName, c);
    }

    public static void customContest(Class<?> c) {
        Objects.requireNonNull(c, "class not allow null");
        Scanner sc = new Scanner(System.in);
        int problems = 0;
        String dirPrefix = "";
        String dir = "";
        int count = 0;
        while (true) {
            if (count > 10) {
                break;
            }
            System.out.print("place input a valid number as problems : ");
            try {
                problems = sc.nextInt();
                if (problems <= 0) {
                    count++;
                    continue;
                }
                break;
            } catch (Exception ignored) {
                count++;
                sc.next(); // 清空输入缓冲区
            }
        }
        if (count > 10) {
            throw new RuntimeException("You only input error max 10 times ");
        }
        System.out.print("place input a valid string as dirPrefix : ");
        dirPrefix = sc.next();
        System.out.print("place input a valid string as dir : ");
        dir = sc.next();
        Problem.customContest(problems, dirPrefix, dir, c);
    }


    /**
     * 可能又其他比赛请自定义
     *
     * @param problems  题目多少
     * @param year      年份
     * @param month     月份
     * @param dirPrefix 前缀
     * @param dirName   目录名
     * @param c         生成在那个目录下？ 传入class会自动生成在对应目录下
     */
    public static void customContest(int problems, int year, int month, int day, String dirPrefix, String dirName, Class<?> c) {
        if (c == null) {
            c = WeekContest.class;
        }
        if (!dirPrefix.endsWith("_")) {
            dirPrefix = dirPrefix + "_";
        }
        dirName = IoUtil.wrapperAbsolutePath(c, dirName);
        StringBuilder sb = new StringBuilder();
        sb.append(dirName);
        sb.append(File.separator);
        sb.append(dirPrefix);
        sb.append(year).append("_");
        sb.append(month).append("_").append(day);
        sb.append(File.separator);
        String wrapperDir = sb.toString();
        createProblems(problems, wrapperDir);
    }
}
