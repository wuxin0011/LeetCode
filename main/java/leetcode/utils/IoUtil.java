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
    public static <T> void testUtil(Class<T> c, String methodName) {
        testUtil(c, methodName, "in.txt");
    }


    public static <T> void testUtil(Class<T> c, String methodName, String fileName) {
        valid(c, methodName, fileName);
        try {
            T obj = c.newInstance();
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                if (!method.getName().equals(methodName)) {
                    continue;
                }
                List<String> inputList = readFile(c, fileName);
                if (inputList == null) {
                    // System.out.println("read content is null");
                    System.exit(0);
                    break;
                }
                startValid(obj, method, inputList);
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
                Object arg = ReflectUtils.parseArg(parameterTypes[i], inputList.get(idx));
                args[i] = arg;
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
                Object expect = ReflectUtils.parseArg(returnName, inputList.get(idx));
                if (expect != null && !expect.equals(result) && !TestUtils.valid(result, expect, returnName)) {
                    f = false;
                    break;
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
                f = false;
                break;
            }
            idx++;
        }
        if (f) {
            System.out.println("ok");
        } else {
            System.out.println("error!");
        }

    }

    public static <T> List<String> readFile(Class<T> c) {
        return readFile(c, "in.txt");
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
            // System.out.println("parse failed " + e.getMessage());
        } finally {
            close(breder);
        }
        // System.out.println("read content = >" + ans);
        return ans;
    }


    /**
     * @param args
     */
    public static void v(int args) {

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


    public static <T> void valid(Class<T> c, String methodName, String fileName) {
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
