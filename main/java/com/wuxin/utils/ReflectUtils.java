package com.wuxin.utils;

import com.wuxin.annotation.Description;
import com.wuxin.utils.Bean.Type;

import java.lang.reflect.Method;

/**
 * @author: wuxin0011
 * @Description:
 */
public class ReflectUtils {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder);
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
        // 自定义标签
        String s = description.customTag();
        tag = isEmpty(s) ? tag : s;
        // 自定义题目类型
        String typeString = getTypes(description);
        return "======================================题目信息=======================" +
                "\n简介: " + desc +
                "\n标签: " + tag +
                (!isEmpty(typeString) ? "\n类型:" + typeString : "") +
                "\n难度: " + difficulty +
                "\n地址: " + url +
                "\n======================================输出结果=========================";
    }


    public static String getTypes(Description description) {
        Type[] types = description.types();
        String[] customTypes = description.customType();
        StringBuilder typeBuilder = new StringBuilder();
        if (types != null) {
            for (Type type : types) {
                if (!isEmpty(type.getType())) {
                    typeBuilder.append(type);
                }
            }
        }
        if (customTypes != null) {
            for (String type : customTypes) {
                if (!isEmpty(type)) {
                    typeBuilder.append(type);
                }
            }
        }
        return typeBuilder.toString();
    }


    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }


}
