package leetcode.utils;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: wuxin0011
 * @Description: 处理输入输出
 */
public class IoUtil {


    /**
     * 默认方法名
     */
    public static final String DEFAULT_METHOD_NAME = "null";

    /**
     * 默认文件名
     */
    public static final String DEFAULT_READ_FILE = "in.txt";


    /**
     * 默认是否开启 ## 解析文件
     */
    public static final boolean DEFAULT_SUPPORT_LONG_CONTENT = false;

    /**
     * 默认路径 如果是 main/java 请用 {“main”，“java”}
     */
    public static final String[] DEFAULT_ROOTS = {"main", "java"};


    public static void main(String[] args) {
        //IoUtil.testUtil(IoUtil.class, "t1", "test.txt", true);
    }


    /**
     * 必须文件名为 in.txt
     * 如果没有传入方法名将使用 class 中除main方法外的一个方法 如果只有一个或者两个方法名
     * 将会调用默认方法
     *
     * @param c
     * @param <T>
     */
    public static <T> void testUtil(Class<T> c) {
        testUtil(c, DEFAULT_METHOD_NAME, DEFAULT_READ_FILE);
    }

    public String t1(String a) {
        return a;
    }


    public static <T> void testUtil(Class<T> c, String fileName, boolean openLongContent) {
        testUtil(c, DEFAULT_METHOD_NAME, fileName, openLongContent);
    }


    public static <T> void testUtil(Class<T> c, boolean openLongContent) {
        testUtil(c, DEFAULT_METHOD_NAME, DEFAULT_READ_FILE, openLongContent);
    }


    public static <T> void testUtil(Class<T> c, String methodName) {
        testUtil(c, methodName, DEFAULT_READ_FILE);
    }


    public static <T> void testUtil(Class<T> c, String methodName, String fileName) {
        testUtil(c, methodName, fileName, DEFAULT_SUPPORT_LONG_CONTENT);
    }


    /**
     * 对拍核心方法
     *
     * @param c               反射的类
     * @param methodName      方法名 但传入默认的时候 自动调用 除 "main" 方法外的一个方法，唯有只有一个其他方法适用
     * @param fileName        读取文件名
     * @param openLongContent 开启##解析
     * @param <T>             T
     */
    public static <T> void testUtil(Class<T> c, String methodName, String fileName, boolean openLongContent) {
        check(c, methodName, fileName);
        try {
            T obj = c.newInstance();

            Method[] methods = c.getDeclaredMethods();
            boolean find = false;
            List<String> names = new ArrayList<>();
            for (Method method : methods) {
                names.add(method.getName());
            }
            // System.out.println("names = " + names);
            if (names.size() > 0 && DEFAULT_READ_FILE.equals(methodName)) {
                for (String name : names) {
                    if (name.equals("main") || name.startsWith("lambda$") || "f".equals(name) || "dfs".equals(name)) {
                        continue;
                    }
                    methodName = name;
                }
            }

            for (Method method : methods) {

                if (!method.getName().equals(methodName)) {
                    continue;
                }
                if ("main".equals(method.getName())) {
                    continue;
                }
                find = true;
                method.setAccessible(true);
                List<String> inputList = readFile(c, fileName, openLongContent);
                if (inputList == null) {
                    // System.out.println("read content is null");
                    System.exit(0);
                    break;
                }
                startValid(obj, method, inputList);
            }
            if (!find) {
                System.err.println("check methodName ,not found " + methodName + " method !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> void startValid(Object obj, Method method, List<String> inputList) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] args = new Object[parameterTypes.length];
        int size = inputList.size();
        boolean f = true;
        for (int idx = 0; idx < size; ) {
            if (inputList.get(idx) == null || inputList.get(idx).length() == 0) {
                idx++;
                continue;
            }

            // 填充参数信息
            for (int i = 0; i < parameterTypes.length && idx < size; i++, idx++) {
                args[i] = ReflectUtils.parseArg(parameterTypes[i], inputList.get(idx));
            }


            if (idx >= size) {
                f = false;
                break;
            }

            // 分析该方法执行参数信息
            Object result = null;
            try {
                result = method.invoke(obj, args);
                // 对比预期结果和实际结果
                String returnName = method.getReturnType().getSimpleName();
                // System.out.println("return simpleName = " + returnName);
                if ("void".equals(returnName) && result == null) {
                    // System.out.println("result type is void place implement this valid method\n");
                    // todo 没有返回值时候如何处理呢 ？
                    // 暂时处理成如果是 void 类型，将转换成第一个参数类型然后比较
                    returnName = parameterTypes[0].getSimpleName();
                    result = args[0];

                }
                Object expect = ReflectUtils.parseArg(returnName, inputList.get(idx));
                if (expect != null && !TestUtils.valid(result, expect, returnName)) {
                    f = false;
                    // break;
                }

            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
                f = false;
                break;
            }
            idx++;
        }
        if (f) {
            System.out.println("success");
        } else {
            System.err.println("fail");
        }

    }


    public static <T> List<String> readFile(Class<T> c, String filename, boolean openLongContent) {
        return readFile(buildAbsolutePath(c), filename, openLongContent);
    }

    public static <T> List<String> readFile(Class<T> c, String filename) {
        return readFile(buildAbsolutePath(c), filename, false);
    }

    public static List<String> readFile(String path, String fileName) {
        return readFile(path, fileName, false);
    }


    public static List<String> readFile(String path, String fileName, boolean openLongContent) {
        File file = new File(path + fileName);
        if (!file.exists()) {
            System.out.println(fileName + " not found!");
            return null;
        }

        BufferedReader breder = null;
        BufferedInputStream bis = null;
        List<String> ans = new ArrayList<>();
        try {
            if (openLongContent) {

                return parseShpInfo(file);

            } else {
                breder = new BufferedReader(new FileReader(file));
                String t = null;
                while ((t = breder.readLine()) != null) {
                    ans.add(t);
                }
            }

        } catch (Exception e) {
            System.err.println("parse failed " + e.getMessage());
        } finally {
            close(breder);
            close(bis);
        }
        // System.out.println("read content = >" + ans);
        return ans;
    }


    public static <T> String buildAbsolutePath(Class<T> c) {
        return buildAbsolutePath() + getPackagePath(c) + File.separator;
    }


    public static String buildAbsolutePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(File.separator);
        for (String defaultRoot : DEFAULT_ROOTS) {
            sb.append(defaultRoot);
            sb.append(File.separator);
        }
        return buildAbsolutePath(sb.toString());
    }

    public static String buildAbsolutePath(String baseDir) {
        String wordDir = getWorkDir();
        return wordDir + baseDir;
    }

    public static <T> String getPackagePath(Class<T> c) {
        Package pkg = c.getPackage();
        String info = pkg.getName();
        info = info.replace("package ", "");
        char[] cs = info.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '.') {
                cs[i] = File.separator.charAt(0);
            }
        }
        return new String(cs);
    }


