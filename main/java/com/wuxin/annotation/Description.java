package com.wuxin.annotation;

import com.wuxin.utils.Bean.Difficulty;
import com.wuxin.utils.Bean.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: wuxin0011
 * @Description: 描述该类信息
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {
    String value() default "";

    String url() default "https://leetcode.cn/";

    Tag tag() default Tag.OTHER;

    Difficulty diff() default Difficulty.SIMPLE;


}
