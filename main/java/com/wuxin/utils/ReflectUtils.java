package com.wuxin.utils;

import com.wuxin.annotation.Description;
import com.wuxin.utils.Bean.Type;

import java.lang.reflect.Method;

/**
 * @author: wuxin0011
 * @Description:
 */
public class ReflectUtils {


    private static final String EMPTY_STR = "";

    public static void main(String[] args) {
        // test ...
    }

    public static <T> String getClassInfo(Class<T> c) {
        if (c == null) {
            return EMPTY_STR;
        }
        return getDescriptionInfo(c.getAnnotation(Description.class), c);
    }

    public static <T> String getMethodInfo(Class<T> c, String methodName) {
        try {
            return getMethodInfo(c.getDeclaredMethod(methodName, c));
        } catch (NoSuchMethodException e) {
            return EMPTY_STR;
        }
    }


    public static <T> String getMethodInfo(Method method) {
        if (method == null) {
            return EMPTY_STR;
        }
        Description annotation = method.getAnnotation(Description.class);
        return getDescriptionInfo(annotation, method.getClass());
    }

    public static <T> String getDescriptionInfo(Description description, Class<T> c) {
        if (description == null) {
            return EMPTY_STR;
        }
        // tag
        String tag = description.tag() != null ? description.tag().getTag() : EMPTY_STR;
        tag = isEmpty(description.customTag()) ? tag : description.customTag();
        // diff
        String difficulty = description.diff() != null ? description.diff().getDesc() : EMPTY_STR;
        // url
        String url = description.url();
        // desc
        String desc = description.value();
        // types
        String types = getTypes(description);
        // views
        String views = getViews(description);
        return "======================================题目信息=======================" +
                (!isEmpty(desc) ? "\n简介: " + desc : "") +
                (!isEmpty(tag) ? "\n标签: " + tag : "") +
                (!isEmpty(types) ? "\n类型: " + types : "") +
                (!isEmpty(difficulty) ? "\n难度: " + difficulty : "") +
                (!isEmpty(url) ? "\n题目地址: " + url : "") +
                (!isEmpty(views) ? "\n参考链接: " + views : "") +
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


    private static String getViews(Description description) {
        if (description == null) {
            return EMPTY_STR;
        }
        StringBuilder builder = new StringBuilder();
        String[] views = description.views();
        if (isEmpty(views)) {
            return EMPTY_STR;
        }
        int len = views.length;
        for (int i = 0; i < len; i++) {
            if (!isEmpty(views[i])) {
                builder.append((int) (i + 1));
                builder.append(" 、");
                builder.append(views[i]);
                if (i != len - 1) {
                    builder.append(" , ");
                }
            }

        }

        return "null".contentEquals(builder) || EMPTY_STR.contentEquals(builder) ? EMPTY_STR : builder.toString();
    }


    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isEmpty(String[] s) {
        return s == null || s.length == 0;
    }


}
