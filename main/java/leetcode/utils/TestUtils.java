package leetcode.utils;

import leetcode.function.Expect;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
public class TestUtils {



    public static void testBoolean(Object real, Object exp) {
        testBoolean(real, exp, System.currentTimeMillis() + " 测试通过！");
    }

    public static void testBoolean(Object real, Object exp, String successMsg) {
        Expect<Object, String> expect = (v1, v2, msg) -> {
            if (v1 != null && v1.equals(v2) || v1 == v2) {
                System.out.println(msg);
            } else {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 测试失败！\n" + "real = " + v1 + ", exp = " + v2 + "\n====================");
            }
        };
        expect.expect(real, exp, successMsg);
    }

    public static <T> void testArray(T[] arr1, T[] arr2, String message) {
        Expect<T[], String> expect = (v1, v2, msg) -> {
            if (v1 == v2) {
                System.out.println("测试通过");
            } else if (v1 != null && v2 != null && v2.length != v1.length) {
                System.out.println("test error");
            } else if (v1 != null && v2 != null) {
                int len = v2.length;
                boolean flag = true;
                for (int i = 0; i < len; i++) {

                    // 判断为 null 情况
                    if (v1[i] == null && v2[i] != null || v1[i] != null && v2[i] == null) {
                        flag = false;
                        break;
                    }
                    if (v1[i] != null && !v1[i].equals(v2[i])) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("测试失败！");
                } else {
                    System.out.println("测试ok！");
                }
            } else {
                System.out.println("test error");
            }
        };
        expect.expect(arr1, arr2, message);
    }


    public static <T> boolean deepEqual(List<T> a, List<T> b) {
        if (a == b) {
            System.out.println("ok");
            return true;
        }
        if (a == null || b == null || a.size() != b.size()) {
            System.out.println("error");
            return false;
        }
        int n = a.size();
        int idx = -1;
        for (int i = 0; i < n; i++) {
            T t1 = a.get(i);
            T t2 = b.get(i);
            if(t1 == null || !valid(t1,t2,t1.getClass().getSimpleName())){
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            System.out.println("ok");
            return true;
        } else {
            System.out.println("error");
            System.out.println("index = " + idx + ",a:" + a.get(idx) + ",b = " + b.get(idx));
            return false;
        }
    }

    public static <T> boolean deepEqual(T[] a, T[] b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null || a.length != b.length) {
            return false;
        }
        int n = a.length, x = -1;
        for (int i = 0; i < n; i++) {
            if (a[i] != null && a[i].equals(b[i])) {
                x = i;
                break;
            }
        }
        if (x != -1) {
            System.out.println("error index =" + x);
            return false;
        }
        return true;

    }


    public static <T> boolean deepEqual(T[][] a, T[][] b) {
        if (a == b) {
            System.out.println("ok");
            return true;
        }
        if (a == null || b == null || a.length != b.length || a[0].length != b[0].length) {
            System.out.println("error length not equal!" + "a.length = " + a.length + ",a[0].length=" + a[0].length + "b.length = " + b.length + ",b[0].length=" + b[0].length);
            return false;
        }
        int m = a.length, n = a[0].length, x = -1, y = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != null && !a[i][j].equals(b[i][j])) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        if (x == -1) {
            return true;
        } else {
            System.out.println("error");
            System.out.println("index row= " + x + ",col = " + y + ":" + a[x][y] + ",b = " + b[x][y]);
            return false;
        }

    }

    public static <T> boolean deepEqual(T[][][] a, T[][][] b) {
        if (a == b) {
            System.out.println("ok");
            return true;
        }
        if (a == null || b == null || a.length != b.length || a[0].length != b[0].length || a[0][0].length != b[0][0].length) {
            System.out.println("error");
            return false;
        }
        int m = a.length, n = a[0].length, z = a[0][0].length;
        boolean f = true;
        int x = -1, y = -1, o = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < z; k++) {
                    if (a[i][j][k] == b[i][j][k]) {
                        continue;
                    }
                    if (a[i][j][k] != null && !a[i][j][k].equals(b[i][j][k])) {
                        x = i;
                        y = j;
                        o = k;
                        break;
                    }
                }
            }
        }
        if (x == -1) {
            System.out.println("ok");
            return true;
        } else {
            System.out.println("error" + "index = (" + x + "," + y + "," + o + ")");
            return false;
        }


    }


    public static boolean valid(Object result, Object expect, String returnType) {
        if (returnType == null) {
            System.out.println("returnType is null error!!!");
            return false;
        }
        try {
            switch (returnType) {
                case "int":
                case "Integer":
                    if (!result.equals(expect)) {
                        System.out.println("error");
                        return false;
                    }
                    return true;
                case "int[]": {
                    Integer[] e = covert((int[]) expect);
                    Integer[] r = covert((int[]) result);
                    return deepEqual(r, e);
                }
                case "int[][]": {
                    Integer[][] e = covert((int[][]) expect);
                    Integer[][] r = covert((int[][]) result);
                    return deepEqual(r, e);
                }
                case "int[][][]": {
                    Integer[][][] e = covert((int[][][]) expect);
                    Integer[][][] r = covert((int[][][]) result);
                    return deepEqual(r, e);
                }
                case "string[]": {
                    String[] r = (String[]) result;
                    String[] e = (String[]) expect;
                    return deepEqual(r, e);
                }
                case "string[][]":
                    return deepEqual((String[][]) result, (String[][]) expect);
                case "string[][][]":
                    return deepEqual((String[][][]) result, (String[][][]) expect);
                case "ArrayList":
                    return deepEqual((ArrayList<Object>) result, (ArrayList<Object>) expect);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static Integer[] covert(int[] a) {
        Integer[] t = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            t[i] = a[i];
        }
        return t;
    }

    public static Integer[][] covert(int[][] a) {
        int m = a.length, n = a[0].length;
        Integer[][] t = new Integer[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                t[i][j] = a[i][j];
            }
        }
        return t;
    }

    public static Integer[][][] covert(int[][][] a) {
        int m = a.length, n = a[0].length, z = a[0][0].length;
        Integer[][][] t = new Integer[m][n][z];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < z; k++) {
                    t[i][j][k] = a[i][j][k];
                }
            }
        }
        return t;
    }


}