    public static String getWorkDir() {
        String dir = System.getProperty("user.dir");
        if (dir == null || "null".equals(dir) || dir.length() == 0) {
            dir = new File("").getAbsolutePath();
        }
        return dir;
    }


    public static <T> void check(Class<T> c, String methodName, String fileName) {
        Objects.requireNonNull(c, "className not null");
        Objects.requireNonNull(methodName, "methodName not null");
        Objects.requireNonNull(fileName, "fileName  not null");
    }

    public static void close(AutoCloseable... closeables) {
        for (AutoCloseable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }


    public static List<String> parseShpInfo(File file) {
        if (file == null || !file.exists()) {
            if (file != null) System.out.println(file.getName() + " is null ,place check exist !");
            return null;
        }
        List<String> ans = new ArrayList<>();
        BufferedInputStream bis = null;
        try {
            byte[] buff = new byte[1024 * 1024];
            StringBuilder sb = new StringBuilder();
            bis = new BufferedInputStream(Files.newInputStream(file.toPath()));
            while ((bis.read(buff)) != -1) {
                sb.append(new String(buff));
            }

            String input = sb.toString();

            Pattern pattern = Pattern.compile("#([^#]+)#", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(input);
            boolean find = false;
            while (matcher.find()) {
                // System.out.println(times + " @@@@=>" + matcher.group(1));
                ans.add(matcher.group(1));
                find = true;
            }
            if (!find) {
                System.out.println("find error place use #content# package your content ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(bis);
        }


        return ans;

    }


    public static void parseShpBackup(File file) {
        List<String> ans = new ArrayList<>();
        byte[] bf = new byte[1];
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(Files.newInputStream(file.toPath()));
            Stack<String> sk = new Stack<>();
            StringBuilder sb = new StringBuilder();
            while ((bis.read(bf) != -1)) {
                String s = new String(bf);
                if (s.equals("\n") || s.equals("\t") || s.equals("\r") || s.equals("\b") || s.equals(" ") || s.equals("'") || s.equals("\"")) {
                    continue;
                }
                if ("#".equals(s)) {
                    if (!sk.isEmpty()) {
                        sk.clear();
                        ans.add(sb.toString());
                    } else {
                        sk.push("#");
                        sb = new StringBuilder();
                    }
                } else {
                    sb.append(new String(bf));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
        }
    }


}
