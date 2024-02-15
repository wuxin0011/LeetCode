package com.wuxin.utils;

import com.wuxin.function.Expect;

import java.text.SimpleDateFormat;
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


    public static <T> void testList(List<T> a, List<T> b) {
        if (a == b) {
            System.out.println("ok");
            return;
        }
        if (a == null || b == null || a.size() != b.size()) {
            System.out.println("error");
            return;
        }
        int n = a.size();
        boolean f = true;
        for (int i = 0; i < n; i++) {
            if (!a.get(i).equals(b.get(i))) {
                f = false;
                break;
            }
        }
        if (f) {
            System.out.println("ok");
        } else {
            System.out.println("error");
            System.out.println("a:" + a);
            System.out.println("b:" + b);
        }
    }


}
