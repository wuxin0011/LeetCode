package code_generation.utils;


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

@SuppressWarnings("all")
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
     * 是否严格相等！
     * 默认 true
     */
    public static final boolean IS_STRICT_EQUAL = true;

    /**
     * 如果想使用自定义路径, 如 main\java => {"main"，"java"}
     */
    public static final String[] DEFAULT_ROOTS = {"main", "java"};


    private static String ROOT_DIR = "null";

    static {
        getProjectRootDir();
    }

    public static String getProjectRootDir() {
        if (!(ROOT_DIR == null || "null".equals(ROOT_DIR))) {
            return ROOT_DIR;
        }
        if (DEFAULT_ROOTS == null) {
            throw new RuntimeException("not null");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(File.separator);
        for (String defaultRoot : DEFAULT_ROOTS) {
            sb.append(defaultRoot);
            sb.append(File.separator);
        }
        ROOT_DIR = sb.toString();
        return ROOT_DIR;
    }


    public static void main(String[] args) {
        //IoUtil.testUtil(IoUtil.class, "t1", "test.txt", true);
    }


    public static <T> void testUtil(Class<T> c) {
        testUtil(c, DEFAULT_METHOD_NAME, DEFAULT_READ_FILE);
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

    public static <T> void testUtil(Class<T> c, String methodName, String fileName, boolean openLongContent) {
        testUtil(c, methodName, fileName, openLongContent, IS_STRICT_EQUAL);
    }


    /**
     * 对拍核心方法
     *
     * @param c               反射的类
     * @param methodName      方法名 但传入默认的时候 自动调用 除 "main" 方法外的一个方法，唯有只有一个其他方法适用
     * @param fileName        读取文件名
     * @param openLongContent 开启##解析
     * @param isStrict        是否开启严格相等 如果不开启元素顺序，希望 为 false
     * @param <T>             T
     */
    public static <T> void testUtil(Class<T> c, String methodName, String fileName, boolean openLongContent, boolean isStrict) {
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
            if (names.size() > 0 && DEFAULT_METHOD_NAME.equals(methodName) || "main".equals(methodName)) {
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
                startValid(obj, method, inputList, isStrict);
            }
            if (!find) {
                System.err.println("check methodName ,not found " + methodName + " method !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> void startValid(Object obj, Method method, List<String> inputList, boolean isStrict) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] args = new Object[parameterTypes.length];
        int size = inputList.size();
        boolean f = true;
        String read = null;
        List<Integer> errorTimes = new ArrayList<>();
        int compareTimes = 0;
        for (int idx = 0; idx < size; ) {
            // 填充参数信息
            boolean isFill = false; // 参数校验标志信息
            for (int i = 0; i < parameterTypes.length && idx < size; i++, idx++) {
                // 允许答案和输入参数之间有间隙
                while (idx < size && ((read = inputList.get(idx)) == null || read.length() == 0)) {
                    idx++;
                }
                if (idx == size) {
                    break;
                }
                if (idx != size && (read == null || read.length() == 0)) {
                    throw new RuntimeException("result not match place check your ans !");
                }
                isFill = true;
                args[i] = ReflectUtils.parseArg(obj, method.getName(), parameterTypes[i], read, i, parameterTypes.length);
                read = null;
            }

            if (idx >= size) {
                if (isFill) {
                    System.out.println("place check result match");
                    errorTimes.add(compareTimes);
                }
                break;
            }

            // 分析该方法执行参数信息
            Object result = null;
            try {
                result = method.invoke(obj, args);
                // 对比预期结果和实际结果
                String returnName = method.getReturnType().getSimpleName();
                if ("void".equals(returnName) && result == null) {
                    // todo 没有返回值时候如何处理呢 ？
                    // 暂时处理成如果是 void 类型，将转换成第一个参数类型然后比较
                    returnName = parameterTypes[0].getSimpleName();
                    result = args[0];
                }

                // 允许答案和输入参数之间有间隙
                while (idx < inputList.size() && ((read = inputList.get(idx)) == null || read.length() == 0)) {
                    idx++;
                }
                // 结果不匹配
                if (read == null || read.length() == 0) {
                    throw new RuntimeException("result not match place check your ans !");
                }
                Object expect = ReflectUtils.parseArg(obj, method.getName(), returnName, read, -1, -1);
                if (expect != null && !TestUtils.valid(result, expect, returnName, isStrict)) {
                    errorTimes.add(compareTimes); // save error
                }
                read = null;
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
                errorTimes.add(compareTimes);
                break;
            }
            idx++; // match ok
            compareTimes++; // 比较次数
        }
        if (errorTimes.size() == 0) {
            System.out.println("Accepted!");
        } else {
            for (int error : errorTimes) {
                System.out.println("compare " + error + " is Error ,place check your program");
            }
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

        return buildAbsolutePath(getProjectRootDir());
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
            e.printStackTrace();
        } finally {
            close(bis);
        }
    }

    private static final String defaultContent = "List<String>";


    /**
     * 根据方法名称匹配 List参数
     *
     * @param c            类名
     * @param name         方法名
     * @param returnType   类型
     * @param idx          参数序号 -1 表示是返回值类型 其他表示该参数的索引位置信息
     * @param argsSize     校验参数类型大小是否一致！
     * @param isReturnType 是否是返回类型
     * @param <T>
     * @return
     */
    public static <T> String findListReturnTypeMethod(Class<T> c, String name, String returnType, int idx, int argsSize) {
        if (c == null) {
            System.out.println("error t is null");
            return defaultContent;
        }
        String path = buildAbsolutePath(c) + c.getSimpleName() + ".java";
        File file = new File(path);
        if (!file.exists()) {
            System.out.println(file.getAbsolutePath() + "Not found !");
            return defaultContent;
        }
        BufferedReader bis = null;
        String s = null;
        String matchMethodName = name + "(";
        try {
            bis = new BufferedReader(new FileReader(file));
            while ((s = bis.readLine()) != null) {
                if (s.contains(matchMethodName)) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(bis);
        }

        if (s == null) {
            System.out.println("not find + " + name + " method");
            return defaultContent;
        }
        StringBuilder sb = new StringBuilder();
        Stack<Character> sk = new Stack<>();
        int st = -1;
        if (idx == -1) {
            st = s.indexOf(returnType);
            if (st == -1) {
                throw new RuntimeException("not find " + returnType + " as return type !");
            }
            st += returnType.length();
            sb.append(returnType);
            for (int i = st; i < s.length(); i++) {
                char cr = s.charAt(i);
                if (sk.isEmpty() && cr == ' ') break;
                if (cr == '[') {
                    sk.push(cr);
                } else if (cr == ']') {
                    if (!sk.isEmpty()) {
                        sk.pop();
                    }
                }
                sb.append(cr);
            }
            return sb.toString();
        } else {
            // 根据方法名查找第一个参数
            st = s.indexOf(matchMethodName);
            if (st == -1) throw new RuntimeException("not find methodName");
            st += name.length();
            List<String> argsList = new ArrayList<>();
            for (int i = st; i < s.length(); i++) {
                char chr = s.charAt(i);
                if (chr == ' ') {
                    if (sb != null && sb.toString().length() != 0 && !"".equals(sb.toString())) {
                        argsList.add(sb.toString());
                        sb = null;
                    }
                    continue;
                }
                if (chr == '(') {
                    sb = new StringBuilder();
                    sk.push(chr);
                } else if (chr == ')') {
                    if (sb != null) {
                        argsList.add(sb.toString());
                        sb = null;
                    }
                    if (!sk.isEmpty()) {
                        sk.pop();
                    }
                    if (sk.isEmpty()) break;
                } else if (chr == ',') {
                    sb = new StringBuilder();
                } else {
                    if (sb != null) {
                        sb.append(chr);
                    }
                }
            }
            if (idx >= argsList.size()) throw new RuntimeException("not find " + returnType);
            if (argsSize != argsList.size()) throw new RuntimeException("args size not match place check!");
            return argsList.get(idx);
        }


    }


    public static <T> String wrapperAbsolutePath(Class<T> c, String dir) {
        Objects.requireNonNull(dir, "dir Not allow null");
        return isAbsolutePath(dir) ? dir : IoUtil.buildAbsolutePath(c) + dir;
    }

    public static boolean isAbsolutePath(String dir) {
        if (dir == null || dir.length() == 0) {
            return false;
        }
        return dir.charAt(0) == File.separator.charAt(0) || dir.charAt(0) == '/' || dir.length() >= 2 && dir.charAt(1) == ':';
    }
}
