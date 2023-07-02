package com.wuxin.utils;

import com.wuxin.annotation.Description;
import com.wuxin.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: wuxin0011
 * @Description:
 */
public class ReflectUtils {

    public static void main(String[] args) {

    }

    public static <T> String getClassInfo(Class<T> c) {
        if (c == null) {
            return null;
        }
        return getDescriptionInfo(c.getAnnotation(Description.class), c);
    }

    public static <T> String getMethodInfo(Class<T> c, String methodName) {
        try {
            return getMethodInfo(c.getDeclaredMethod(methodName, c));
        } catch (NoSuchMethodException e) {
            return null;
        }
    }


    public static <T> String getMethodInfo(Method method) {
        if (method == null) {
            return null;
        }
        Description annotation = method.getAnnotation(Description.class);
        return getDescriptionInfo(annotation, method.getClass());
    }

    public static <T> String getDescriptionInfo(Description description, Class<T> c) {
        if (description == null) {
            return null;
        }
        String tag = description.tag().getTag();
        String difficulty = description.diff().getDesc();
        String url = description.url();
        String desc = description.value();
        return "======================================题目信息=======================" +
                "\n简介: " + desc +
                "\n标签: " + tag +
                "\n难度: " + difficulty +
                "\n地址: " + url +
                "\n======================================输出结果=========================";
    }

    @Test
    public void test01() {

    }


    public static void runTests(Class<?> testClass) {
        Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    method.invoke(testClass);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
