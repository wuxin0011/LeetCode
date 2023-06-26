package com.wuxin.utils;

import com.wuxin.annotation.Description;

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
        Description annotation = c.getAnnotation(Description.class);
        return annotation != null ? annotation.value() : c.getSimpleName();
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
        return annotation != null ? annotation.value() : null;
    }
}
