package leetcode.utils;

import leetcode.function.Expect;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: wuxin0011
 * @Description:
 */
@SuppressWarnings("all")
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


    public static <T> boolean deepEqual(List<T> a, List<T> b, boolean isStrict) {
        if (a == b) {
            System.out.println("ok");
            return true;
        }
        if (a == null || b == null || a.size() != b.size()) {
            System.out.println("result.size() != expect.size()");
            return false;
        }
        int n = a.size();
        if (isStrict) {
            int idx = -1;
            for (int i = 0; i < n; i++) {
                T t1 = a.get(i);
                T t2 = b.get(i);
                if (t1 == null || !valid(t1, t2, t1.getClass().getSimpleName(), isStrict)) {
                    idx = i;
                    break;
                }
            }
            if (idx == -1) {
                // System.out.println("ok");
                return true;
            } else {
                System.out.println("error");
                System.out.println("index = " + idx + ",a:" + a.get(idx) + ",b = " + b.get(idx));
                return false;
            }
        } else {
            Set<T> aset = new HashSet<>();
            Set<T> bset = new HashSet<>();
            for (int i = 0; i < n; i++) {
                T t1 = a.get(i);
                T t2 = b.get(i);
                aset.add(t1);
                bset.add(t2);
            }
            return valid(aset, bset);
        }

    }

    public static <T> boolean deepEqual(T[] a, T[] b, boolean isStrict) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null || a.length != b.length) {
            System.out.println(" result = " + Arrays.toString(a) + ",length = " + a.length);
            System.out.println(" expect = " + Arrays.toString(b) + ",legnth = " + b.length);
            return false;
        }
        if (isStrict) {
            int n = a.length, x = -1;
            for (int i = 0; i < n; i++) {
                if (!valid(a[i], b[i], b[i].getClass().getSimpleName(), isStrict)) {
                    x = i;
                    break;
                }
            }
            if (x != -1) {
                System.err.println("error:( idx = " + x + " expect result = " + b[x] + ",but result =  " + a[x]);
                return false;
            }
            return true;
        } else {
            Set<T> aset = new HashSet<>();
            Set<T> bset = new HashSet<>();
            for (int i = 0; i < a.length; i++) {
                aset.add(a[i]);
                bset.add(b[i]);
            }
            // valid
            return valid(aset, bset);
        }

    }


    public static <T> boolean deepEqual(T[][] a, T[][] b, boolean isStrict) {
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
                if (!valid(a[i][j], b[i][j], b[i][j].getClass().getSimpleName(), isStrict)) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        if (x == -1) {
            return true;
        } else {
            System.err.println("error : (" + x + "," + y + " ) expect result = " + b[x][y] + ",but result =  " + a[x][y]);
            return false;
        }

    }


    public static boolean deepEqual(ListNode result, ListNode expect) {
        if (result == expect) return true;

        if (result == null || expect == null) return false;

        while (result != null && expect != null) {
            if (result.val != expect.val) {
                System.out.println("result = " + result.val + ",expect = " + expect.val);
                return false;
            }
            result = result.next;
            expect = expect.next;
        }

        if (result != expect) {
            System.out.println("node length not equal");
            return false;
        }
        return true;
    }


    public static boolean deepEqual(TreeNode result, TreeNode expect) {
        if (result == expect) return true;
        if (result == null || expect == null) return false;

        Deque<TreeNode> rq = new ArrayDeque<>();
        Deque<TreeNode> eq = new ArrayDeque<>();
        rq.add(result);
        eq.add(expect);

        while (!rq.isEmpty() && !eq.isEmpty()) {
            int s1 = rq.size();
            int s2 = eq.size();
            if (s1 != s2) {
                return false;
            }
            int size = s1;
            while (size > 0) {
                size--;
                TreeNode rNode = rq.poll();
                TreeNode eNode = eq.poll();
                if (rNode == null || eNode == null) {
                    return false;
                }
                if (rNode.val != eNode.val) {
                    return false;
                }

                if (rNode.left != null) {
                    rq.add(rNode.left);
                }
                if (rNode.right != null) {
                    rq.add(rNode.right);
                }

                if (eNode.left != null) {
                    eq.add(eNode.left);
                }
                if (eNode.right != null) {
                    eq.add(eNode.right);
                }

            }
        }
        return rq.size() == eq.size();
    }

    public static <T> boolean deepEqual(T[][][] a, T[][][] b, boolean isStrict) {
        if (a == b) {
            // System.out.println("ok");
            return true;
        }
        if (a == null || b == null || a.length != b.length || a[0].length != b[0].length || a[0][0].length != b[0][0].length) {
            System.out.println("error length not equal");
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
                    if (!valid(a[i][j][k], b[i][j][k], b[i][j][k].getClass().getSimpleName(), isStrict)) {
                        x = i;
                        y = j;
                        o = k;
                        break;
                    }
                }
            }
        }
        if (x == -1) {
            // System.out.println("ok");
            return true;
        } else {
            System.out.println("error" + "index = (" + x + "," + y + "," + o + "),expect result = " + b[x][y][o] + ",but result = " + a[x][y][0]);
            return false;
        }


    }


    public static boolean valid(Object result, Object expect, String returnType, boolean isStrict) {
        if (result == expect) {
            return true;
        }
        if (result == null) {
            System.out.println("result = null expect =" + expect);
            return false;
        }
        if (returnType == null) {
            System.out.println("not support result is null");
            return false;
        }
        try {
            switch (returnType) {
                case "int[]": {
                    Integer[] e = covert((int[]) expect);
                    Integer[] r = covert((int[]) result);
                    return deepEqual(r, e, isStrict);
                }
                case "int[][]": {
                    Integer[][] e = covert((int[][]) expect);
                    Integer[][] r = covert((int[][]) result);
                    return deepEqual(r, e, isStrict);
                }
                case "int[][][]": {
                    Integer[][][] e = covert((int[][][]) expect);
                    Integer[][][] r = covert((int[][][]) result);
                    return deepEqual(r, e, isStrict);
                }
                case "String[]": {
                    String[] r = (String[]) result;
                    String[] e = (String[]) expect;
                    return deepEqual(r, e, isStrict);
                }
                case "String[][]":
                    return deepEqual((String[][]) result, (String[][]) expect, isStrict);
                case "String[][][]":
                    return deepEqual((String[][][]) result, (String[][][]) expect, isStrict);
                case "char[]": {
                    Character[] e = covert((char[]) expect);
                    Character[] r = covert((char[]) result);
                    return deepEqual(r, e, isStrict);
                }
                case "char[][]": {
                    Character[][] e = covert((char[][]) expect);
                    Character[][] r = covert((char[][]) result);
                    return deepEqual(r, e, isStrict);
                }
                case "char[][][]": {
                    Character[][][] e = covert((char[][][]) expect);
                    Character[][][] r = covert((char[][][]) result);
                    return deepEqual(r, e, isStrict);
                }
                case "TreeNode": {
                    TreeNode e = (TreeNode) expect;
                    TreeNode r = (TreeNode) result;
                    return deepEqual(r, e);
                }
                case "ListNode": {
                    ListNode e = (ListNode) expect;
                    ListNode r = (ListNode) result;
                    return deepEqual(r, e);
                }

                case "List":
                case "ArrayList":
                    return deepEqual((ArrayList<Object>) result, (ArrayList<Object>) expect, isStrict);
                default:
                    boolean t = expect != null && expect.equals(result);
                    boolean isArray = expect != null && expect.getClass().getSimpleName().contains("[]");
                    if (isArray) {
                        t = Arrays.deepEquals((Object[]) result, (Object[]) expect);
                        if (!t) {
                            System.err.println("expect result = " + Arrays.deepToString((Object[]) expect) + ",but result = " + Arrays.deepToString((Object[]) result));
                        }
                    } else {
                        if(!t){
                            System.err.println("expect result = " + expect + ",but result = " + result);
                        }
                    }
                    return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static Integer[] covert(int[] a) {
        Integer[] t = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            t[i] = a[i];
        }
        return t;
    }

    public static Long[] covert(long[] a) {
        Long[] t = new Long[a.length];
        for (int i = 0; i < a.length; i++) {
            t[i] = a[i];
        }
        return t;
    }


    public static Float[] covert(float[] a) {
        Float[] t = new Float[a.length];
        for (int i = 0; i < a.length; i++) {
            t[i] = a[i];
        }
        return t;
    }

    public static Double[] covert(double[] a) {
        Double[] t = new Double[a.length];
        for (int i = 0; i < a.length; i++) {
            t[i] = a[i];
        }
        return t;
    }

    public static Character[] covert(char[] a) {
        Character[] t = new Character[a.length];
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

    public static Character[][] covert(char[][] a) {
        int m = a.length, n = a[0].length;
        Character[][] t = new Character[m][n];
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


    public static Character[][][] covert(char[][][] a) {
        int m = a.length, n = a[0].length, z = a[0][0].length;
        Character[][][] t = new Character[m][n][z];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < z; k++) {
                    t[i][j][k] = a[i][j][k];
                }
            }
        }
        return t;
    }


    public static <T> T[] covert(Object[] a, Class<T> c) {
        T[] t = (T[]) Array.newInstance(c, a.length);
        try {
            for (int i = 0; i < a.length; i++) {
                t[i] = (T) a[i];
            }
        } catch (Exception e) {
            // ignore

        }
        return t;
    }


    public static <T> boolean valid(Set<T> aset, Set<T> bset) {
        if (aset == bset) {
            return true;
        }
        if (aset.size() != bset.size()) {
            return false;
        }
        int cnt = 0;
        for (T a : aset) {
            if (bset.contains(a)) {
                cnt++;
            } else {
                return false;
            }
        }
        return cnt == aset.size();
    }


}
