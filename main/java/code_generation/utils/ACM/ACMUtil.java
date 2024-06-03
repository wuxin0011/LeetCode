package code_generation.utils.ACM;

import code_generation.utils.CustomColor;
import code_generation.utils.ExceptionUtils;
import code_generation.utils.IoUtil;
import code_generation.utils.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author: wuxin0011
 * @Description: ACM 模式 输入输出
 */
public class ACMUtil {


    public static final boolean isWin = System.getProperty("os.name").contains("Win") || System.getProperty("os.name").contains("win");
    public static final String CHANGE_DIR_COMMAND = isWin ? "cmd /c cd" : "cd";
    public static final String DEFAULT_INPUT = "in.txt";
    public static final String DEFAULT_OUTPUT = "out.txt";
    public static final String DEFAULT_TEMP = "temp.txt";

    static String outHomeDir = null;

    static {
        init();
    }

    private static void init() {
        String userHome = IoUtil.getWorkDir();
        Properties properties = System.getProperties();
        String classPathInfo = (String) properties.get("java.class.path");
        String[] split = classPathInfo.split(";");
        for (String s : split) {
            if (s.contains(userHome)) {
                outHomeDir = s;
                break;
            }
        }
    }

    static boolean printInfo = false;

    public static void runOnlyInput(Class<?> c) {
        runOnlyInput(c, "", "",true);
    }
    public static void runOnlyInput(Class<?> c,boolean isPrint) {
        runOnlyInput(c, "", "",isPrint);
    }

    public static void runOnlyInput(Class<?> c, String dir, String fileName,boolean isPrint) {
        check(c);
        if (StringUtils.isEmpty(fileName)) {
            fileName = DEFAULT_INPUT;
        }
        String in = IoUtil.wrapperAbsolutePath(c, dir) + File.separator + fileName;
        printInfo = isPrint;
        run(c, "", in, null, null);
        printInfo = false;
    }

    public static void run(Class<?> c) {
        check(c);
        String absolutePath = IoUtil.wrapperAbsolutePath(c, "") + File.separator;
        String in = absolutePath + DEFAULT_INPUT;
        String out = absolutePath + DEFAULT_OUTPUT;
        String temp = absolutePath + DEFAULT_TEMP;
        run(c, "", in, out, temp);
    }


    // handler abs
    private static String getAbs(Class<?> c, String dir, String s) {
        check(c);
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        String abs = IoUtil.wrapperAbsolutePath(c, String.format("%s%s", dir, s));
        File f = IoUtil.createFile(c, abs);
        if (f != null) {
            return f.getAbsolutePath();
        }
        return abs;
    }

    public static void run(Class<?> c, String dir, String in, String out, String temp) {
        BufferedReader input = null, error = null;
        try {
            check(c);

            if (in == null) {
                throw new RuntimeException("input file not allow null");
            }

            if (outHomeDir == null) {
                init();
            }

            String command = null;
            String name = c.getName().replace("class", "");

            in = getAbs(c, dir, in);
            if (out != null) {
                temp = getAbs(c, dir, temp);
                out = getAbs(c, dir, out);
            }
            // only input
            if (outHomeDir != null) {
                if (out == null) {
                    command = String.format("%s %s && java %s < %s",CHANGE_DIR_COMMAND, outHomeDir, name, in);
                } else {
                    command = String.format("%s %s & java %s < %s > %s",CHANGE_DIR_COMMAND, outHomeDir, name, in, temp);

                    // if this project is git project,this command will run but not result
                    // command = String.format("cmd /c cd %s & java %s < %s > %s & cmd /c git diff %s %s", outHomeDir, name, in, temp, out, temp);
                }
            }


            if (command == null) {
                return;
            }
            //System.out.println("command = " + command);

            // 执行命令
            Process process = Runtime.getRuntime().exec(command);

            if(printInfo) {
                input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                error = new BufferedReader(new InputStreamReader(process.getErrorStream()));

                String line;
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
                while ((line = error.readLine()) != null) {
                    System.out.println(line);
                }
            }



            int exitCode = process.waitFor();
            // System.out.println("\nExit Code: " + exitCode);


            if (temp != null && !printInfo) {
                compareFileDiff(out, temp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(input);
            IoUtil.close(error);
        }

    }

    private static void check(Class<?> c) {
        if (c == null) {
            throw new NullPointerException("class not allow null");
        }
    }


    /**
     * compare file content
     *
     * @param out  expect
     * @param temp result
     */
    public static void compareFileDiff(String out, String temp) {
        if (out == null || temp == null) {
            return;
        }
        // ExceptionUtils.sleep(2);
        File o = new File(out);
        File t = new File(temp);
        ExceptionUtils.executeWithExceptionHandling(() -> {
            if (!o.exists()) {
                o.createNewFile();
            }
            if (!t.exists()) {
                t.createNewFile();
            }
        }, false);
        BufferedReader fis1 = null;
        BufferedReader fis2 = null;
        String a1 = null, b1 = null;
        List<Integer> error = new ArrayList<>();
        Map<Integer, String> m1 = new HashMap<>();
        Map<Integer, String> m2 = new HashMap<>();
        try {
            fis1 = new BufferedReader(new FileReader(o));
            fis2 = new BufferedReader(new FileReader(t));

            int no = 1;

            while (true) {
                a1 = fis1.readLine();
                b1 = fis2.readLine();
                if (Objects.equals(a1, b1) && a1 == null) {
                    break;
                }
                if (a1 == null || !a1.equals(b1)) {
                    m1.put(no, a1);
                    m2.put(no, b1);
                    error.add(no);
                }
                no++;
            }


        } catch (Exception ignored) {

        } finally {
            IoUtil.close(fis1);
            IoUtil.close(fis2);
        }

        if (error.size() == 0) {
            System.out.println("Accepted");
        } else {
            for (Integer line : error) {
                // System.out.println(integer + " line not equals ");
                String a = m1.get(line);
                String b = m2.get(line);
                System.out.println("Diff Line " + line);
                System.out.println("Expect : " + ("\n".equals(a) ? "\\n" : a));
                System.out.println("Result : " + CustomColor.pink((b == null || "".equals(b) ? "is space black maybe you need \\n" : b)));
                System.out.println();
            }
        }
    }

}
