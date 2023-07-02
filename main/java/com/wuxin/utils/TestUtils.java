package com.wuxin.utils;

import com.wuxin.annotation.Test;
import com.wuxin.function.Expect;

/**
 * @author: wuxin0011
 * @Description:
 */
public class TestUtils {



    public static void testBoolean(Object real, Object exp) {
        testBoolean(real, exp, System.currentTimeMillis() + " 测试通过！");
    }

    public static void testBoolean(Object real, Object exp, String message) {
        Expect<Object, String> expect = (v1, v2, msg) -> {
            if (v1 != null && v1.equals(v2)) {
                System.out.println(msg);
            } else {
                System.err.println(System.currentTimeMillis() + " 测试失败！" + "v1 = " + v1 + ", V2 = " + v2);
            }
        };
        expect.expect(real, exp, message);
    }

    public static void testArray(int[] arr1, int[] arr2, String message) {
        Expect<int[], String> expect = (v1, v2, msg) -> {
            if (arr1 == arr2) {
                System.out.println("测试通过");
            } else if (arr1 != null && arr2 != null && arr2.length != arr1.length) {
                System.out.println("test error");
            } else if (arr1 != null && arr2 != null) {
                int len = arr2.length;
                boolean flag = true;
                for (int i = 0; i < len; i++) {
                    if (arr1[i] != arr2[i]) {
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
}
