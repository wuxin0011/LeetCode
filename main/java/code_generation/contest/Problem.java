package code_generation.contest;

import code_generation.crwal.leetcode.LCContest;
import code_generation.utils.IoUtil;
import code_generation.utils.ReflectUtils;
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
            // IoUtil.writeContent(file, StringUtils.replaceIgnoreContent(testCase));
            IoUtil.writeContent(file, testCase);
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
        createTemplate(classFilePath, txtFile, "", "");
    }


    public static void createTemplate(File classFilePath, String txtFile, String testCase, String classTempalte) {
        try {
            String name = classFilePath.getName().replace(".java", "");
            txtFile = handlerTxt(txtFile, name, classFilePath, testCase);
            if (StringUtils.isEmpty(classTempalte)) {
                String packageInfo = ReflectUtils.getPackageInfo(classFilePath.getAbsolutePath());
                classTempalte = String.format(pattern, packageInfo, name, name, txtFile);
            }
            IoUtil.writeContent(classFilePath, classTempalte);
            System.out.println(classFilePath + "  create success !\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    final static String pattern = "package %s;\n" +
            "\n" +
            "import code_generation.utils.IoUtil;\n" +
            "import code_generation.annotation.TestCaseGroup;\n" +
            "import java.util.*;\n" +
            "/**\n" +
            " * @author: \n" +
            " * @Description:\n" +
            " * @url\n" +
            " * @title\n" +
            " */\n" +
            "//@TestCaseGroup(start = 1,end = 0x3fff,use = true)\n" +
            "public class %s {\n" +
            "    public static void main(String[] args) {\n" +
            "        IoUtil.testUtil(%s.class,IoUtil.DEFAULT_METHOD_NAME,\"%s.txt\");\n" +
            "    }\n" +
            "}\n";

    public static String createDir(int id, boolean isUpper) {
        if (id < 0 || id >= 26) {
            throw new RuntimeException("place input 0-25 number");
        }
        char c = isUpper ? (char) (id + 'A') : (char) (id + 'a');
        return String.valueOf(c);
    }

    public static String createDir(int id, boolean isUpper, String dir) {
        return dir + File.separatorChar + createDir(id, isUpper);
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


    public static void create(ProblemInfo info) {
        File classFile = IoUtil.createFile(info.getJavaFile());
        createTemplate(classFile, info.getTxtFile(), info.getTestCase(), ClassTemplate.getTemplate(info.getClassTemplate()));
    }


    public static void create(String javaFilePath, String txtFilePath, Class<?> c) {
        javaFilePath = IoUtil.wrapperAbsolutePath(c, javaFilePath);
        txtFilePath = IoUtil.wrapperAbsolutePath(c, txtFilePath);
        create(javaFilePath, txtFilePath);
    }


    public static void create(String javaFilePath, String txtFilePath) {
        File classFile = IoUtil.createFile(javaFilePath);
        if (classFile != null) {
            createTemplate(classFile, txtFilePath);
        }
    }

    public static File getClassFile(int id, String path) {
        File dirFile = new File(createDir(id, false, path));
        if (!dirFile.exists()) dirFile.mkdir();
        String prefix = createDir(id, true, dirFile.getAbsolutePath());
        return IoUtil.createFile(prefix + ".java");
    }

    public static void create(int id, String path) {
        // create dir
        create(id, path, "", "");
    }


    public static void create(int id, String path, String testCase, String code) {
        // create dir
        File classFile = getClassFile(id, path);
        if (classFile != null) {
            createTemplate(classFile.getAbsolutePath(), testCase, code);
        }
    }

    public static void create(int id, String path, String testCase, ClassTemplate classTemplate) {
        File classFile = getClassFile(id, path);
        String packageInfo = ReflectUtils.getPackageInfo(classFile.getAbsolutePath());
        classTemplate.buildPackageInfo(packageInfo);
        String code = ClassTemplate.getTemplate(classTemplate);
        createTemplate(classFile.getAbsolutePath(), testCase, code);
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
            c = LCContest.class;
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
