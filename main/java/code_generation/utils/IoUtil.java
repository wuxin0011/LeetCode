package code_generation.utils;


import code_generation.contest.ParseCodeInfo;
import code_generation.crwal.leetcode.LCCustom;
import code_generation.crwal.leetcode.LCSubmit;
import code_generation.proxy.TestData;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
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
     * 空类型或者不填充参数
     */
    public static final String VOID_OR_ARGS = "null";

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

    static Class<?> runClass;

    /**
     * 对拍核心方法
     *
     * @param src             反射的类
     * @param methodName      方法名 但传入默认的时候 自动调用 除 "main" 方法外的一个方法，唯有只有一个其他方法适用
     * @param fileName        读取文件名
     * @param openLongContent 开启##解析
     * @param isStrict        是否开启严格相等 如果不开启元素顺序，希望 为 false
     */
    public static <T> void testUtil(Class<T> src, String methodName, String fileName, boolean openLongContent, boolean isStrict) {
        check(src, methodName, fileName);
        runClass = src;
        boolean find = false;
        try {

            List<String> inputList = readFile(src, fileName, openLongContent);
            if (inputList == null) {
                System.exit(0);
            }
            // 构造类对拍
            if (ParseCodeInfo.ConstructorClass.equals(methodName)) {
                find = true;
                handlerConstructorValid(src, inputList, methodName, isStrict);
            } else {
                Object obj = ReflectUtils.initObjcect(src, null);
                Method method = findMethodName(src, methodName);
                if (method != null) {
                    find = true;
                    startValid(obj, method, inputList, isStrict, true);
                }


            }
            if (!find) {
                System.err.println("check methodName ,not found " + methodName + " method !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 构造类函数对拍
    public static void handlerConstructorValid(Class<?> src, List<String> inputList, String methodName, boolean isStrict) {
        String[] names = null, args = null, expect = null;
        // 是否是测试阶段
        boolean isTest = false;

        if (isTest) {
            System.err.println("constructor class is test ... ");
        }

        final String className = src.getSimpleName();

        Constructor constructor = src.getDeclaredConstructors()[0];
        constructor.setAccessible(true);

        Class[] parameterTypes = constructor.getParameterTypes();
        Object[] constructorArgs = null;


        Method[] declaredMethods = src.getDeclaredMethods();


        List<String> result = null;

        int runTimes = 0;

        Map<String, Method> map = new HashMap<>();
        for (Method method : declaredMethods) {
            method.setAccessible(true);
            map.put(method.getName(), method);
        }

        int t = 0;
        int compareTimes = 0;
        TestData testData = new TestData(src);
        int[] testGroup = testData == null || testData.testCaseGroup == null ? new int[]{1, 0x3fffff} : testData.testCaseGroup;
        boolean isTestCase = false;
        List<String> errorTimes = new ArrayList<>();
        for (int k = 0; k < inputList.size(); k++) {
            String s = inputList.get(k);
            if (StringUtils.isEmpty(s)) {
                continue;
            }
            // System.out.println(s);
            t++;
            if (t % 3 == 1) {
                names = ReflectUtils.oneStringArray(s);
                for (int i = 0; i < names.length; i++) {
                    names[i] = StringUtils.ingoreString(names[i]);
                }
            } else if (t % 3 == 2) {
                args = ReflectUtils.parseConstrunctorClassString(s);
            } else if (t % 3 == 0) {
                expect = ReflectUtils.parseConstrunctorClassString(s);
            }


            // 每三行内容为一组 需要填充调用的方法名，填入结果 以及期望结果
            if (t % 3 == 0) {

                Object obj = null;
                int a = 0, b = 0, deep = 0;

                // check
                if (args.length != expect.length || args.length != names.length || expect.length != names.length) {
                    throw new RuntimeException("result not mathch palce check");
                }

                compareTimes++;
                isTestCase = testGroup == null ? true : testGroup[0] <= compareTimes && compareTimes <= testGroup[1];
                if (!isTestCase) {
                    continue;
                }


                for (int index = 0; index < names.length; index++) {

                    String name = names[index];

                    if (StringUtils.isEmpty(name)) {
                        continue;
                    }


                    if (isTest) {
                        System.out.println();
                        System.out.println("name   =   " + name);
                        System.out.println("args   =   " + args[index]);
                        System.out.println("exp    =   " + expect[index]);
                        continue;
                    }

                    if (name.equals(className)) {
                        //  构造函数实例化
                        try {
                            result = new ArrayList<>();
                            ReflectUtils.handlerConstructorMethodInput(args[index], result);
                            obj = handlerConstructorMethod(constructor, result, src);
                        } catch (Exception e) {
                            e.printStackTrace();
                            obj = null;
                        }
                    } else {
                        Objects.requireNonNull(obj, "obj is null");
                        Method method = map.get(name);
                        if (!map.containsKey(name)) {
                            throw new RuntimeException("not find method " + name + ", place check your format !");
                        }
                        result = new ArrayList<>();
                        ReflectUtils.handlerConstructorMethodInput(args[index], result, method);
                        ReflectUtils.handlerConstructorMethodOutput(expect[index], result, method);
                        boolean isOk = startValid(obj, map.get(name), result, isStrict, false);
                        if (!isOk) {
                            String errorInfo = "Run CompareTimes :  " + compareTimes + "\nCall Method      :  " + name + "\nArgs Index       :  " + index +  "\nArgs             :  " + args[index];
                            errorTimes.add(errorInfo+"\n");
                        }
                    }
                }

                // clear
                args = names = expect = null;
                result = null;
            }
        }

        if (testData != null && !StringUtils.isEmpty(testData.info)) {
            System.out.println(testData.info);
        }
        if (errorTimes.size() == 0) {
            System.out.println("Accepted!");
        } else {
            for (String errorInfo : errorTimes) {
                System.out.println(errorInfo);
            }
        }
    }

    public static Object handlerConstructorMethod(Constructor<?> constructor, List<String> inputList, Class<?> aclass) {
        Object obj = null;
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        if (parameterTypes == null || parameterTypes.length == 0) {
            try {
                obj = constructor.newInstance();
                return obj;
            } catch (Exception e) {
                return null;
            }
        }
        Object[] args = null;

        int size = inputList.size();
        String read = null;
        for (int idx = 0; idx < size; ) {
            args = new Object[parameterTypes.length];
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
                args[i] = ReflectUtils.parseArg(aclass, constructor.getName(), parameterTypes[i], read, i, parameterTypes.length);
                read = null;
            }

            try {
                obj = constructor.newInstance(args);
            } catch (Exception e) {
                e.printStackTrace();
                obj = null;
            }
        }
        return obj;


    }


    /**
     * 对拍核心方法
     * @param obj 对象
     * @param method 方法
     * @param inputList 输入输出
     * @param isStrict 是否要求顺序
     * @param newObj 每次执行是否创建新对象
     * @return
     */
    public static boolean startValid(Object obj, Method method, List<String> inputList, boolean isStrict, boolean newObj) {
        Objects.requireNonNull(obj, "obj is null");
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class<?> srcClass = obj.getClass();
        Class<?> origin = ReflectUtils.loadOrigin(obj.getClass());
        Object[] args = null;
        String returnName = method.getReturnType().getSimpleName();
        final String tempRetrunName = returnName;
        int size = inputList.size();
        String read = null;

        TestData testData = newObj ? new TestData(method, origin, srcClass) : null;

        int[] testCaseInfo = testData == null || testData.testCaseGroup == null ? new int[]{1, 0x3f3f3f} : testData.testCaseGroup;


        int typeId = -2; // 如果返回值是空类型 而且需要比较的标志

        boolean isStatic = Modifier.isStatic(method.getModifiers()); // 是否是静态方法

        boolean isConstrunctorClass = !origin.getSimpleName().equals(obj.getClass().getSimpleName());

        List<Integer> errorTimes = new ArrayList<>();
        int exceptionTime = -1;
        int compareTimes = 1;
        boolean isStartTest = false;
        for (int idx = 0; idx < size; ) {

            if (compareTimes > testCaseInfo[1]) {
                break;
            }

            // 是否在测试范围内
            isStartTest = testCaseInfo[0] <= compareTimes && compareTimes <= testCaseInfo[1];

            if(isStartTest) {
                if (newObj && !isStatic) {
                    // 如果不是构造类型对拍，定义普通类型属性会影响下次对拍 因此重新初始化
                    // 就是上次数据影响这次对拍
                    // example: leetcode.everyday.Code_0049_39
                    obj = ReflectUtils.initObjcect(srcClass, null);
                    Objects.requireNonNull(obj, "obj is null");
                }
            }


            // 填充参数信息
            boolean isFill = false; // 参数校验标志信息

            if (parameterTypes == null || parameterTypes.length == 0) {
                while (idx < size && ((read = inputList.get(idx)) == null)) {
                    idx++;
                }
                if (VOID_OR_ARGS.equals(read) || read.length() == 0) {
                    isFill = true;
                    read = null;
                    idx++;
                } else {
                    throw new RuntimeException("NO args fill, should null");
                }
            } else {
                args = new Object[parameterTypes.length];
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
                    if (isStartTest) {
                        args[i] = ReflectUtils.parseArg(origin, method.getName(), parameterTypes[i], read, i, parameterTypes.length);
                    }
                    read = null;
                }

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
                if (isStartTest) {
                    if (parameterTypes == null || parameterTypes.length == 0) {
                        result = method.invoke(isStatic ? null : obj);
                    } else {
                        result = method.invoke(isStatic ? null : obj, args);
                    }
                }

                // 允许答案和输入参数之间有间隙
                while (idx < inputList.size() && ((read = inputList.get(idx)) == null || read.length() == 0)) {
                    idx++;
                }
                // 结果不匹配
                if (read == null) {
                    if ("string".equalsIgnoreCase(returnName)) {
                        read = "";
                    } else {
                        throw new RuntimeException("result not match place check your ans !");
                    }
                }

                if (!isStartTest) {
                    returnName = tempRetrunName; // origin return name
                    idx++; // match ok
                    compareTimes++; // 比较次数
                    continue;
                }

                if ("void".equalsIgnoreCase(returnName) && result == null) {

                    // 如果结果为null说明只是调用改方法 并且返回值为 void
                    // 这次就不参与比较了
                    if (VOID_OR_ARGS.equals(read)) {
                        idx++; // match ok
                        compareTimes++; // 比较次数
                        continue;
                    }

                    //  没有返回值时候如何处理呢 ？
                    if (args != null && args.length > 0) {
                        // 先处理成不是基本数据类型 因为基本数据类型是值传递 无法比较
                        if (typeId == -2) {
                            typeId = ReflectUtils.handlerVoidReturnType(parameterTypes);
                        }
                        if (typeId == -1) {
                            throw new RuntimeException("unkonwn compare type");
                        }
                        returnName = parameterTypes[typeId].getSimpleName();
                        result = args[typeId];
                    }
                }

                Object expect = ReflectUtils.parseArg(origin, method.getName(), returnName, read, -1, -1);
                if (expect != null && !TestUtils.valid(result, expect, returnName, isStrict, true)) {
                    // 非构造类才输出错误信息
                    if (newObj) {
                        System.out.println("compare " + compareTimes + " is Error , Run Method Name : " + method.getName() + "\n"); // save error
                    }
                    errorTimes.add(compareTimes);
                }
                args = null;
                read = null;
            } catch (Exception e) {
                e.printStackTrace();
                exceptionTime = compareTimes;
                break;
            }
            returnName = tempRetrunName; // origin return name
            idx++; // match ok
            compareTimes++; // 比较次数
        }


        if ( newObj && !StringUtils.isEmpty(testData.info)) {
            System.out.println(testData.info);
        }

        if (errorTimes.size() == 0 && exceptionTime == -1 && newObj) {
            System.out.println("Accepted!");
            List<String> urls = LCCustom.matchLeetCodeUrls(IoUtil.readContent(String.format("%s%s.java", IoUtil.buildAbsolutePath(runClass), runClass.getSimpleName())));
            if(!urls.isEmpty()){
                System.out.println("\nSubmit remotely ? (y|yes) ");
                Scanner sc = new Scanner(System.in);
                String next = sc.next();
                if("y".equals(next.toLowerCase()) || "yes".equals(next.toLowerCase())){
                    LCSubmit.submit(runClass);
                }
            }

        } else {
//            for (int error : errorTimes) {
//                System.out.println("compare " + error + " is Error ,place check your program");
//            }
            if (exceptionTime != -1) {
                System.out.println("exception times :" + exceptionTime);
            }
        }
        return errorTimes.size() == 0 && exceptionTime == -1;
    }


    // TODO 无尽对拍

    /**
     * 通过调用两个函数一般认为一个是暴力方法 一个是优化后的方法， 暴力方法容易书写，优化后的方法但是数据量太小不太容易比较 为此写一个测试方法
     * <p>
     * 通过输入fileName方法 可以试试特殊的测试用例
     *
     * @param A                A 类
     * @param methodNameA      方法A
     * @param B                B 类
     * @param methodNameB      方法B
     * @param maxCount         最大数据量
     * @param minRunTimes      最少执行次数
     * @param fileName         如果文件不为空 可以读取数据后在比较 但是读取内容不能有期望值
     * @param ifVoidCompareArg 如果没有返回值 将会通过那个参数来比较
     */
    public static void upd(Class<?> A, String methodNameA, Class<?> B, String methodNameB, int maxCount, int minRunTimes, String fileName, int ifVoidCompareArg) {
        boolean isTest = true;
        if (isTest) {
            System.out.println("this api is test ...");
        }
        if (A == null && B == null) {
            throw new NullPointerException("class type not allow null");
        }
        if (A == null && B != null) {
            A = B;
        } else if (B == null && A != null) {
            B = A;
        }
        Method m1 = findMethodName(A, methodNameA);
        if (m1 == null) {
            throw new NullPointerException();
        }
        Method m2 = findMethodName(B, methodNameB);
        if (m2 == null) {
            throw new NullPointerException();
        }
        if (A == B && methodNameB.equals(methodNameA)) {
            System.out.println("class type and method name is same");
            return;
        }
        Class<?>[] p1 = m1.getParameterTypes();
        Class<?>[] p2 = m2.getParameterTypes();


        int L = p1.length;

        String[] argsType1 = buildArgsType(A, p1, m1);
        String[] argsType2 = buildArgsType(B, p2, m2);
        if (!TestUtils.deepEqual(argsType1, argsType2, true)) {
            throw new RuntimeException("args type not equal");
        }
        String[] argsType = Arrays.copyOf(argsType1, L);


        Object obj1 = null, obj2 = null; // 调用对象

        boolean isVoid = m1.getReturnType().getSimpleName().equals("void");


        boolean isStatic1 = Modifier.isStatic(m1.getModifiers());
        boolean isStatic2 = Modifier.isStatic(m2.getModifiers());
        Object[] args = new Object[L];
        for (int i = 0; i < 10000; i++) {
            buildParamaters(argsType, args);
            Object r = invokeObjectMethod(A, m1, args, ifVoidCompareArg, isStatic1, isVoid);
            Object e = invokeObjectMethod(B, m2, args, ifVoidCompareArg, isStatic1, isVoid);
            if (r == e) {
                continue;
            }
            if (r == null || e == null) {
                throw new NullPointerException();
            }

            // deepEquals() // TODO 比较结果是否一致
        }
    }

    public static Object invokeObjectMethod(Class<?> src, Method m, Object[] args, int ifVoidCompareArgs, boolean isStatic, boolean isVoid) {
        Object obj = null;
        Object[] curArgs = Arrays.copyOf(args, args.length);
        if (!isStatic) {
            obj = loadObj(src);
        }
        if (!isStatic && obj == null) {
            throw new NullPointerException("object is null");
        }
        Object result = null;
        try {
            result = m.invoke(obj, curArgs);
        } catch (Exception ignore) {

        }

        if (isVoid) {
            if (ifVoidCompareArgs >= curArgs.length) {
                throw new IndexOutOfBoundsException();
            }
            return curArgs[ifVoidCompareArgs];
        }
        return result;
    }

    private static String[] buildArgsType(Class<?> a, Class<?>[] p, Method m) {
        Objects.requireNonNull(a, "class is null");
        Objects.requireNonNull(p, "parameter type is null");
        Objects.requireNonNull(m, "method is null");
        String[] argsType = new String[p.length + 1];
        for (int i = 0; i <= p.length; i++) {
            String typeName = i <= p.length - 1 ? p[i].getSimpleName() : m.getReturnType().getSimpleName();
            if (typeName.contains("ArrayList") || typeName.contains("List")) {
                typeName = IoUtil.findListReturnTypeMethod(a, m.getName(), typeName, i == p.length ? -1 : i, p.length);
                typeName = typeName.replace("ArrayList", "List");
            }
            argsType[i] = typeName;
        }
        return argsType;
    }


    public static Object loadObj(Class<?> src) {
        try {
            return src.newInstance();
        } catch (Exception e) {
            return null;
        }
    }


    public static void buildParamaters(String[] parameterTypes, Object[] args) {
        int mxCnt = 10000000;
        int ifVoidCompare = 0;
        // TODO 根据参数类型构造数据
        // TODO 由于数据类型众多 仅仅是指出数据量是不正确的 后面再说吧该方法😂
        for (int i = 0; i < parameterTypes.length; i++) {
            Object o = null;
            args[i] = o;
        }
    }

    public static Method findMethodName(Class<?> src, String methodName) {
        boolean find = false;
        Method[] methods = src.getDeclaredMethods();

        List<String> names = new ArrayList<>();
        for (Method method : methods) {
            names.add(method.getName());
        }

        if (names.size() > 0 && DEFAULT_METHOD_NAME.equals(methodName) || "main".equals(methodName)) {
            for (String name : names) {
                if (name.equals("main") || name.startsWith("lambda$")) {
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
            return method;
        }
        return null;
    }


    public static List<String> readFile(Class<?> c, String filename, boolean openLongContent) {
        return readFile(buildAbsolutePath(ReflectUtils.loadOrigin(c)), filename, openLongContent);
    }

    public static List<String> readFile(Class<?> c, String filename) {
        return readFile(buildAbsolutePath(ReflectUtils.loadOrigin(c)), filename, false);
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


    public static void check(Class<?> src, String methodName, String fileName) {
        Objects.requireNonNull(src, "className not null");
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
    public static String findListReturnTypeMethod(Class<?> c, String name, String returnType, int idx, int argsSize) {
        if (c == null) {
            System.out.println("error t is null");
            return defaultContent;
        }
        c = ReflectUtils.loadOrigin(c);
        String path = buildAbsolutePath(c) + c.getSimpleName() + ".java";
        File file = new File(path);
        if (!file.exists()) {
            System.out.println(file.getAbsolutePath() + "Not found !");
            return defaultContent;
        }
        BufferedReader bis = null;
        String s = null;
        // fix LC bi week 147 contest bug
        if(name.contains("$")) {
            String[] split = name.split("\\$");
            name = split[split.length - 1];
        }
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


    public static String wrapperAbsolutePath(Class<?> c, String dir) {
        Objects.requireNonNull(dir, "dir Not allow null");
        return isAbsolutePath(dir) ? dir : IoUtil.buildAbsolutePath(c) + dir;
    }

    public static boolean isAbsolutePath(String dir) {
        if (dir == null || dir.length() == 0) {
            return false;
        }
        return dir.charAt(0) == File.separator.charAt(0) || dir.charAt(0) == '/' || dir.length() >= 2 && dir.charAt(1) == ':';
    }


    public static String readContent(Class<?> c, String path) {
        path = wrapperAbsolutePath(c, path);
        return readContent(path);
    }

    public static String readContent(String path) {
        return readContent(new File(path));
    }


    public static String readContent(File file) {
        BufferedInputStream bis = null;
        StringBuilder sb = null;
        try {
            if (!file.exists()) {
                return "";
            }
            bis = new BufferedInputStream(new FileInputStream(file));
            sb = new StringBuilder();
            byte[] buf = new byte[1024 * 1024];
            int len = -1;
            while ((len = bis.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            close(bis);
        }

    }


    public static void writeContent(Class<?> c, String url, String content) {
        url = wrapperAbsolutePath(c, url);
        writeContent(url, content);
    }


    public static void writeContent(String url, String content) {
        writeContent(new File(url), content);
    }

    public static void writeContent(File file, String content) {
        BufferedOutputStream bos = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(content.getBytes(StandardCharsets.UTF_8));
            bos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(bos);
        }
    }


    public static File createFile(Class<?> c, String filename) {
        filename = wrapperAbsolutePath(c, filename);
        return createFile(filename);
    }


    public static File createFile(String fileName) {

        try {
            File file = new File(fileName);
            if (file == null) {
                return null;
            }
            String parent = file.getParent();
            if (parent == null) {
                return file;
            }
            File parentFile = new File(parent);
            if (!parentFile.exists()) parentFile.mkdirs();
            if (!file.exists()) {
                file.createNewFile();
                return file;
            }
            //System.out.println(fileName + " is exists create fail");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
