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
       // test ...
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
        // tag
        String tag = description.tag() != null ? description.tag().getTag() : "";
        tag = isEmpty(description.customTag()) ? tag : description.customTag();
        // diff
        String difficulty = description.diff() != null ? description.diff().getDesc() : "";
        // url
        String url = description.url();
        // desc
        String desc = description.value();
        // types
        String types = getTypes(description);
        return "======================================题目信息=======================" +
                (!isEmpty(desc) ? "\n简介: " + desc : "") +
                (!isEmpty(tag) ? "\n标签: " + tag : "") +
                (!isEmpty(types) ? "\n类型: " + types : "") +
                (!isEmpty(difficulty) ? "\n难度: " + difficulty : "") +
                (!isEmpty(url) ? "\n地址: " + url : "") +
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
