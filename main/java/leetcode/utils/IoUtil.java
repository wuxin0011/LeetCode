package leetcode.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: wuxin0011
 * @Description: 处理输入输出
 */
public class IoUtil {


    /**
     * 必须文件名为 in.txt
     * 如果没有传入方法名将使用 class 中除main方法外的一个方法 如果只有一个或者两个方法名
     * 将会调用默认方法
     *
     * @param c
     * @param <T>
     */
    public static <T> void testUtil(Class<T> c) {
        testUtil(c, "null", "in.txt");
    }


    /**
     * 传入类型和方法名
     * 对拍文件为in.txt
     *
     * @param c
     * @param methodName
     * @param <T>
     */

    public static <T> void testUtil(Class<T> c, String methodName) {
        testUtil(c, methodName, "in.txt");
    }

    public static void check1() {

    }


    /**
     * 指定类和类的方法名和对拍文件名
     *
     * @param c
     * @param methodName
     * @param fileName
     * @param <T>
     */
    public static <T> void testUtil(Class<T> c, String methodName, String fileName) {
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
            if (names.size() > 0 && methodName == null || "null".equals(methodName)) {
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
                List<String> inputList = readFile(c, fileName);
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
                if ("void".equals(returnName)) {
                    // System.out.println("result type is void place implement this valid method\n");
                    // todo 没有返回值时候如何处理呢 ？
                    // 暂时处理成如果是 void 类型，将转换成第一个参数类型然后比较
                    returnName = parameterTypes[0].getSimpleName();
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
            ;
        }

    }


    public static <T> List<String> readFile(Class<T> c, String filename) {
        return readFile(buildAbsolutePath(c), filename);
    }

    public static List<String> readFile(String path, String fileName) {
        File file = new File(path + fileName);
        if (!file.exists()) {
            System.out.println(fileName + " not found!");
            return null;
        }
        // System.out.println("read fileName  = " + file.getAbsolutePath());
        BufferedReader breder = null;
        List<String> ans = new ArrayList<>();
        try {
            breder = new BufferedReader(new FileReader(file));
            String t = null;
            while ((t = breder.readLine()) != null) {
                ans.add(t);
            }
        } catch (Exception e) {
             System.err.println("parse failed " + e.getMessage());
        } finally {
            close(breder);
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
        sb.append("main");
        sb.append(File.separator);
        sb.append("java");
        sb.append(File.separator);
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
}
